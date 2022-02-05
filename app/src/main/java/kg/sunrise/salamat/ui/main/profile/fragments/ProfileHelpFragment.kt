package kg.sunrise.salamat.ui.main.profile.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.databinding.ProfileHelpFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileHelpFragment : BaseFragmentWithVM<ProfileHelpFragmentBinding, ProfileHelpViewModel>() {
    override val viewModel by viewModel<ProfileHelpViewModel>()

    override val progressBar by lazy { binding.inclProgress.clProgress }

    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProfileHelpFragmentBinding {
        return ProfileHelpFragmentBinding.inflate(inflater, container
        , false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_white_bg)
    }


}