package com.learn.view_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.learn.view_homework.data_models.TodoItemRepository

public class MainActivity : AppCompatActivity() {

    public var navController: NavController? = null

    fun getNavigController(): NavController? {
        return navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fragment = findViewById<FragmentContainerView>(R.id.fragment_container_view)
//        var navController =  findNavController(R.id.fragment_container_view_host)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_host) as NavHostFragment
        navController = navHostFragment.navController
    }
}