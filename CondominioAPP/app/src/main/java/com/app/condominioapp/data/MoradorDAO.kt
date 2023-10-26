package com.app.condominioapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoradorDAO {

    @Query("select * from moradores")
    fun listar(): Flow<List<Morador>>

    @Insert
    suspend fun inserir(morador: Morador)

    @Update
    suspend fun atualizar(morador: Morador)

    @Delete
    suspend fun excluir(morador: Morador)

    @Query("delete from Moradores")
    suspend fun excluirTodos()

}