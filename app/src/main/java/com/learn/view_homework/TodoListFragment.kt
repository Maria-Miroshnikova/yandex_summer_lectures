package com.learn.view_homework

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.list_design.TodoListAdapter
//import com.learn.view_homework.view_models.TodoListViewModel
import com.learn.view_homework.view_models.TodoViewModel
import com.learn.view_homework.view_models.TodoViewModelFactory

class TodoListFragment : Fragment() {

   // private val viewModel:TodoViewModel by activityViewModels<TodoViewModel>()  /*{
   //     TodoViewModelFactory()(activity?.application as MyApplication).roomRepository)
   // }*/

    private val viewModel:TodoViewModel by activityViewModels {
        TodoViewModelFactory {
            (requireActivity().application as MyApplication).myApplicationComponent.todoViewModel()
        }
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
    }

    override fun onResume() {
        super.onResume()

        val fab = view?.findViewById<FloatingActionButton>(R.id.add_new_todo_button)

        /* vector-animated-drawable expirience
        val arrowsImageView = view?.findViewById<ImageView>(R.id.arrows_image)
        val arrows_vector = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.arrows_animated_vector)
        arrowsImageView?.setImageDrawable(arrows_vector)
        arrows_vector?.start()*/

        (AnimatorInflater.loadAnimator(requireContext(), R.animator.fab_animator) as ObjectAnimator).apply {
            target = fab
            start()
        }


        val todoItemToEdit = viewModel.itemToEdit

        todoItemToEdit.observe(viewLifecycleOwner, object: Observer<TodoItem>{
            override fun onChanged(t: TodoItem?) {
                if (viewModel.itemToEditWasUpdated) {
                    viewModel.itemToEditWasUpdated = false
                    val action =
                        TodoListFragmentDirections.actionTodoListFragmentToNewOrEditTodoItemFragment(false)
                    (activity as MainActivity).navController?.navigate(action)
                }
            }
        })

        val floatingActionButton = view?.findViewById<FloatingActionButton>(R.id.add_new_todo_button)
        floatingActionButton?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val action = TodoListFragmentDirections.actionTodoListFragmentToNewOrEditTodoItemFragment(true)
                (activity as MainActivity).navController?.navigate(action)
            }
        })

        val adapter = TodoListAdapter(viewModel, context)
        val countOfDoneTextView = view?.findViewById<TextView>(R.id.count_of_done_textview)
        val recyclerView : RecyclerView? = view?.findViewById<RecyclerView>(R.id.todo_list_recyclerview)

        val todoListLiveData = viewModel.todoListLiveData
        todoListLiveData.observe(viewLifecycleOwner, object : Observer<List<TodoItem>> {
            override fun onChanged(t: List<TodoItem>?) {
                adapter.setData(t!!) // TODO: if empty?
                recyclerView?.adapter = adapter
                recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                val countOfDone = t.count { it.isDone }
                countOfDoneTextView?.text = activity?.resources?.getString(R.string.count_item_done_text) + countOfDone.toString()
            }
        })
    }
}
