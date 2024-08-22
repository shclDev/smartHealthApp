package com.shcl.smarthealth.domain.model.remote.dashboard

data class OverallResponse(
    val bloodGlucoseCommentInfo: BloodGlucoseCommentInfo,
    val bloodGlucoseInfo: BloodGlucoseInfo,
    val bloodPressureCommentInfo: BloodPressureCommentInfo,
    val bloodPressureDiastolicInfo: BloodPressureDiastolicInfo,
    val bloodPressureSystolicInfo: BloodPressureSystolicInfo,
    val completedSurveyAnswerInfo: CompletedSurveyAnswerInfo,
    val createdDate: String,
    val exerciseRecommendInfo: ExerciseRecommendInfo,
    val heartRateCommentInfo: HeartRateCommentInfo,
    val heartRateInfo: HeartRateInfo,
    val heartRateVariabilityInfo: HeartRateVariabilityInfo,
    val heightInfo: HeightInfo,
    val lastModifiedDate: String,
    val nutritionalRecommendInfo: NutritionalRecommendInfo,
    val stepsInfo: StepsInfo,
    val weightInfo: WeightInfo
)