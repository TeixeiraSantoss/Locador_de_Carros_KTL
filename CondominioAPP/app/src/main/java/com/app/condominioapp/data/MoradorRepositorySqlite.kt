package com.app.condominioapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoradorRepositorySqlite
    @Inject constructor(val moradorDAO: MoradorDAO) : MoradorRepository{
    override val moradores: Flow<List<Morador>>
        get() = moradorDAO.listar()

    override suspend fun salvar(morador: Morador) {
        if (morador.id == 0){
            moradorDAO.inserir(morador)
        } else {
            moradorDAO.atualizar(morador)
        }
    }

    override suspend fun excluir(morador: Morador) {
        moradorDAO.excluir(morador)
    }

    override suspend fun excluirTodos() {
        moradorDAO.excluirTodos()
    }
}
