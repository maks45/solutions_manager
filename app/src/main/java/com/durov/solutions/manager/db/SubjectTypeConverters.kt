package com.durov.solutions.manager.db

import androidx.room.TypeConverter
import com.durov.solutions.manager.model.Factor
import com.google.gson.Gson

class SubjectTypeConverters{

    @TypeConverter
    fun listToJson(value: List<Factor>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Factor>::class.java).toList()

}
