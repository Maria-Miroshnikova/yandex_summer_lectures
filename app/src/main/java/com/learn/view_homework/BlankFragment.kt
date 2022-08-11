package com.learn.view_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onStart() {
        super.onStart()

        val button = view?.findViewById<Button>(R.id.back_button)
        button?.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {
                (activity as MainActivity).navController?.navigate(R.id.action_blankFragment_to_todoListFragment)
            }

        })
    }
}