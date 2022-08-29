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
//import com.learn.view_homework.view_models.TodoListViewModel
import android.text.Spannable

import android.graphics.Color

import android.text.style.ForegroundColorSpan

import android.text.SpannableString
import androidx.core.view.ViewCompat
import com.learn.view_homework.view_models.TodoViewModel

// observe List и при обновлении - adapter.setData!
// но действия со списком сами влияют на livedata
// придется перерисовывать список каждый раз?
// тогда точно diffutil
class TodoListAdapter(/*val viewModel: TodoListViewModel,*/
                        val viewModel: TodoViewModel,
                        val context: Context?) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    var listTodo: List<TodoItem> = ArrayList<TodoItem>()

    class TodoViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView) {
        val isDonecheckBox : CheckBox = itemView.findViewById(R.id.isDoneCheckBox)
        val todoTextView : TextView = itemView.findViewById(R.id.todoTextView)
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
                //viewModel.checkItem(idx)
                var item = listTodo[idx]
                item.isDone = !item.isDone
                viewModel.changeItem(item)
            }
        })

        holder.todoTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val idx = holder.adapterPosition
              //  viewModel.itemToEditWasUpdated = true
              //  viewModel.setItemToEdit(idx)
                ViewCompat.setTransitionName(v!!, "todo_item_in_list")
                viewModel.itemToEditWasUpdated = true
                viewModel.itemToEdit.value = listTodo[idx]
            }
        })
    }

    fun setData(data: List<TodoItem>)
    {
        listTodo = data
    }
}