package kg.sunrise.salamat.ui.main.child.childSubCategory

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.sunrise.salamat.data.remote.response.ChildDetailResponse
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve.MedicalObservingFragment
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.NormsInDevolopmentFragment
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter.ParametersChildFragment
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ParentResponsibilityFragment
import timber.log.Timber
import java.util.*

class ChildTabsAdapter(
    fm: Fragment
) : FragmentStateAdapter(fm) {
    private var slug = ""
    private var data: ChildDetailResponse? = null
    private val normsInDevolopmentFragment = NormsInDevolopmentFragment()
    private val parentResponsibilityFragment = ParentResponsibilityFragment()
    private val medicalObservingFragment = MedicalObservingFragment()
    private val parametersChildFragment = ParametersChildFragment()

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                normsInDevolopmentFragment.populateData(data!!.currentContent.inlineContent.doctorSupervisionRu)
                normsInDevolopmentFragment
            }
            1 -> {
                parentResponsibilityFragment.populateData(data!!.currentContent.inlineContent.parentResponsibilitiesRu)
                parentResponsibilityFragment
            }
            2 -> {
                medicalObservingFragment.populateData(data!!.subData , slug)
                medicalObservingFragment
            }
            3 -> {
                parametersChildFragment.populateSlug(slug)
                parametersChildFragment
            }
            else -> throw IllegalStateException("that position doesnt exist $position")
        }
    }

    fun sendData(data: ChildDetailResponse , slug : String): ChildDetailResponse {
        this.data = data
        this.slug = slug
        return data
    }

}