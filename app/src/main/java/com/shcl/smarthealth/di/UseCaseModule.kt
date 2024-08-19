package com.shcl.smarthealth.di

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.domain.repository.NCloudRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.repository.SurveyRepository
import com.shcl.smarthealth.domain.repository.UserRepository
import com.shcl.smarthealth.domain.usecase.dashboard.DashBoardUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetBloodPressureDBUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetBodyCompositionDBUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetGlucoseDBUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetNutritionAdviceUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetWeatherUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.UserImageUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.UserInfoDBUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.UserInfoServerUseCase
import com.shcl.smarthealth.domain.usecase.isens.GetGlucoseRecordUseCase
import com.shcl.smarthealth.domain.usecase.isens.IsensDeviceUseCase
import com.shcl.smarthealth.domain.usecase.isens.IsensScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.isens.SetGlucoseRecordUserCase
import com.shcl.smarthealth.domain.usecase.omron.BodyCompositionUseCase
import com.shcl.smarthealth.domain.usecase.omron.GetDataTransferUseCase
import com.shcl.smarthealth.domain.usecase.omron.GetDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.RegisterDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.ScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.SetBloodPressureUseCase
import com.shcl.smarthealth.domain.usecase.survey.CompleteSurveyUseCase
import com.shcl.smarthealth.domain.usecase.survey.GetCategoryQuestionUseCase
import com.shcl.smarthealth.domain.usecase.survey.GetSurveyInfoUseCase
import com.shcl.smarthealth.domain.usecase.survey.SetCategoryAnswerUseCase
import com.shcl.smarthealth.domain.usecase.survey.StartSurveyUseCase
import com.shcl.smarthealth.domain.usecase.survey.SurveyUseCase
import com.shcl.smarthealth.domain.usecase.user.LastedLoginUserRoomUpdateUseCase
import com.shcl.smarthealth.domain.usecase.user.LastedLoginUserUseCase
import com.shcl.smarthealth.domain.usecase.user.LoggedUserUseCase
import com.shcl.smarthealth.domain.usecase.user.UserProfileUseCase
import com.shcl.smarthealth.domain.usecase.user.UserRoomUpdateUseCase
import com.shcl.smarthealth.domain.usecase.user.UserSignCheckUseCase
import com.shcl.smarthealth.domain.usecase.user.UserSignInUseCase
import com.shcl.smarthealth.domain.usecase.user.UserSignUpUseCase
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoicePlayUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceTTSUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideDashBoardUseCases(dashBoardRepository: DashBoardRepository , userRepository: UserRepository) = DashBoardUseCase(
        getNutritionAdviceUseCase = GetNutritionAdviceUseCase(repository = dashBoardRepository),
        getBloodPressureDBUseCase = GetBloodPressureDBUseCase(repository = dashBoardRepository),
        getWeightDBUseCase = GetBodyCompositionDBUseCase(repository = dashBoardRepository),
        getGlucoseDBUseCase = GetGlucoseDBUseCase(repository = dashBoardRepository),
        getWeatherUseCase = GetWeatherUseCase(repository = dashBoardRepository),
        userInfoDBUseCase = UserInfoDBUseCase(repository = dashBoardRepository),
        userInfoServerUseCase = UserInfoServerUseCase(repository = userRepository),
        userImageUseCase = UserImageUseCase(repository = userRepository)
    )

    @Provides
    fun provideOmronDeviceUseCase(omronRepository: OmronRepository) = OmronDeviceUseCase(
        scanDeviceUseCase = ScanDeviceUseCase(repository = omronRepository),
        getDataTransferUseCase = GetDataTransferUseCase(repository = omronRepository),
        setBloodPressureUseCase = SetBloodPressureUseCase(repository = omronRepository),
        registerDeviceUseCase = RegisterDeviceUseCase(repository = omronRepository),
        bodyCompositionUseCase = BodyCompositionUseCase(repository = omronRepository),
        getDeviceUseCase = GetDeviceUseCase(repository = omronRepository)
    )

    @Provides
    fun provideIsensDeviceUseCase(isensRepository: IsensRepository) = IsensDeviceUseCase(
        isensScanDeviceUseCase = IsensScanDeviceUseCase(repository = isensRepository),
        getGlucoseRecordUseCase = GetGlucoseRecordUseCase(repository = isensRepository),
        setGlucoseRecordUserCase = SetGlucoseRecordUserCase(repository = isensRepository)
    )

    @Provides
    fun provideUserUseCase(userRepository: UserRepository) = UserUseCase(
        userSignUpUseCase = UserSignUpUseCase(repository = userRepository),
        userRoomUpdateUseCase = UserRoomUpdateUseCase(repository = userRepository),
        lastedLoginUserRoomUpdateUseCase = LastedLoginUserRoomUpdateUseCase(repository = userRepository),
        userSignCheckUseCase = UserSignCheckUseCase(repository = userRepository),
        loggedUserUseCase = LoggedUserUseCase(repository = userRepository),
        userProfileUseCase = UserProfileUseCase(repository = userRepository),
        userSignInUseCase = UserSignInUseCase(repository = userRepository),
        lastedLoginUserUseCase = LastedLoginUserUseCase(repository = userRepository)
    )

    @Provides
    fun provideSurveyUseCase(surveyRepository: SurveyRepository , nCloudRepository: NCloudRepository) = SurveyUseCase(
        startSurveyUseCase = StartSurveyUseCase(repository = surveyRepository),
        completeSurveyUseCase = CompleteSurveyUseCase(repository = surveyRepository),
        getSurveysInfoUseCase = GetSurveyInfoUseCase(repository = surveyRepository),
        getCategoryQuestionUseCase = GetCategoryQuestionUseCase(repository = surveyRepository),
        setCategoryAnswerUseCase = SetCategoryAnswerUseCase(repository =    surveyRepository),
        voiceUseCase = VoiceUseCase(voicePlayUseCase = VoicePlayUseCase.INSTANCE, voiceTTSUseCase = VoiceTTSUseCase(nCloudRepository))
    )


    @Provides
    fun provideVoiceUseCase(nCloudRepository: NCloudRepository) = VoiceUseCase(
            voiceTTSUseCase = VoiceTTSUseCase(repositroy = nCloudRepository),
            voicePlayUseCase = VoicePlayUseCase.INSTANCE
    )

}