package com.tinne14.testprojectsuitmedia.ui.third

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinne14.testprojectsuitmedia.Constant
import com.tinne14.testprojectsuitmedia.data.DataItem
import com.tinne14.testprojectsuitmedia.databinding.ItemUserBinding

class UserAdapter :
    PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder (val binding: ItemUserBinding) : RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(data: DataItem){
            Glide.with(itemView)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivAvatar)
            binding.apply {
                tvFullName.text = "${data.firstName} ${data.lastName}"
                tvEmail.text = data.email
            }
            itemView.setOnClickListener {
                Constant.fullname = "${data.firstName} ${data.lastName}"
                Toast.makeText(itemView.context, "Selected User: ${data.firstName} ${data.lastName}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        data.let {
            if (data != null) {
                holder.bind(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}