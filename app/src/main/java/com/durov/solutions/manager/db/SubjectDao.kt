package com.durov.solutions.manager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.durov.solutions.manager.db.entity.SubjectEntity

@Dao
abstract class SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addSubject(subject: SubjectEntity)

    @Query("SELECT * FROM subject")
    abstract suspend fun getAll(): List<SubjectEntity>

    @Delete
    abstract suspend fun deleteSubject(subject: SubjectEntity)

}
