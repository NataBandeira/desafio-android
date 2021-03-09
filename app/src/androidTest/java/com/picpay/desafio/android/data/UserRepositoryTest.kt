package com.picpay.desafio.android.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.ContactUserDao
import com.picpay.desafio.android.data.remote.PicPayApiService
import com.picpay.desafio.android.repository.ContactUserRepository
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var contactUserRepository: ContactUserRepository
    lateinit var userApi: PicPayApiService
    lateinit var userDao: ContactUserDao

    private lateinit var database: AppDatabase

    @Before
    @Throws(Exception::class)
    fun setUp() = runBlocking {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userApi = PicPayApiService.create()
        userDao = database.contactUserDao()

        contactUserRepository = ContactUserRepository(database, userApi)
        contactUserRepository.refreshContactUserList()
    }

    @Test
    @Throws(Exception::class)
    fun getDataRepository() = runBlocking{
        var expectedSize = 33
        var contacts = contactUserRepository.getContactUserList().blockingObserve()
        assertNotNull("contact List Is Null", contacts?.size)
        MatcherAssert.assertThat(
            contacts?.size,
            CoreMatchers.equalTo(expectedSize)
        )
    }

    @After
    fun tearDown() {
        database!!.close()
    }
}