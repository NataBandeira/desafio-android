package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.local.ContactUserCache
import com.picpay.desafio.android.entities.ContactUserEntity
import com.picpay.desafio.android.data.remote.UserProperty


fun List<ContactUserCache>.asModelList(): List<ContactUserEntity> {
    return map {
        ContactUserEntity(
            id = it.id,
            name = it.name,
            img = it.img,
            username = it.username
        )
    }
}

fun List<UserProperty>.asContactUserList(): List<ContactUserCache> {
    return map {
        ContactUserCache(
            id = it.id,
            name = it.name,
            img = it.img,
            username = it.username
        )
    }
}

fun List<UserProperty>.asContactUserModelList(): List<ContactUserEntity> {
    return map {
        ContactUserEntity(
            id = it.id,
            name = it.name,
            img = it.img,
            username = it.username
        )
    }
}


