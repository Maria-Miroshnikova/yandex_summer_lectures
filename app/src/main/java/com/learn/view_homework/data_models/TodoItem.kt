package com.learn.view_homework.data_models

import androidx.lifecycle.GeneratedAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@Entity
@JsonClass(generateAdapter = true)
data class TodoItem(@Json(name = "text") var text: String,
                    @Json(name = "importance") var importenceStatus: IMPORTANCE_STATUS,
                    @Json(name = "created_at") val creationDate : Date?) {

    enum class IMPORTANCE_STATUS {
        LOW,
        NO,
        URGENTLY;

        // TODO: нормальная единая на всю прогу связь!
        companion object {
            fun toEnum(value: String): IMPORTANCE_STATUS? {
                when (value) {
                    "Нет" -> return NO
                    "Низкая" -> return LOW
                    "!! Высокая" -> return URGENTLY
                    else -> return null
                }
            }
        }

        fun toSting(): String {
            when (this) {
                NO -> return "Нет"
                LOW -> return "Низкая"
                URGENTLY -> return "!! Высокая"
            }
        }

        fun toInt(): Int {
            when (this) {
                NO -> return 0
                LOW -> return 1
                URGENTLY -> return 2
            }
        }
    }

    @PrimaryKey(autoGenerate = true)
    @Json(name = "id") var id: Int? = null

//    var updatingDate : Date? = null

    @Json(name = "deadline") var deadline: Date? = null

    @Json(name = "done") var isDone: Boolean = false

}