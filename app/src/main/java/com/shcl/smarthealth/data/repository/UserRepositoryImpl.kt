package com.shcl.smarthealth.data.repository

import android.util.Log
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource
) : UserRepository{

    override suspend fun signCheck(): Flow<ApiResponse<String>> {
       return userRemoteDataSource.signCheck()
    }

    override suspend fun signUp(request: SignUpRequest): Flow<SignUpResponse?> {
        return flow{
            userRemoteDataSource.signUp(request).collect{
                it?.let {
                    if(it.success){
                        it.data?.let { response->
                            emit(
                                SignUpResponse(
                                    type = response.type,
                                    token = response.token,
                                    id = response.id,
                                    name = response.name,
                                    authCode = response.authCode
                                )
                            )
                        }?:run{
                            emit(null)
                        }
                    }else{
                        emit(null)
                    }
                }
            }
        }
    }

    override suspend fun userRoomUpdate(userRoom: UserRoom) {

        try{
            measureRecordDataSource.updateUser(userRoom)

        }catch (e : Exception){
            Log.e("register" , e.message.toString())
        }
    }

    override suspend fun lastedLoginUserRoomUpdate(lastedLoginUserRoom: LastedLoginUserRoom) {
        try{

            measureRecordDataSource.updateLastedLoginUser(lastedLoginUserRoom)

        }catch(e : Exception){
            Log.e("register" , e.message.toString())
        }
    }

    override suspend fun getLastedLoginUserFromRoom(): Flow<LastedLoginUserRoom> {
        return flow{
            measureRecordDataSource.getLastedLoginUser()
        }
    }

    override suspend fun getAllUser(): Flow<List<UserRoom>> = flow{
            measureRecordDataSource.getAllUser().collect{
                Log.d("smarthealth" , it.toString())
                emit(it)
            }
    }

    override suspend fun signIn(request: SignInRequest): Flow<ApiResponse<SignInResponse?>> {
        return flow{
            userRemoteDataSource.signIn(request).collect{
                emit(it)
            }
        }
    }

    override suspend fun userProfile(): Flow<ProfileResponse?> {
        return flow{
            userRemoteDataSource.userProfile().collect{
                it?.let {
                    emit(it.data)
                }
            }
        }
    }

    override suspend fun userProfilePicture(): Flow<String?> {
        return flow{
            userRemoteDataSource.userProfilePicture().collect{
                it?.let {
                    emit(it.data)
                }
            }
        }
    }

}