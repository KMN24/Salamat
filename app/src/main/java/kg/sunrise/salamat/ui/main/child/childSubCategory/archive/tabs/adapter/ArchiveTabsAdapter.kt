package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.sunrise.salamat.App
import kg.sunrise.salamat.data.remote.response.*
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.ArchiveFragment
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.ChildParamFragment
import kg.sunrise.salamat.utils.Constants
import kg.sunrise.salamat.utils.preference.getLanguage

class ArchiveTabsAdapter(frag: Fragment): FragmentStateAdapter(frag) {

    private val archiveFragment = ArchiveFragment()
    private val childParamFragment = ChildParamFragment()

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            ARCHIVE_FRAGMENT -> archiveFragment
            CHILD_PARAM_FRAGMENT -> childParamFragment
            else -> throw Throwable("Position $position doesn't exist")
        }
    }

    companion object{
        const val ARCHIVE_FRAGMENT = 0
        const val CHILD_PARAM_FRAGMENT = 1
        const val ITEM_COUNT = 2
    }

    fun populateArchiveData(data: ChildDetailResponse){
        val convertData = arrayListOf<ArchiveDto>()
        data.archives.forEach { data -> convertData.add(data.convertToDto(getLanguage(App.context) == Constants.RUS_LANG)) }
        archiveFragment.populateData(convertData)
    }

    fun populateChildParamData(data: ArrayList<ChildParameterResponse>){
        val convertData = arrayListOf<ChildParameterDTO>()
        data.forEach { convertData.add(it.convertToDto(getLanguage(App.context) == Constants.RUS_LANG)) }
        childParamFragment.populateData(convertData)
    }


}