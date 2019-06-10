package co.com.ceiba.mobile.pruebadeingreso.view.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.exts.inflate

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    var data = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder =
            UserHolder(parent.inflate(R.layout.user_list_item))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) =
            holder.bind(data[position])

    class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: UserListItemBinding = DataBindingUtil.bind(view)!!
        fun bind(user: User) {
            binding.user = user
        }
    }
}