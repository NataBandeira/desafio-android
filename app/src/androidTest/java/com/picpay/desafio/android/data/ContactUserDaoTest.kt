package com.picpay.desafio.android.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.ContactUserCache
import com.picpay.desafio.android.data.local.ContactUserDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactUserDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ContactUserDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        dao = database.contactUserDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDataFromDatabase() = runBlocking{
        val contatos = listOf<ContactUserCache>(
            ContactUserCache(
                name = "User 01",
                username = "Username 01",
                img = "http://teste.com/1809",
                id = 1002
            ),
            ContactUserCache(
                name = "User 02",
                username = "Username 02",
                img = "http://teste.com/1809",
                id = 1003
            ),
            ContactUserCache(
                name = "User 03",
                username = "Username 03",
                img = "http://teste.com/1809",
                id = 1004
            )
        )
        dao.insertAll(contatos)
        val byName = dao.getAll().blockingObserve()

        assertThat("Quantidade de " + byName?.size + " -> " + contatos.size ,
            byName?.size,
            equalTo(contatos.size)
        )
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}