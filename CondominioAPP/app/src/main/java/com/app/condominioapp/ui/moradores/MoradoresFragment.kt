package com.app.condominioapp.ui.moradores

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import com.app.condominioapp.R
import com.app.condominioapp.data.MoradorRepository
import com.app.condominioapp.databinding.FragmentListMoradoresBinding
import com.app.condominioapp.ui.moradores.placeholder.PlaceholderContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoradoresFragment : Fragment() {

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : MoradorViewModel by activityViewModels()

        val binding = FragmentListMoradoresBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.moradores.collect{moradores->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = MoradoresAdapter(moradores, viewModel)
                }
            }
        }
        return binding.root
    }
}