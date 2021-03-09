package com.picpay.desafio.android.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentUserListBinding
import com.picpay.desafio.android.viewmodels.UserListViewModel


class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by lazy {
        val application = requireNotNull(this.activity).application
        ViewModelProvider(this, UserListViewModel.Factory(application)).get(UserListViewModel::class.java)
    }

    private val adapter = UserListAdapter()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val binding: FragmentUserListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.userListViewModel = viewModel
        binding.lifecycleOwner = this
        binding.contactUserList.adapter = adapter

        viewModel.contactUserList().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.i("Teste","1")
                adapter.submitList(it)
            }
            Log.i("Teste","2")
            viewModel.updateStatus(it)
        })

        return binding.root
    }
}