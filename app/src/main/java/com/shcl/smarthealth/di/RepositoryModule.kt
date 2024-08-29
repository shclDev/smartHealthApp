package com.shcl.smarthealth.di

import com.shcl.smarthealth.data.repository.DashBoardRepositoryImpl
import com.shcl.smarthealth.data.repository.IsensRepositoryImpl
import com.shcl.smarthealth.data.repository.MeasurementRepositoryImpl
import com.shcl.smarthealth.data.repository.NCloudRepositoryImpl
import com.shcl.smarthealth.data.repository.OmronRepositoryImpl
import com.shcl.smarthealth.data.repository.SurveyRepositoryImpl
import com.shcl.smarthealth.data.repository.UserRepositoryImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.LocalDBDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.SpeechRecognizerSource
import com.shcl.smarthealth.data.repository.dataSource.SurveyRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.domain.repository.MeasurmentRepository
import com.shcl.smarthealth.domain.repository.NCloudRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.repository.SurveyRepository
import com.shcl.smarthealth.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDashBoardRepository(
        dashBoardRemoteDataSource: DashBoardRemoteDataSource,
        localDBDataSource: LocalDBDataSource,
    ): DashBoardRepository =
        DashBoardRepositoryImpl(dashBoardRemoteDataSource , localDBDataSource)

    @Provides
    fun provideOmronRepository(
        omronDeviceDataSource: OmronDeviceDataSource,
        localDBDataSource: LocalDBDataSource,
    ) : OmronRepository = OmronRepositoryImpl(omronDeviceDataSource , localDBDataSource)

    @Provides
    fun provideIsensRepository(
        isensDeviceDataSource: IsensDeviceDataSource,
        localDBDataSource: LocalDBDataSource,
    ) : IsensRepository = IsensRepositoryImpl(isensDeviceDataSource , localDBDataSource )

    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        localDBDataSource: LocalDBDataSource,
    ) : UserRepository = UserRepositoryImpl(  userRemoteDataSource , localDBDataSource )


    @Provides
    fun provideSurveyRepository(
        surveyRemoteDataSource: SurveyRemoteDataSource,
        localDBDataSource: LocalDBDataSource,
    ) : SurveyRepository = SurveyRepositoryImpl(  surveyRemoteDataSource )

    @Provides
    fun provideNCloudRepository(
        nCloudRemoteDataSource: NCloudRemoteDataSource,
        localDBDataSource: LocalDBDataSource,
        recognizerSource: SpeechRecognizerSource
    ) : NCloudRepository = NCloudRepositoryImpl(  nCloudRemoteDataSource , localDBDataSource , recognizerSource )

    @Provides
    fun provideMeasurementRepository(
        measureRecordRemoteDataSource: MeasureRecordRemoteDataSource
    ) : MeasurmentRepository = MeasurementRepositoryImpl( measureRecordRemoteDataSource )

}