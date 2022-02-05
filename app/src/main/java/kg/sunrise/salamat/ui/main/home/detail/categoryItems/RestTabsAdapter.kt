package kg.sunrise.salamat.ui.main.home.detail.categoryItems

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.sunrise.salamat.data.remote.response.CategoryItemDTO
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.CategoryDevelopNormsFragment
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.ParentSkillsFragment
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.MedicalSupportFragment


class RestTabsAdapter(
    fm: Fragment
) : FragmentStateAdapter(fm) {

    private val developNormsFragment = CategoryDevelopNormsFragment()
    private val parentSkillsFragment = ParentSkillsFragment()
    private val medicalSupport = MedicalSupportFragment()

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            DEVELOP_NORMS -> developNormsFragment
            PARENT_SKILLS -> parentSkillsFragment
            MEDICAL_SUPPORT -> medicalSupport
            else -> throw Throwable("Position $position doesn't exist")
        }

    }

    fun populateData(categoryData: CategoryItemDTO) {
        developNormsFragment.populateData(categoryData.content.doctorSupervision)
        parentSkillsFragment.populateData(categoryData.content.parentResponsibilities)
        medicalSupport.populatedata(categoryData.medicalSupport)
    }

    companion object {

        const val DEVELOP_NORMS = 0
        const val PARENT_SKILLS = 1
        const val MEDICAL_SUPPORT = 2

        private const val ITEM_COUNT = 3
    }
}