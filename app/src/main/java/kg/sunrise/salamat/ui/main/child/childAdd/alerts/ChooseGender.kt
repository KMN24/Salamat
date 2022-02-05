package kg.sunrise.salamat.ui.main.child.childAdd.alerts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.alertDialog.BaseAlertDialog
import kg.sunrise.salamat.databinding.CvChooseGenderBinding
import kg.sunrise.salamat.ui.main.child.childAdd.consts.Gender

class ChooseGender(var male: (Gender) -> Unit) : BaseAlertDialog<CvChooseGenderBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CvChooseGenderBinding {
        return CvChooseGenderBinding.inflate(inflater)
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.choose_gender_width)
        val height = resources.getDimensionPixelSize(R.dimen.choose_gender_height)
        dialog?.window?.setLayout(width, height)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.male.setOnClickListener {
            male(Gender.MALE)
            dismiss()
        }
        binding.female.setOnClickListener {
            male(Gender.FEMALE)
            dismiss()
        }
    }
}