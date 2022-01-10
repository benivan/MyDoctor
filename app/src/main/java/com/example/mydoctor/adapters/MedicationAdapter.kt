package com.example.mydoctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.data.Medication
import com.example.mydoctor.databinding.MedicationItemBinding

class MedicationAdapter(
    private var medications: List<Medication>
) : RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicationAdapter.MedicationViewHolder {
        val binding =
            MedicationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicationAdapter.MedicationViewHolder, position: Int) {
        val currentItem = medications[position]
        holder.bind(currentItem)
    }

    fun submitList(list: List<Medication>) {
        medications = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return medications.size
    }

    inner class MedicationViewHolder(private val binding: MedicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Medication) {
            binding.medication = currentItem.name
            binding.dose = currentItem.dose
            binding.strength = currentItem.strength
        }
    }

}