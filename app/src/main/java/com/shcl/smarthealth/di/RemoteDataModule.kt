package com.shcl.smarthealth.di


import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.api.SurveyApi
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.api.WeatherApi
import com.shcl.smarthealth.data.repository.dataSoruceImpl.DashBoardRemoteDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSoruceImpl.SurveyRemoteDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSoruceImpl.UserRemoteDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.SurveyRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideDashBoardRemoteDataSource(@NetworkModule.shcl dashBoardApi: DashBoardApi , @NetworkModule.weather weatherApi: WeatherApi) : DashBoardRemoteDataSource =
        DashBoardRemoteDataSourceImpl(dashBoardApi , weatherApi)

    @Provides
    fun provideUserRemoteDataSource(@NetworkModule.shcl userApi: UserApi) : UserRemoteDataSource =
        UserRemoteDataSourceImpl(userApi)

    @Provides
    fun provideSurveyRemoteDataSource(@NetworkModule.shcl surveyApi: SurveyApi) : SurveyRemoteDataSource =
        SurveyRemoteDataSourceImpl(surveyApi)

}