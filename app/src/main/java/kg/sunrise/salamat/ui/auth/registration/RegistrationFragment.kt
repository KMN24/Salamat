package kg.sunrise.salamat.ui.auth.registration

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.arg.ConfirmArgData
import kg.sunrise.salamat.data.enums.AuthType
import kg.sunrise.salamat.data.remote.response.CheckNumberResponse
import kg.sunrise.salamat.databinding.RegistrationFragmentBinding
import kg.sunrise.salamat.ui.auth.login.LoginViewModel
import kg.sunrise.salamat.utils.extesions.navigate
import kg.sunrise.salamat.utils.preference.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class RegistrationFragment : BaseFragmentWithVM<RegistrationFragmentBinding, LoginViewModel>() {

    override val viewModel by viewModel<LoginViewModel>()

    override val progressBar by lazy { binding.inclProgress.clProgress }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegistrationFragmentBinding {
        return RegistrationFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        toast(R.string.before_registrion)
        initWatcher()
        setEmptyToken()
        initListener()
    }

    private fun setEmptyToken() {
        setEmptyToken(requireContext())
    }

    private fun initWatcher() {
        binding.edtPhone.addTextChangedListener {
            binding.btnRegistration.isEnabled = it?.length == 17
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun initListener() {
        binding.btnRegistration.setOnClickListener {
            if (!binding.checkReg.isChecked) {
                toast(R.string.before_registrion)
                return@setOnClickListener
            }
            setAccessPhone(requireContext() , getString(R.string.phoneNumber , binding.edtPhone.unMasked))
            viewModel.getExistNumber(getPhone(requireContext()))
        }
        binding.tvConf.setOnClickListener {
            it.findNavController()
                .navigate(RegistrationFragmentDirections.actionRegistrationFragmentToConfidentialityFragment())
        }
    }


    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is CheckNumberResponse -> {
                if (!data.exist) {
                    navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToConfirmFragment(
                                ConfirmArgData(
                                    getPhone(requireContext()), AuthType.REGISTRATION
                                )
                            )
                        )
                } else {
                    toast(getString(R.string.please_auth))
                }
            }
        }
    }

    override fun successRequest() {

    }
}