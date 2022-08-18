package com.learn.view_homework.repository.room

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        if (value != null) {
            return Date(value)
        }
        return null
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        if (date != null)
            return date?.time?.toLong()
        return null
    }
}
