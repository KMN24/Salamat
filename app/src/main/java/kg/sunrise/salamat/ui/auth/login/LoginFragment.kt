package kg.sunrise.salamat.ui.auth.login

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.arg.ConfirmArgData
import kg.sunrise.salamat.data.enums.AuthType
import kg.sunrise.salamat.data.remote.response.CheckNumberResponse
import kg.sunrise.salamat.data.remote.response.User
import kg.sunrise.salamat.databinding.LoginFragmentBinding
import kg.sunrise.salamat.utils.extesions.navigate
import kg.sunrise.salamat.utils.preference.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragmentWithVM<LoginFragmentBinding, LoginViewModel>() {

    override val viewModel by viewModel<LoginViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LoginFragmentBinding {
        return LoginFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setEmtyToken()
        backgroundSet(R.drawable.preface_bg)
        initWatcher()
        initListener()
    }

    private fun setEmtyToken() {
        setEmptyToken(requireContext())
    }

    private fun initWatcher() {
        binding.edtPhone.addTextChangedListener {
            binding.btnLogin.isEnabled = it?.length == 17
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            setAccessPhone(requireContext(), getString(R.string.phoneNumber , binding.edtPhone.unMasked))
            viewModel.getExistNumber(getPhone(requireContext()))
        }
    }


    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is CheckNumberResponse -> {
                if (data.exist) {
                    navigate(LoginFragmentDirections.actionLoginFragmentToConfirmFragment( ConfirmArgData(
                        getPhone(requireContext()), AuthType.AUTH)))
                } else {
                    toast(getString(R.string.please_reg))
                }
            }

            }
        }

    override fun successRequest() {

    }
}