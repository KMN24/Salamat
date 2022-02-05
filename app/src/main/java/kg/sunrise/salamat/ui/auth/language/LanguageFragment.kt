package kg.sunrise.salamat.ui.auth.language

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.LanguageFragmentBinding
import kg.sunrise.salamat.utils.preference.setLanguage

class LanguageFragment : BaseFragment<LanguageFragmentBinding>() {
    private lateinit var activatedTv : TextView
    private var language = "ru"

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LanguageFragmentBinding {
        return LanguageFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        initUI()
        initListener()
    }

    private fun initListener() {
        binding.tvKy.setOnClickListener { changeActivate(it as TextView, "ky") }
        binding.tvRu.setOnClickListener { changeActivate(it as TextView, "ru") }
        binding.btnNext.setOnClickListener {
            setLanguage(requireContext(), language)
            it.findNavController().navigate(
                LanguageFragmentDirections.actionLanguageFragmentToChooseFragment()
            )
        }
    }

    private fun changeActivate(textView: TextView, language: String) {
        if (activatedTv != textView) {
            this.language = language
            activatedTv.isActivated = false
            textView.isActivated = true
            activatedTv = textView
        }
    }

    private fun initUI() {
        activatedTv = binding.tvRu
        binding.tvRu.isActivated = true
        binding.tvKy.isActivated = false
    }

}