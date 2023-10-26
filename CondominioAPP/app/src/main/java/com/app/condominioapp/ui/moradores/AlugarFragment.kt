package com.app.condominioapp.ui.moradores

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador
import com.app.condominioapp.databinding.FragmentAlugarBinding

class AlugarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : MoradorViewModel by activityViewModels()
        val binding = FragmentAlugarBinding.inflate((layoutInflater))

        val morador = viewModel.morador
        binding.showMarca.setText(morador.marca)
        binding.showModelo.setText(morador.modelo)
        binding.showAno.setText(morador.ano.toString())

        binding.btnContinue.setOnClickListener {
            if (binding.checkSim.isChecked){
                morador.alugado = true
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Veículo alugado com sucesso")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        toHome()
                    }
                builder.create().show()
            } else if (binding.checkNao.isChecked){
                morador.alugado = false
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Veículo não foi alugado")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        toHome()
                    }
                builder.create().show()
            }
            viewModel.morador = morador
            viewModel.salvar()
        }

        return binding.root
    }

    private fun toHome() {
        val action = AlugarFragmentDirections.actionAlugarFragmentToNavHome()
        findNavController().navigate(action)
    }
}