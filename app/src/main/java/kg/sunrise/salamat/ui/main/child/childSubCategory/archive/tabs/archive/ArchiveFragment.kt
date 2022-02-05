package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.data.remote.response.Archive
import kg.sunrise.salamat.data.remote.response.ArchiveDto
import kg.sunrise.salamat.data.remote.response.ChecklistItem
import kg.sunrise.salamat.databinding.FragmentTabChildArchiveBinding
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.adapter.ArchiveAdapter
import kg.sunrise.salamat.utils.preference.getLanguage
import timber.log.Timber

class ArchiveFragment: BaseFragment<FragmentTabChildArchiveBinding>() {

    private val adapter = ArchiveAdapter()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTabChildArchiveBinding {
        return FragmentTabChildArchiveBinding.inflate(inflater)
    }

    override fun init() {
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvArchive.adapter = adapter
    }

    fun populateData(archiveList: ArrayList<ArchiveDto>){
        adapter.setData(archiveList)
    }

}