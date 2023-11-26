package com.durov.solutions.manager.db

import androidx.room.TypeConverter
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Solution
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SolutionTypeConverter {

    @TypeConverter
    fun factorListToJson(value: List<Factor>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToFactorList(value: String) = Gson().fromJson(value, Array<Factor>::class.java).toList()

    @TypeConverter
    fun solutionListToJson(value: List<Solution>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToSolutionList(value: String) =
        Gson().fromJson(value, Array<Solution>::class.java).toList()

    @TypeConverter
    fun factorRateToStr(rate: Map<Long, Int>): String =
        Gson().toJson(rate)

    @TypeConverter
    fun jsonToFactorRate(value: String): Map<Long, Int> {
        val typeToken = object : TypeToken<Map<Long, Int>>() {}
        return Gson().fromJson(value, typeToken)
    }

}
