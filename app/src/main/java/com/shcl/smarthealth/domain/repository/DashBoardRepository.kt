package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import kotlinx.coroutines.flow.Flow

interface DashBoardRepository{
    fun getNutritionAdvice() : Flow<String>
    fun getLastedBloodPressure()  : Flow<BloodPressureRoom>
    fun getLastedBodyComposition() : Flow<BodyCompositionRoom>
    fun getLastedGlucose() : Flow<GlucoseRecordRoom>
}
