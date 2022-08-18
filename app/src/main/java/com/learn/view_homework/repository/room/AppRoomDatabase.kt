package com.learn.view_homework.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learn.view_homework.data_models.TodoItem
import kotlinx.coroutines.internal.synchronized

@TypeConverters(DateConverter::class)
@Database(entities = [TodoItem::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao

    companion object {
        var INSTANCE: AppRoomDatabase? = null

        fun getAppDataBase(context: Context): AppRoomDatabase {
            return INSTANCE ?: kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}