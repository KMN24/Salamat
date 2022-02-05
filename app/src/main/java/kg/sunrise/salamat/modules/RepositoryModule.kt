package kg.sunrise.salamat.modules

import kg.sunrise.salamat.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepo(get()) }
    single { HomeRepo(get()) }
    single { NotificationRepo(get()) }
    single { ChildRepo(get()) }
    single { ProfileRepo(get()) }
    single { MedBackRepo(get()) }
}