package com.app.condominioapp.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MoradorRepositoryFirebase @Inject constructor(
    private val produtosRef : CollectionReference) : MoradorRepository {

        private var _moradores = MutableStateFlow(listOf<Morador>())
        override val moradores: Flow<List<Morador>> = _moradores.asStateFlow()

        init {
            produtosRef.addSnapshotListener{snapshot, _ ->
                if (snapshot != null){
                    var moradores = mutableListOf<Morador>()
                    snapshot.documents.forEach {doc ->
                        val morador = doc.toObject<Morador>()
                        if (morador != null){
                            morador.docId = doc.id
                            moradores.add(morador)
                        }
                    }
                    _moradores.value = moradores
                } else {
                    _moradores = MutableStateFlow(listOf())
                }
            }
        }

        override suspend fun salvar(morador: Morador) {
            if (morador.docId.isNullOrEmpty()){
                var doc = produtosRef.document()
                morador.docId = doc.id
                doc.set(morador)
            } else {
                produtosRef.document(morador.docId).set(morador)
            }
        }

        override suspend fun excluir(morador: Morador) {
            produtosRef.document(morador.docId).delete()
        }

        override suspend fun excluirTodos() {
            _moradores.collect(){moradores ->
                moradores.forEach{morador ->
                    produtosRef.document(morador.docId).delete()
                }
            }
        }
}