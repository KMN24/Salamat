package kg.sunrise.salamat.ui.auth.download

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.DownLoadFragmentBinding
import kg.sunrise.salamat.ui.main.MainActivity.MainActivity


class DownLoadFragment : BaseFragment<DownLoadFragmentBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DownLoadFragmentBinding = DownLoadFragmentBinding.inflate(inflater, container, false)

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}