package kg.sunrise.salamat.ui.auth.preface

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.PrefaceFragmentBinding
import kg.sunrise.salamat.ui.main.MainActivity.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PrefaceFragment : BaseFragment<PrefaceFragmentBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PrefaceFragmentBinding {
        return PrefaceFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        lifecycleScope.launch {
            delay(2000)
            if (isUserLoggedIn()) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                val action = PrefaceFragmentDirections.actionPrefaceFragmentToLanguageFragment()
                binding.root.findNavController().navigate(action)
            }
        }
    }

    private fun isUserLoggedIn() = Firebase.auth.currentUser != null

}