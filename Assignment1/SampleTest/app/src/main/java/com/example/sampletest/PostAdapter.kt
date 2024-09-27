package com.example.sampletest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    var posts: List<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val post_author_name: Button = itemView.findViewById(R.id.post_authorName)
        val post_image: ImageView = itemView.findViewById(R.id.post_image)
        val post_like_count: TextView = itemView.findViewById(R.id.post_like_count)
        val post_description: TextView = itemView.findViewById(R.id.post_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            post_author_name.text = posts[position].author
            post_image.setImageResource(posts[position].image)
            post_like_count.text = posts[position].numOfLikes.toString()
            post_description.text = posts[position].description
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}