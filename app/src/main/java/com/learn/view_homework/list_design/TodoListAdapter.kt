package com.learn.view_homework.list_design

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learn.view_homework.R
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.view_models.TodoListViewModel
import android.text.Spannable

import android.graphics.Color

import android.text.style.ForegroundColorSpan

import android.text.SpannableString




class TodoListAdapter(val viewModel: TodoListViewModel, val context: Context?) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    var listTodo: List<TodoItem> = ArrayList<TodoItem>()

    class TodoViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView) {//,
         // View.OnClickListener  {
        val isDonecheckBox : CheckBox = itemView.findViewById(R.id.isDoneCheckBox)
        val todoTextView : TextView = itemView.findViewById(R.id.todoTextView)
/*
        init {
            isDonecheckBox.setOnClickListener(this)/*(object: View.OnClickListener{
                override fun onClick(v : View)
                {
                    onChangeTodoItemStatusListener.onChanged()
                }

            })*/
            todoTextView.setOnClickListener(this)/*(object: View.OnClickListener {
                override fun onClick(v: View?) {
                    TODO("Not yet implemented")
                }

            })*/
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }*/
    }

    override fun getItemCount(): Int {
        return listTodo.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.isDonecheckBox.isChecked = listTodo.get(position).isDone

        if (listTodo.get(position).importenceStatus != TodoItem.IMPORTANCE_STATUS.NO)
        {
            val importanceFlag: Spannable = SpannableString("!! ")

            importanceFlag.setSpan(
                ForegroundColorSpan(context!!.getColor(R.color.red)),
                0,
                importanceFlag.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.todoTextView.setText(importanceFlag)
        }

        holder.todoTextView.append(listTodo.get(position).text)

        holder.isDonecheckBox.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val idx = holder.adapterPosition
                viewModel.checkItem(idx)
            }
        })

        holder.todoTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val idx = holder.adapterPosition
                //viewModel.itemToEdit.postValue(listTodo[idx])
               // var item = viewModel.itemToEdit.value
                //Log.i("WHERE:", "Adapter change item")
                viewModel.itemToEditWasUpdated = true
                viewModel.setItemToEdit(idx)
            //   val item = viewModel.itemToEdit.value
               // viewModel.idxToEdit.value = idx
            }
        })
    }

    fun setData(data: List<TodoItem>)
    {
        listTodo = data
    }
}