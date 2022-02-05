package kg.sunrise.salamat.ui.main.child.childAdd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.theartofdev.edmodo.cropper.CropImage
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.ChildPost
import kg.sunrise.salamat.databinding.FragmentChildAddBinding
import kg.sunrise.salamat.ui.main.child.childAdd.alerts.ChooseDate
import kg.sunrise.salamat.ui.main.child.childAdd.alerts.ChooseGender
import kg.sunrise.salamat.ui.main.child.childAdd.consts.Gender
import kg.sunrise.salamat.ui.main.child.childAdd.parser.ContentParser
import kg.sunrise.salamat.utils.extesions.getBack
import kg.sunrise.salamat.utils.extesions.gone

import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ChildAddFragment : BaseFragmentWithVM<FragmentChildAddBinding, ChildAddViewModel>() {
    private val parser by lazy {
        ContentParser()
    }
    private val chooseDate by lazy {
        ChooseDate(requireContext())
    }
    private var gender = ""
    private var male: Gender = Gender.DEFAULT
    private var resultUri: Uri = Uri.EMPTY
    override val viewModel by viewModel<ChildAddViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }


    override fun findTypeOfObject(data: Any?) {

    }


    private fun initRequest() {
        binding.btnSave.setOnClickListener {
            if (takeValues())
                viewModel.addChild(
                    ChildPost(
                        name = binding.etName.text.toString(),
                        gender = parser.getGender(gender),
                        avatar = parser.putAvatar(resultUri),
                        birth_date = parser.getDate(chooseDate.date),
                        parent = 0
                    )
                )
        }
    }


    override fun successRequest() {
        toast(getString(R.string.sussessfuly_saved))
        findNavController().navigate(ChildAddFragmentDirections.actionChildAddFragmentToChildFragment())
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChildAddBinding {
        return FragmentChildAddBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        initListeners()
        initRequest()
    }

    private fun takeValues(): Boolean {
        when {
            binding.etName.text.isEmpty() -> toast(R.string.name_child)
            binding.tvChooseDate.text.isEmpty() -> toast(R.string.choose_date)
            binding.tvChooseGender.text.isEmpty() -> toast(R.string.choose_gender)
            resultUri.toString().isEmpty() -> toast(R.string.choose_photo)
            else -> return true
        }
        return false
    }

    private fun initListeners() {
        binding.clImage.setOnClickListener {
            changeAvatar()
        }
        binding.tvChooseDate.setOnClickListener {
            chooseDate.createDatePicker( binding.tvChooseDate)
        }
        binding.tvChooseGender.setOnClickListener {
            ChooseGender {
                male = it
                checkGender()
            }.show(parentFragmentManager, null)
        }

        binding.idNavBack.setOnClickListener {
            it.getBack()
        }
    }

    private fun checkGender() {
        when (male) {
            Gender.MALE -> {
                binding.tvChooseGender.text = getString(R.string.male_sex)
                gender = getString(R.string.male_gender)
            }
            Gender.FEMALE -> {
                binding.tvChooseGender.text = getString(R.string.female_sex)
                gender = getString(R.string.gender_female)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    resultUri = result.uri
                    setPhoto(resultUri, binding.ivChoosenPhoto, binding.tvDownloadPhoto)
                } else {
                    if (result == null) {
                        toast(getString(R.string.choose_photo))
                    }
                }
            }
        }
    }

    private fun setPhoto(uri: Uri, image: ImageView, textView: TextView) {
        image.setImageURI(uri)
        textView.gone()
        binding.ivAvatar.gone()
        val drawable = image.drawable
        image.setImageURI(null)
        binding.clImage.background = drawable
    }

    private fun changeAvatar() {
        CropImage.activity()
            .setFixAspectRatio(true)
            .start(requireContext(), this@ChildAddFragment)
    }
}



