package com.example.mydoctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.data.Lab
import com.example.mydoctor.databinding.LabsItemBinding

class LabAdapter(
    private var labs: List<Lab>
) : RecyclerView.Adapter<LabAdapter.LabViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabAdapter.LabViewHolder {
        val binding = LabsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LabAdapter.LabViewHolder, position: Int) {
        val currentItem = labs[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return labs.size
    }

    fun submitList(list: List<Lab>) {
        labs = list
        notifyDataSetChanged()
    }


    inner class LabViewHolder(private val binding: LabsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Lab) {
            binding.testName = currentItem.test
            binding.report = currentItem.report
        }
    }

}