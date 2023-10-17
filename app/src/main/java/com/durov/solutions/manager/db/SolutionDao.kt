package com.durov.solutions.manager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.durov.solutions.manager.db.entity.SolutionEntity

@Dao
abstract class SolutionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addSolution(solution: SolutionEntity): Long

    @Delete
    abstract suspend fun deleteSolution(solution: SolutionEntity)

    @Query("SELECT * FROM solution WHERE id =:id")
    abstract suspend fun getById(id: Long): SolutionEntity

    @Query("SELECT * FROM solution WHERE subject_id =:subjectId")
    abstract suspend fun getBySubjectId(subjectId: Long): List<SolutionEntity>

}
