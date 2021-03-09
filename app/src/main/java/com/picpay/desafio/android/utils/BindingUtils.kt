package com.picpay.desafio.android.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.picpay.desafio.android.R
import com.picpay.desafio.android.entities.ContactUserEntity
import com.picpay.desafio.android.viewmodels.ApiStatus
import com.squareup.picasso.Picasso


@BindingAdapter("apiStatus")
fun apiStatus(progressBarView: View, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            progressBarView.visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            progressBarView.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            progressBarView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).error(R.drawable.ic_round_account_circle).into(view)
    //TODO: Tratar a progress bar
}


@BindingAdapter("setUsername")
fun TextView.setUsername(item: ContactUserEntity?){
    item?.let {
        text = item.username
    }
}

@BindingAdapter("setName")
fun TextView.setName(item: ContactUserEntity?){
    item?.let {
        text = item.name
    }
}