package com.picpay.desafio.android.repository

import androidx.lifecycle.Transformations
import com.picpay.desafio.android.data.remote.PicPayApiService
import com.picpay.desafio.android.data.local.ContactUserCache
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.ContactUserDao
import com.picpay.desafio.android.mapper.asContactUserList
import com.picpay.desafio.android.mapper.asModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await


class ContactUserRepository(private val database: AppDatabase, private  val picPayApiService: PicPayApiService){

    private var dao: ContactUserDao = database.contactUserDao()

    fun  getContactUserList() = Transformations.map(dao.getAll()) {
        it.asModelList()
    }

    suspend fun refreshContactUserList(){
        withContext(Dispatchers.IO){
            val contactUserList = PicPayApiService.create().getUsers().await()
            saveOnDatabase(contactUserList.asContactUserList())
        }
    }

    private suspend fun saveOnDatabase(data:List<ContactUserCache>){
        dao.insertAll(data)
    }
}