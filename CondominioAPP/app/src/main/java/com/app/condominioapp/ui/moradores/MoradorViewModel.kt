package com.app.condominioapp.ui.moradores

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.condominioapp.data.Morador
import com.app.condominioapp.data.MoradorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoradorViewModel @Inject constructor(val repository: MoradorRepository) : ViewModel(){

    var morador : Morador = Morador()

    private var _moradores = MutableStateFlow(listOf<Morador>())
    val moradores : Flow<List<Morador>> = _moradores

    init {
        viewModelScope.launch {
            repository.moradores.collect{moradores ->
                _moradores.value = moradores
            }
        }
    }

    fun novo(){
        this.morador = Morador()
    }

    fun editar(morador: Morador){
        this.morador = morador
    }

    fun salvar() = viewModelScope.launch{
        repository.salvar(morador)
    }

    fun excluir(morador: Morador) = viewModelScope.launch{
        repository.excluir(morador)
    }

}