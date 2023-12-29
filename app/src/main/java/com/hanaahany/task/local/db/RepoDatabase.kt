package com.hanaahany.task.local.db

import android.content.Context
import androidx.room.*
import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.utils.Constants.DATA_BASE

@Database(entities = [RepoDetailsEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDAO

    companion object {
        @Volatile
        private var instance: RepoDatabase? = null

        fun getRepoDetailsDataBaseInstance(context: Context): RepoDatabase {
            return instance ?: synchronized(this) {
                val instanceHolder = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java, DATA_BASE
                ).build()
                instance = instanceHolder
                instanceHolder

            }
        }
    }
}