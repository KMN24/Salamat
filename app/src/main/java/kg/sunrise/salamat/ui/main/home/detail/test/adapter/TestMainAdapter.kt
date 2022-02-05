package kg.sunrise.salamat.ui.main.home.detail.test.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.sunrise.salamat.data.remote.response.TestQuestionsItem
import kg.sunrise.salamat.ui.main.home.detail.test.detail.TestDetailFragment



class TestMainAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    private var title = ""
    lateinit var testDetailFragment: TestDetailFragment
    var dataTest = arrayListOf<TestQuestionsItem>()
    override fun getItemCount(): Int = dataTest.size
    private var count = 0
    companion object {
        var isClickedOnAnswer = false
    }


    override fun createFragment(position: Int): Fragment {
        testDetailFragment = TestDetailFragment()
        currentPosition(position)
        return testDetailFragment
    }

    private fun currentPosition(position: Int) {
        testDetailFragment.populateData(dataTest[position], title)
    }

    fun setData(data: ArrayList<TestQuestionsItem>, title: String) {
        this.title = title
        initData(data)
    }

    private fun initData(
        array: ArrayList<TestQuestionsItem>,
    ): ArrayList<TestQuestionsItem> {
        dataTest = array
        return dataTest
    }
}