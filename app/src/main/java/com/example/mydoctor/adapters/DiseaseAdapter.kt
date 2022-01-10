package com.example.mydoctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.HomePage
import com.example.mydoctor.HomePageDirections
import com.example.mydoctor.data.Disease
import com.example.mydoctor.databinding.DiseaseItemBinding

class DiseaseAdapter(
    private var diseases: List<Disease>
) : RecyclerView.Adapter<DiseaseAdapter.DiseaseItemViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiseaseItemViewHolder {
        val binding = DiseaseItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DiseaseItemViewHolder(binding)
    }
    fun submitList(list: List<Disease>) {
        diseases = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DiseaseItemViewHolder, position: Int) {
        val currentItem = diseases[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return diseases.size
    }


    inner class DiseaseItemViewHolder(private val binding: DiseaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Disease){
            binding.diseaseTV.text = currentItem.name
            binding.diseaseTV.setOnClickListener { view ->
                val action = HomePageDirections.actionHomePageToDiseaseDetailsPage(currentItem.diseaseId)
                view.findNavController().navigate(action)
            }
        }

    }


}