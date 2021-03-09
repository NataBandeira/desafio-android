package com.picpay.desafio.android.data.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContactUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contactUsers: List<ContactUserCache>)

    @Query("SELECT * FROM contact_users_table")
    fun getAll(): LiveData<List<ContactUserCache>>
}
