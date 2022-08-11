package com.learn.view_homework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.data_models.TodoItemRepository
import com.learn.view_homework.list_design.TodoListAdapter
import com.learn.view_homework.view_models.TodoListViewModel
import kotlin.math.log

class TodoListFragment : Fragment() {

    interface OnChangeTodoItemStatusListener{
        abstract fun onChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        //Log.i("WHERE:", "ListFragment onStart")
        val viewModel = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)

        // ATTENTION: if I want the same instance of LiveData, I must call Provider with the same OWNER!
        val todoItemToEdit = viewModel.itemToEdit

        // TODO: WTF почему observe вызывается заново??
        todoItemToEdit.observe(this, object: Observer<Int>{
            override fun onChanged(t: Int?) {
                if (viewModel.itemToEditWasUpdated) {
                    viewModel.itemToEditWasUpdated = false
                  //  val kek = viewModel.itemToEdit.value
                    val action =
                        TodoListFragmentDirections.actionTodoListFragmentToNewOrEditTodoItemFragment(
                            false
                        )
                    //Log.i("WHERE:", "ListFragment action to Edit")
                    (activity as MainActivity).navController?.navigate(action)
                }
            }
        })

        val floatingActionButton = view?.findViewById<FloatingActionButton>(R.id.add_new_todo_button)
        floatingActionButton?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val action = TodoListFragmentDirections.actionTodoListFragmentToNewOrEditTodoItemFragment(true)
                (activity as MainActivity).navController?.navigate(action)
                //Log.i("WHERE:", "ListFragment action to Create")
                //(activity as MainActivity).navController?.navigate(R.id.action_todoListFragment_to_blankFragment)
            }
        })

        val countOfDone = viewModel.countDoneItems
        val countOfDoneTextView = view?.findViewById<TextView>(R.id.count_of_done_textview)
        countOfDone.observe(this, object : Observer<Int>{
            override fun onChanged(t: Int?) {
                countOfDoneTextView?.text = activity?.resources?.getString(R.string.count_item_done_text) + t.toString()
            }
        })

        val adapter = TodoListAdapter(viewModel, context)
        adapter.setData(viewModel.repository.getTodoList())

        val recyclerView : RecyclerView? = view?.findViewById<RecyclerView>(R.id.todo_list_recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}


// а адаптер реально будет обновляться? зачем вообще так делать?