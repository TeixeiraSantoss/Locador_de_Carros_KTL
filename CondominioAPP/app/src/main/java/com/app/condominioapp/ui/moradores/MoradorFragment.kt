package com.app.condominioapp.ui.moradores

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador
import com.app.condominioapp.databinding.FragmentMoradorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoradorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : MoradorViewModel by activityViewModels()
        val binding = FragmentMoradorBinding.inflate((layoutInflater))

        val morador = viewModel.morador
        binding.inputMarca.setText(morador.marca)
        binding.inputModelo.setText(morador.modelo)
        binding.inputAno.setText(morador.ano.toString())
       
        binding.Salvar.setOnClickListener{
            val moradorSalvar = Morador(
                morador.id,
                morador.docId,
                binding.inputMarca.text.toString(),
                binding.inputModelo.text.toString(),
                binding.inputAno.text.toString().toInt(),
                morador.alugado
            )
            viewModel.morador = moradorSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}