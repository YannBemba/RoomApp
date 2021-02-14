package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.model.User
import com.example.roomapp.databinding.CustomRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()

    class ListViewHolder(binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root) {
        var idListViewHolder = binding.idList
        var nomListViewHolder = binding.nomList
        var prenomListViewHolder = binding.prenomList
        var ageListViewHolder = binding.ageList
        var rowLayout = binding.rowLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(CustomRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.idListViewHolder.text = currentItem.id.toString()
        holder.nomListViewHolder.text = currentItem.nom
        holder.prenomListViewHolder.text = currentItem.prenom
        holder.ageListViewHolder.text = currentItem.age.toString()
        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = userList.size

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}