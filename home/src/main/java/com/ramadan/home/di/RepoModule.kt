package com.ramadan.home.di

//@Module
//@InstallIn(SingletonComponent::class)
//object RepoModule {
//
//    @Provides
//    @Singleton
//    fun provideRemoteDataSource(networkManager: AppNetworkManager): RemoteDataSource =
//        RemoteDataSourceImp(networkManager)
//
//    @Provides
//    @Singleton
//    fun provideLocalDataSource(sharedPref: SharedPref): LocalDataSource =
//        LocalDataSourceImp(sharedPref)
//
//    @Provides
//    @Singleton
//    fun provideSplashRepository(
//        remoteDataSource: RemoteDataSource,
//        localDataSource: LocalDataSource
//    ): SplashRepo {
//        return HomeRepoImpl(remoteDataSource, localDataSource)
//    }
//
//}