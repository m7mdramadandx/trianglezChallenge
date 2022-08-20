package com.ramadan.trianglezchallenge.di

import android.content.Context
import androidx.room.Room
import com.ramadan.cache.DBConstant
import com.ramadan.cache.MoviesDatabase
import com.ramadan.cache.SharedPref
import com.ramadan.cache.SharedPrefManager
import com.ramadan.cache.db.MoviesDao
import com.ramadan.home.data.HomeRepoImpl
import com.ramadan.home.data.source.local.LocalDataSource
import com.ramadan.home.data.source.local.LocalDataSourceImp
import com.ramadan.home.data.source.remote.RemoteDataSource
import com.ramadan.home.data.source.remote.RemoteDataSourceImp
import com.ramadan.home.domain.HomeRepo
import com.ramadan.netwrok.ApiService
import com.ramadan.netwrok.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
//        val chuckerInterceptor =
//            ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true).build()

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(chuckerInterceptor)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(interceptor)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
            .baseUrl(com.ramadan.trianglezchallenge.BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideSharedPrefManager(@ApplicationContext appContext: Context) =
        SharedPrefManager(appContext)

    @Provides
    @Singleton
    fun provideShared(sharedPrefManager: SharedPrefManager) = SharedPref(sharedPrefManager)


    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, DBConstant.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSourceImp(apiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(sharedPref: SharedPref): LocalDataSource =
        LocalDataSourceImp(sharedPref)


    @Provides
    @Singleton
    fun provideHomeRepo(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): HomeRepo {
        return HomeRepoImpl(remoteDataSource, localDataSource)
    }

}