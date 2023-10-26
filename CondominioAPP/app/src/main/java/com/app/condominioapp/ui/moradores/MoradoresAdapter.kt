package com.app.condominioapp.ui.moradores

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import coil.load
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador

import com.app.condominioapp.ui.moradores.placeholder.PlaceholderContent.PlaceholderItem
import com.app.condominioapp.databinding.FragmentItemMoradoresBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MoradoresAdapter(private val moradores: List<Morador>, val viewModel : MoradorViewModel):
    RecyclerView.Adapter<MoradoresAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemMoradoresBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMorador = moradores[position]

        //Carregamento remoto
//        holder.imgFoto.load(R.drawable.carro1)
//        Firebase.storage.getReference(itemMorador.foto)
//            .downloadUrl.addOnSuccessListener { imageUrl ->
//                holder.imgFoto.load(imageUrl)
//            }


        holder.txtMarca.text = itemMorador.marca
        holder.txtModelo.text = itemMorador.modelo
        holder.txtAno.text = itemMorador.ano.toString()
        if (itemMorador.alugado == false){
            holder.txtAlugado.text = "Disponivel"
        } else {
            holder.txtAlugado.text = "Alugado"
        }

  //Editar
        holder.itemView.setOnClickListener{view ->
            viewModel.editar(itemMorador)
            val action = MoradoresFragmentDirections.actionNavHomeToMoradorFragment()
            view.findNavController().navigate(action)

        }

  //Adicionar opção de cadastra visita
        holder.itemView.setOnLongClickListener {view ->
            AlertDialog.Builder(view.context)
                .setMessage("O que deseja fazer?")  //MUDAR

                .setPositiveButton("Alugar"){ dialog, id -> //MUDAR
                    viewModel.editar(itemMorador)
                    val action = MoradoresFragmentDirections.actionNavHomeToAlugarFragment()
                    view.findNavController().navigate(action)
                }

                .setNegativeButton("Apagar") { dialog, id -> //MUDAR
                    viewModel.excluir(itemMorador)
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = moradores.size

    inner class ViewHolder(binding: FragmentItemMoradoresBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txtMarca: TextView = binding.txtMarca
        val txtModelo: TextView = binding.txtModelo
        val txtAno: TextView = binding.txtAno
        val txtAlugado: TextView = binding.txtAlugado
        val imgFoto: ImageView = binding.imgFoto


    }

}