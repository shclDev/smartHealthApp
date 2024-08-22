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
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
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
        measureRecordDataSource: MeasureRecordDataSource
    ): DashBoardRepository =
        DashBoardRepositoryImpl(dashBoardRemoteDataSource , measureRecordDataSource)

    @Provides
    fun provideOmronRepository(
        omronDeviceDataSource: OmronDeviceDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : OmronRepository = OmronRepositoryImpl(omronDeviceDataSource , measureRecordDataSource)

    @Provides
    fun provideIsensRepository(
        isensDeviceDataSource: IsensDeviceDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : IsensRepository = IsensRepositoryImpl(isensDeviceDataSource , measureRecordDataSource )

    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : UserRepository = UserRepositoryImpl(  userRemoteDataSource , measureRecordDataSource )


    @Provides
    fun provideSurveyRepository(
        surveyRemoteDataSource: SurveyRemoteDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : SurveyRepository = SurveyRepositoryImpl(  surveyRemoteDataSource )

    @Provides
    fun provideNCloudRepository(
        nCloudRemoteDataSource: NCloudRemoteDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : NCloudRepository = NCloudRepositoryImpl(  nCloudRemoteDataSource , measureRecordDataSource )

    @Provides
    fun provideMeasurementRepository(
        measureRecordRemoteDataSource: MeasureRecordRemoteDataSource
    ) : MeasurmentRepository = MeasurementRepositoryImpl( measureRecordRemoteDataSource )

}