package com.example.insuranceapp.presentation.ui.userpolicies

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.example.insuranceapp.R

class UserPoliciesAdapter(private val userPolicies: UserPolicies) :RecyclerView.Adapter<UserPoliciesAdapter.MyViewHolder>(){

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val editButton : ImageView = view.findViewById(R.id.editImageView)
        val downloadButton : ImageView = view.findViewById(R.id.downloadImage)
        val deleteButton : ImageView = view.findViewById(R.id.deleteImage)
        val checkAccess:TextView = view.findViewById(R.id.checkAccess)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_policies_recyclerview_item_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.editButton.setOnClickListener{
            userPolicies.onClickOpenEdit(position)
        }
        holder.checkAccess.setOnClickListener {
            userPolicies.checkUserAccess()
        }
    }

    override fun getItemCount(): Int {
      return 10
    }

    interface OnClickCardView{
        fun onClickOpenEdit(position: Int)
        fun checkUserAccess()
    }
}