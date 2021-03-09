package com.picpay.desafio.android.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.picpay.desafio.android.data.remote.PicPayApiService
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.entities.ContactUserEntity
import com.picpay.desafio.android.repository.ContactUserRepository
import kotlinx.coroutines.*

enum class ApiStatus {
    LOADING,
    ERROR,
    DONE
}

class UserListViewModel(private val contactUserRepository: ContactUserRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    init {
        _status.value = ApiStatus.LOADING
    }

    fun contactUserList(): LiveData<List<ContactUserEntity>> {
        updateData()
        return contactUserRepository.getContactUserList()
    }

    private fun updateData() {
        coroutineScope.launch {
            try {
                contactUserRepository.refreshContactUserList()
            } catch (e: Exception) {
                Log.i("Teste", "ApiStatus.ERROR")
                _status.value = ApiStatus.ERROR
            }
        }

    }

    fun updateStatus(list: List<ContactUserEntity>) {
        if (!list.isNullOrEmpty()) {
            Log.i("Teste", "ApiStatus.DONE")
            _status.value = ApiStatus.DONE
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserListViewModel(
                    ContactUserRepository(
                        AppDatabase.getInstance(app),
                        PicPayApiService.create()
                    )
                ) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
