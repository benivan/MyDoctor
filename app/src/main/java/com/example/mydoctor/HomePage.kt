package com.example.mydoctor

import android.content.res.Configuration
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.WorkManager
import com.example.mydoctor.adapters.DiseaseAdapter
import com.example.mydoctor.databinding.FragmentHomePageBinding
import com.example.mydoctor.util.Resource
import com.example.mydoctor.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*


@AndroidEntryPoint
class HomePage : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding: FragmentHomePageBinding get() = _binding!!

    private val args by navArgs<HomePageArgs>()

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DiseaseAdapter(emptyList())

        setGreeting()

        val orientation = this.resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerview.layoutManager = layoutManager
            binding.recyclerview.adapter = adapter
        } else {
            val layoutManager = GridLayoutManager(requireContext(), 5)
            binding.recyclerview.layoutManager = layoutManager
            binding.recyclerview.adapter = adapter
        }

        binding.userNameTV.text = args.username
        viewModel.getDiseases()


        viewModel.disease.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is Resource.Failure -> {
                    binding.errorTextView.text = it.toString()
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    Log.d(TAG, "onViewCreated: ${it.data}")
                    binding.progressBar.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    adapter.submitList(it.data)
                }
            }
        }.launchIn(lifecycleScope)


    }

    private fun setGreeting() {
        val c: Calendar = Calendar.getInstance()

        val message = when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> Pair("Good Morning", R.drawable.ic_sun_summer_svgrepo_com)
            in 12..15 -> Pair("Good Afternoon", R.drawable.ic_sun_summer_svgrepo_com)
            in 16..20 -> Pair("Good Evening", R.drawable.ic_moon_phases_svgrepo_com)
            else -> Pair("Good Night", R.drawable.ic_moon_phases_svgrepo_com)
        }

        binding.greetingMessage.text = message.first
        binding.dayNightImage.setImageResource(message.second)
    }

    companion object {
        private const val TAG = "HomePage"
    }

}