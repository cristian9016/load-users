package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.models.Post
import co.com.ceiba.mobile.pruebadeingreso.util.inflate
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    var data = listOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder =
            PostHolder(parent.inflate(R.layout.post_list_item))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) =
            holder.bind(data[position])

    class PostHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post) {
            view.title.text = post.title
            view.body.text = post.body
        }
    }
}