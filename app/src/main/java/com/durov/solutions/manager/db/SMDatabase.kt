package com.durov.solutions.manager.db

import androidx.room.Database
import androidx.room.TypeConverters
import com.durov.solutions.manager.db.entity.SubjectEntity

@Database(entities = [SubjectEntity::class], version = 1, exportSchema = false)
abstract class SMDatabase {
}