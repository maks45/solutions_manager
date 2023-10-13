package com.durov.solutions.manager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.durov.solutions.manager.db.entity.SubjectEntity

@Database(entities = [SubjectEntity::class], version = 1, exportSchema = false)
@TypeConverters(SubjectTypeConverters::class)
abstract class SMDatabase : RoomDatabase() {
    companion object {
        const val NAME = "solutions_manager_db"
    }

    abstract fun getSubjectsDao(): SubjectDao

}
