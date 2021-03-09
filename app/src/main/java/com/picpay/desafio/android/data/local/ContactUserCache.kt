package com.picpay.desafio.android.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_users_table")
data class ContactUserCache(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "contact_name")
    val name: String,
    @ColumnInfo(name = "contact_img")
    val img: String,
    @ColumnInfo(name = "contact_username")
    val username: String)
