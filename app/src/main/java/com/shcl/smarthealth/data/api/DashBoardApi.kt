package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import retrofit2.http.GET

interface DashBoardApi {

    @GET("/dashboard/nutrition")
    suspend fun getNutritionAdvice() : Result<String>
    @GET("/dashboard/exercise")
    suspend fun getExerciseAdvice() : Result<String>
    @GET("/personal/health/dashboard/overall")
    suspend fun getDashboardAllData() : ApiResponse<OverallResponse?>
}