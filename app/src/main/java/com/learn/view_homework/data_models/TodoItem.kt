package com.learn.view_homework.data_models

import java.util.*

data class TodoItem(var text: String, var importenceStatus: IMPORTANCE_STATUS,  val creationDate : Date?) {

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

    var id: String = ""

    var updatingDate : Date? = null

    var deadline: Date? = null

    var isDone: Boolean = false

//    init {
 //       id = (1000..10000).random().toString()
  //  }

}