package com.whats2ps.data

import android.content.Context
import androidx.room.*

@Database(entities = [MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "whats2ps_db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}
