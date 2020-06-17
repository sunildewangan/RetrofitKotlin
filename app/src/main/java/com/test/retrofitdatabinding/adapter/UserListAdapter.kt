package com.test.retrofitdatabinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.retrofitdatabinding.R
import com.test.retrofitdatabinding.model.Data
import kotlinx.android.synthetic.main.item_user.view.*

public class UserListAdapter(var users:ArrayList<Data>, var context:Context):
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


     fun updateUsers(newUsers: ArrayList<Data>){
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =  UserListAdapter.UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))



    override fun getItemCount(): Int {
       return users.count()
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        holder.bind(users.get(position),context)
    }

    class UserViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val userName = view.name
        private val userEmail = view.email
        fun bind(country:Data, context: Context){
            userName.text = country.userId .toString()
            userEmail.text = country.title
        }
    }

}