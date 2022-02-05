package kg.sunrise.salamat.modules

import kg.sunrise.salamat.ui.auth.confirm.ConfirmFragmentViewModel
import kg.sunrise.salamat.ui.auth.download.DownLoadViewModel
import kg.sunrise.salamat.ui.auth.login.LoginViewModel
import kg.sunrise.salamat.ui.auth.region.RegionViewModel
import kg.sunrise.salamat.ui.main.MainActivity.ShareViewModel
import kg.sunrise.salamat.ui.main.child.ChildViewModel
import kg.sunrise.salamat.ui.main.child.childAdd.ChildAddViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.ChildSubViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.ChildArchiveViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.ArchiveViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.ChildParamViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter.ChildParameterViewModel
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve.MedicalObserveViewModel
import kg.sunrise.salamat.ui.main.child.detail.ChildDetailViewModel
import kg.sunrise.salamat.ui.main.home.HomeViewModel
import kg.sunrise.salamat.ui.main.home.detail.HomeDetailViewModel
import kg.sunrise.salamat.ui.main.home.detail.test.TestMainViewModel
import kg.sunrise.salamat.ui.main.home.detail.test.detail.TestDetailViewModel
import kg.sunrise.salamat.ui.main.home.doctors_appointment.step1.DoctorsAppointmentStep1ViewModel
import kg.sunrise.salamat.ui.main.home.doctors_appointment.step2.DoctorsAppointmentStep2ViewModel
import kg.sunrise.salamat.ui.main.home.doctors_appointment.step3.DoctorsAppointmentStep3ViewModel
import kg.sunrise.salamat.ui.main.home.freqAskedQues.FreqAskedQuesViewModel
import kg.sunrise.salamat.ui.main.home.freqAskedQues.detail.fragment.FaqDetailViewModel
import kg.sunrise.salamat.ui.main.notification.NotificationViewModel
import kg.sunrise.salamat.ui.main.profile.ProfileViewModel
import kg.sunrise.salamat.ui.main.profile.aboutApp.AboutAppViewModel
import kg.sunrise.salamat.ui.main.profile.changeCity.ChangeRegionViewModel
import kg.sunrise.salamat.ui.main.profile.help.HelpViewModel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {LoginViewModel(get())}
    viewModel { RegionViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { HomeDetailViewModel(get()) }
    viewModel { DownLoadViewModel(get()) }
    viewModel { NotificationViewModel(get()) }
    viewModel { ChildViewModel(get()) }
    viewModel { ChildAddViewModel(get()) }
    viewModel { ChildSubViewModel(get()) }
    viewModel { AboutAppViewModel(get()) }
    viewModel { HelpViewModel(get()) }
    viewModel { FreqAskedQuesViewModel(get()) }
    viewModel { FaqDetailViewModel(get()) }
    viewModel { DoctorsAppointmentStep1ViewModel(get()) }
    viewModel { DoctorsAppointmentStep2ViewModel(get()) }
    viewModel { DoctorsAppointmentStep3ViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { TestMainViewModel(get()) }
    viewModel { TestDetailViewModel(get()) }
    viewModel { ChildDetailViewModel(get()) }
    viewModel { ChildArchiveViewModel(get()) }
    viewModel { ArchiveViewModel() }
    viewModel { ChildParamViewModel(get()) }
    viewModel { ChangeRegionViewModel(get()) }
    viewModel { ConfirmFragmentViewModel(get()) }
    viewModel { ShareViewModel() }
    viewModel { MedicalObserveViewModel(get()) }
    viewModel { ChildParameterViewModel(get()) }
}