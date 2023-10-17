package com.durov.solutions.manager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.durov.solutions.manager.db.entity.FactorEntity

@Dao
abstract class FactorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addFactor(factor: FactorEntity): Long

    @Delete
    abstract suspend fun deleteFactor(factor: FactorEntity)

    @Query("SELECT * FROM factor WHERE id =:id")
    abstract suspend fun getById(id: Long): FactorEntity

    @Query("SELECT * FROM factor WHERE subject_id =:subjectId")
    abstract suspend fun getBySubjectId(subjectId: Long): List<FactorEntity>

}
