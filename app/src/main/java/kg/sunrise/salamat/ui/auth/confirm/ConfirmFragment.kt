package kg.sunrise.salamat.ui.auth.confirm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kg.sunrise.salamat.base.activity.BaseActivity
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.utils.preference.*
import timber.log.Timber
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.enums.AuthType
import kg.sunrise.salamat.data.remote.response.User
import kg.sunrise.salamat.databinding.ConfirmFragmentBinding
import kotlinx.coroutines.delay
import org.koin.android.viewmodel.ext.android.viewModel

import java.util.concurrent.TimeUnit

class ConfirmFragment : BaseFragmentWithVM<ConfirmFragmentBinding, ConfirmFragmentViewModel>() {
    override val viewModel by viewModel<ConfirmFragmentViewModel>()
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var storedVerificationId: String? = ""

    private val args: ConfirmFragmentArgs by navArgs()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ConfirmFragmentBinding {
        return ConfirmFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        auth = Firebase.auth

        val callback = getCallback()
        sendCode(callback)

        initTextWatcher()
        initListener()
    }

    private fun initListener() {
        binding.btnConfirm.setOnClickListener {
            verifyPhoneNumberWithCode(storedVerificationId, binding.edtCode.unMasked)
        }
    }

    private fun initTextWatcher() {
        binding.edtCode.addTextChangedListener {
            binding.btnConfirm.isEnabled = it?.length == 7
        }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendCode(callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(args.confirmArgs.phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callback)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun getCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Timber.e("err ${e.message}")
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        user.getIdToken(false).addOnSuccessListener {
                            setRegistraturaToken(requireContext(), it.token!!)
                            setAccessUid(requireContext(), user.uid)
                            gotoNextPart()
                        }
                    }
                    Timber.e("success")
                } else {
                    (requireActivity() as BaseActivity).toast(getString(R.string.confirm_error))
                }
            }
    }

    private fun gotoNextPart() {
        if (args.confirmArgs.type == AuthType.AUTH) {
            viewModel.setLogin(getPhone(requireContext()))
        } else {
            binding.root.findNavController()
                .navigate(ConfirmFragmentDirections.actionConfirmFragmentToRegionFragment())
        }
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is User -> {
                setAccessToken(requireContext(), data.token)
                binding.root.findNavController()
                    .navigate(ConfirmFragmentDirections.actionConfirmFragmentToDownLoadFragment())
            }

        }
    }

    override fun successRequest() {

    }
}