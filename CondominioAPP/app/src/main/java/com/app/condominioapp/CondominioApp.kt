package com.app.condominioapp

import android.app.Application
import android.content.Context
import com.app.condominioapp.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class CondominioApp : Application(){

    @Provides
    fun provideProdutoRef(): CollectionReference {
        return Firebase.firestore.collection("produtos")
    }

    @Provides
    fun provideMoradorRepositoryFirebase(produtoRef : CollectionReference)
        : MoradorRepository{
        return MoradorRepositoryFirebase(produtoRef)
    }

//    @Provides
//    fun provideMoradorRepositorySqlite(moradorDAO: MoradorDAO) : MoradorRepository{
//        return MoradorRepositorySqlite(moradorDAO)
//    }

    @Provides
    fun provideMoradorDAO(bancoSQLite: BancoSQLite) : MoradorDAO{
        return bancoSQLite.moradorDAO()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx : Context): BancoSQLite{
        return BancoSQLite.get(ctx)
    }
}