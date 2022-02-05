package kg.sunrise.salamat.ui.auth.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.ChooseFragmentBinding

class ChooseFragment : BaseFragment<ChooseFragmentBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChooseFragmentBinding {
        return ChooseFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            it.findNavController()
                .navigate(ChooseFragmentDirections.actionChooseFragmentToLoginFragment())
        }

        binding.btnRegistration.setOnClickListener {
            it.findNavController()
                .navigate(ChooseFragmentDirections.actionChooseFragmentToRegistrationFragment())
        }
    }
}