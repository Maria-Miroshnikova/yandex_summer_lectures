package com.learn.view_homework.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class UpdateRepositoryWorker(context: Context, params: WorkerParameters ) : Worker(context, params) {
    override fun doWork(): Result {
        try{
            /*TODO:
            appRepository.updateDB()
            *
            *
            * */
        }
        catch (ex: Exception){
            return Result.failure()
        }
        return Result.success()
    }
}