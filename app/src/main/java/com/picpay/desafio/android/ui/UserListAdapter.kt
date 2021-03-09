package com.picpay.desafio.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.entities.ContactUserEntity

class UserListAdapter : ListAdapter<ContactUserEntity, UserListAdapter.ViewHolder>(UserPropertyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userListItem = getItem(position)
        holder.bind(userListItem)
    }

    class ViewHolder private constructor(private val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(userPropertyItem: ContactUserEntity){
            binding.userContact = userPropertyItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class UserPropertyDiffCallback: DiffUtil.ItemCallback<ContactUserEntity>(){
    override fun areItemsTheSame(oldItem: ContactUserEntity, newItem: ContactUserEntity): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: ContactUserEntity, newItem: ContactUserEntity): Boolean {
        return oldItem == newItem
    }
}



//class UserListAdapter : ListAdapter<UserProperty, UserListAdapter.ViewHolder>(UserPropertyDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val userListItem = getItem(position)
//        Log.i("Teste", userListItem.name)
//        holder.bind(userListItem)
//    }
//
//    class ViewHolder private constructor(private val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root){
//
//        fun bind(userPropertyItem: UserProperty){
//            binding.userContact = userPropertyItem
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//
//}
//
//class UserPropertyDiffCallback: DiffUtil.ItemCallback<UserProperty>(){
//    override fun areItemsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
//        return newItem.id == oldItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
//        return oldItem == newItem
//    }
//}

//var users = emptyList<User>()
//    set(value) {
//        val result = DiffUtil.calculateDiff(
//            UserListDiffCallback(
//                field,
//                value
//            )
//        )
//        result.dispatchUpdatesTo(this)
//        field = value
//    }
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
//    val view = LayoutInflater.from(parent.context)
//        .inflate(R.layout.list_item_user, parent, false)
//
//    return UserListItemViewHolder(view)
//}
//
//override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
//    holder.bind(users[position])
//}
//
//override fun getItemCount(): Int = users.size