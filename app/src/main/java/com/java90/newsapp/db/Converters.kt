package com.java90.newsapp.db

import androidx.room.TypeConverter
import com.java90.newsapp.models.Source

class Converters {

    // I only want to get name.
    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}