package kg.sunrise.salamat.ui.main.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.ProfileFragmentBinding
import kg.sunrise.salamat.databinding.RegionBottomFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWhiteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kg.sunrise.salamat.ui.auth.AuthActivity
import kg.sunrise.salamat.ui.auth.region.RegionFragment
import kg.sunrise.salamat.ui.auth.region.bottom.RegionBottomFragment
import kg.sunrise.salamat.utils.extesions.setStatusColor
import kg.sunrise.salamat.utils.preference.*


class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {

    private lateinit var toolbar: ToolbarWhiteBinding

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?
    ): ProfileFragmentBinding {
        return ProfileFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        initToolbar()
        setUpUI()
        initListeners()
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.profile_acc)
    }

    private fun setUpUI(){
        backgroundSet(R.drawable.preface_bg)
        setStatusColor(requireActivity())
        binding.tvLogin.text = getPhone(requireContext())
    }


    private fun initListeners(){
         binding.tvAboutApp.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAboutAppFragment())
        }
        
        binding.tvHelp.setOnClickListener {
            findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToHelpFragment()
            )
        }

        binding.tvExit.setOnClickListener {
            showExitDialog()
        }

        binding.tvChangeRegion.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToChangeRegionFragment()
            )
        }
}

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("")
            .setMessage(R.string.exit_text)
            .setCancelable(true)
            .setPositiveButton(R.string.exit) { _: DialogInterface?, _: Int ->
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, AuthActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton(R.string.stay_acc) { _: DialogInterface?, _: Int -> }

        val alert = builder.create()
        alert.show()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.pink_ed5db4, null))
            alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.pink_ed5db4, null))
        } else {
            alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.pink_ed5db4))
            alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.pink_ed5db4))
        }
    }


}