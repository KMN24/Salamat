package kg.sunrise.salamat.modules

import kg.sunrise.salamat.App.Companion.context
import kg.sunrise.salamat.data.remote.api.AuthApi
import kg.sunrise.salamat.data.remote.api.HomeApi
import kg.sunrise.salamat.data.remote.api.MedBackApi
import kg.sunrise.salamat.data.remote.api.ProfileApi
import org.koin.dsl.module
import retrofit2.Retrofit

private val backRetrofit : Retrofit = createNetworkClient(context)
private val registraturaRetrofit : Retrofit = createRegitrstraturaClient(context)

private val AUTH_API : AuthApi = backRetrofit.create(AuthApi::class.java)
private val HOME_API : HomeApi = backRetrofit.create(HomeApi::class.java)
private val PROFILE_API : ProfileApi = backRetrofit.create(ProfileApi::class.java)

private val Med_Back_API: MedBackApi = registraturaRetrofit.create(MedBackApi::class.java)

val networkModule = module {
    single { AUTH_API }
    single { HOME_API }
    single { PROFILE_API }
    single { Med_Back_API }
}