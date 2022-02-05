package kg.sunrise.salamat.ui.main.MainActivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.activity.BaseActivity
import kg.sunrise.salamat.ui.main.home.detail.test.detail.result.TestResultFragment
import kg.sunrise.salamat.utils.extesions.gone
import kg.sunrise.salamat.utils.extesions.setupWithNavController
import kg.sunrise.salamat.utils.extesions.visible
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    val viewModel by viewModel<ShareViewModel>()
    private lateinit var nav: BottomNavigationView

    private var currentNavController: LiveData<NavController>? = null
    private val testResult = TestResultFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        nav = findViewById<BottomNavigationView>(R.id.bnv_main)

        val navGraphIds = listOf(
            R.navigation.home_nav,
            R.navigation.child_nav,
            R.navigation.notification_nav,
            R.navigation.profile_nav
        )

        val controller = nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        controller.observe(this, {
            it.addOnDestinationChangedListener { _, destination, arguments ->
                if (destination.id in arrayOf(
                        R.id.homeDetailFragment,
                        R.id.categoryItemsFragment,
                        R.id.childDetailFragment,
                        R.id.childAddFragment,
                        R.id.freqAskedQuesFragment,
                        R.id.helpFragment,
                        R.id.faqDetailFragment,
                        R.id.doctorsAppointmentStep1Fragment,
                        R.id.doctorsAppointmentStep2Fragment,
                        R.id.doctorsAppointmentStep3Fragment,
                        R.id.aboutAppFragment,
                        R.id.faqDetailFragment,
                        R.id.testMainFragment ,
                        R.id.testResultFragment,
                        R.id.childArchiveFragment,
                        R.id.testMainFragment,
                        R.id.childSubFragment
                    )
                ) {
                    nav.gone()
                } else {
                    Log.e("Main", "arguments: $arguments")
                    nav.visible()
                }
            }
        })

        currentNavController = controller
    }

    override fun onNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }
}