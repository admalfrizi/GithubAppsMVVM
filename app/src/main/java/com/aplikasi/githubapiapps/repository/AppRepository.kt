package com.aplikasi.githubapiapps.repository

import com.aplikasi.githubapiapps.data.RemoteDataSource
import com.aplikasi.githubapiapps.util.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class AppRepository @Inject constructor(private val rmtData : RemoteDataSource) {

    fun getUsers() = flow {
        emit(Resource.loading(null))
        rmtData.getUsers().let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            } else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
            e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getUserDetails(name: String?) = flow {
        emit(Resource.loading(null))

        rmtData.getUserDetails(name).let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }
            else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
        e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getRepos() = flow {
        emit(Resource.loading(null))
        rmtData.getRepos().let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }
            else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
            e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getUserRepos(nameUser: String) = flow {
        emit(Resource.loading(null))
        rmtData.getUserRepos(nameUser).let {
            if (it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }
            else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
        e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getUserFollowers(nameUser: String) = flow{
        emit(Resource.loading(null))
        rmtData.getUserFollowers(nameUser).let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
            e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getUserFollowings(nameUser: String) = flow{
        emit(Resource.loading(null))
        rmtData.getUserFollowings(nameUser).let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
            e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }

    fun getSearchingUsers(nameQuery: String) = flow {
        emit(Resource.loading(null))
        rmtData.getSearchUsers(nameQuery).let {
            if(it.isSuccessful){
                val result = it.body()
                emit(Resource.success(result))
            }else {
                emit(Resource.error(it.message(), null))
            }
        }
    }.catch {
            e -> emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
    }
}