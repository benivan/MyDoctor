package com.example.mydoctor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydoctor.adapters.LabAdapter
import com.example.mydoctor.adapters.MedicationAdapter
import com.example.mydoctor.databinding.FragmentDiseaseDetailsPageBinding
import com.example.mydoctor.util.Resource
import com.example.mydoctor.viewmodels.DiseaseDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DiseaseDetailsPage : Fragment() {

    private var _binding: FragmentDiseaseDetailsPageBinding? = null
    private val binding: FragmentDiseaseDetailsPageBinding get() = _binding!!

    private val args by navArgs<DiseaseDetailsPageArgs>()

    private val viewModel: DiseaseDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDiseaseDetailsPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerLabs = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        binding.labsRecyclerView.layoutManager = layoutManagerLabs

        val layoutManagerMedicationRecyclerView = LinearLayoutManager(requireContext())

        binding.medicationRecyclerView.layoutManager = layoutManagerMedicationRecyclerView

        val labAdapter = LabAdapter(emptyList())
        val medicationAdapter = MedicationAdapter(emptyList())

        binding.labsRecyclerView.adapter = labAdapter
        binding.medicationRecyclerView.adapter = medicationAdapter

        viewModel.getLabsFromId(args.diseaseId)
        viewModel.getMedicationFromId(args.diseaseId)

        viewModel.labs.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Failure -> {

                }
                is Resource.Success -> {
                    Log.d(TAG, "onViewCreated: ${it.data}")
                    labAdapter.submitList(it.data)
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.medication.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Failure -> {

                }
                is Resource.Success -> {
                    Log.d(TAG, "onViewCreated: ${it.data}")
                    medicationAdapter.submitList(it.data)
                }
            }
        }.launchIn(lifecycleScope)
    }


    companion object {
        private const val TAG = "DiseaseDetailsPage"
    }
}