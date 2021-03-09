package com.picpay.desafio.android.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.ui.UserListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactItemFragmentTest {
    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<UserListFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
    }
}