package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.util.inflate
import io.reactivex.subjects.PublishSubject

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var data = listOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val showPost = PublishSubject.create<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                0 -> UserHolder(parent.inflate(R.layout.user_list_item))
                else -> EmptyViewHolder(parent.inflate(R.layout.empty_view))
            }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (holder) {
                is UserHolder -> holder.bind(data[position] as User, showPost)
                else -> Unit
            }

    override fun getItemViewType(position: Int): Int =
            when (data[position]) {
                is User -> 0
                else -> 1
            }

    class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: UserListItemBinding = DataBindingUtil.bind(view)!!
        fun bind(user: User, showPost: PublishSubject<User>) {
            binding.user = user
            binding.showPost = showPost
        }
    }

    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}