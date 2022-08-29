package com.learn.view_homework.dagger

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learn.view_homework.repository.room.AppRoomDatabase
import com.learn.view_homework.repository.room.RepositoryRoom
import com.learn.view_homework.repository.room.TodoItemDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppRoomDatabaseModule() {

    @Provides
    @Singleton
    fun provideRoomDatabase(context : Context) : AppRoomDatabase {
        kotlin.synchronized(this) {
            return Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java,
                "todo_database"
            ).build()
        }
    }

    @Provides
    @Singleton
    fun provideTodoItemDao(context: Context) : TodoItemDao {
        return provideRoomDatabase(context).todoItemDao()
    }
}