package com.learn.view_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.learn.view_homework.data_models.TodoItemRepository
import com.learn.view_homework.repository.UpdateRepositoryWorker
import java.util.concurrent.TimeUnit

public class MainActivity : AppCompatActivity() {

    public var navController: NavController? = null

    fun getNavigController(): NavController? {
        return navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_host) as NavHostFragment
        navController = navHostFragment.navController

        val request = PeriodicWorkRequestBuilder<UpdateRepositoryWorker>(
            repeatInterval = 8,
            repeatIntervalTimeUnit = TimeUnit.HOURS,
            flexTimeInterval = 25,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        ).build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(request)
    }
}