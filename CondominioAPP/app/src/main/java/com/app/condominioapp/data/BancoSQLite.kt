package com.app.condominioapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Morador::class], version = 2, exportSchema = false)
abstract class BancoSQLite : RoomDatabase(){

    abstract fun moradorDAO(): MoradorDAO

    companion object{

        @Volatile
        private var INSTANCIA: BancoSQLite? = null

        fun get(context: Context) : BancoSQLite{
            if (INSTANCIA == null){
                INSTANCIA = Room.databaseBuilder(
                    context.applicationContext,
                    BancoSQLite::class.java,
                    "BancoSQLite.db"
                ).build()
            }
            return INSTANCIA as BancoSQLite
        }
    }
}