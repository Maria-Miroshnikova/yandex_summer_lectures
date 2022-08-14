package com.learn.view_homework

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.data_models.TodoItemBuilder
//import com.learn.view_homework.view_models.TodoListViewModel
import com.learn.view_homework.view_models.TodoViewModel
import com.learn.view_homework.view_models.TodoViewModelFactory
import java.time.LocalDateTime
import java.util.*

class NewOrEditTodoItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_or_edit_todo_item, container, false)
    }

  //  fun onStartOLD() {
        /*super.onStart()
        Log.i("WHERE:", "EditFragment onStart")
        val viewModel = ViewModelProvider(requireActivity()).get(TodoListViewModel::class.java)

        val isToCreate = NewOrEditTodoItemFragmentArgs.fromBundle(requireArguments()).isToCreateNew

        // ----------- общие настройки

        val closeImageView = view?.findViewById<ImageView>(R.id.close_editinig_button)
        closeImageView?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val navController = (activity as MainActivity).navController
               //navController?.navigateUp()
            navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
            }

        })

        val descriptionEditText = view?.findViewById<EditText>(R.id.todo_text_edittext)

        val importanceSpinner = view?.findViewById<Spinner>(R.id.importance_spinner)
        context?.let {
            ArrayAdapter.createFromResource(
                it, R.array.importance_spinner_vars, android.R.layout.simple_spinner_item)
        }.also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            importanceSpinner?.adapter = adapter
        }
        importanceSpinner?.setSelection(TodoItem.IMPORTANCE_STATUS.NO.toInt())

        val deadlineSwitch = view?.findViewById<Switch>(R.id.deadline_switch)
        val deadlineTextView = view?.findViewById<TextView>(R.id.deadline_textview_text)
        var deadlineDate : Date? = null
        val datePicker : DatePickerDialog = DatePickerDialog(requireActivity())

        // --------- доп. настройки для редактирования

        if (!isToCreate) {

        //    val repo = viewModel.repository.getTodoList()
        //    val idx = viewModel.itemToEdit.value
            val todoItemToEdit = viewModel.repository.getTodoList()[viewModel.itemToEdit.value!!]

            descriptionEditText?.setText(todoItemToEdit?.text)

            importanceSpinner?.setSelection(todoItemToEdit.importenceStatus.toInt())

            if (todoItemToEdit?.deadline != null)
            {
                deadlineSwitch?.isChecked = true
                deadlineTextView?.setText(todoItemToEdit.deadline.toString())
            }

            //val deleteImageView = view?.findViewById<ImageView>(R.)
            val deleteTextView = view?.findViewById<TextView>(R.id.delete_textview)
            deleteTextView?.setTextColor(resources.getColor(R.color.red))
            deleteTextView?.setOnClickListener(object : View.OnClickListener
            {
                override fun onClick(v: View?) {
                    viewModel.removeItem(viewModel.itemToEdit.value!!)
                    (activity as MainActivity).navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
                }

            })
        }

        deadlineSwitch?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked)
                {
                    datePicker.show()
                    datePicker.setOnDateSetListener(object : DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            deadlineDate = Date(year, month, dayOfMonth)
                            deadlineTextView?.setText(deadlineDate.toString())
                        }
                    })
                }
                else{
                    deadlineTextView?.text = ""
                    deadlineDate = null
                }
            }

        })

        // ------- SAVING

        val saveButton = view?.findViewById<Button>(R.id.save_editing_button)

        saveButton?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val todoItemToSave = collectInfo(deadlineDate)
                if (isToCreate) {
                    viewModel.addItem(todoItemToSave)
                }
                else {
                    viewModel.changeItem(viewModel.itemToEdit.value!!, todoItemToSave)
                }
                (activity as MainActivity).navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
            }
        })
            */
   // }

    private fun collectInfo(deadlineDate: Date?, isToCreate: Boolean): TodoItem? {
        val importanceSpinner = view?.findViewById<Spinner>(R.id.importance_spinner)
        val status = importanceSpinner?.selectedItem.toString()

        val descriptionEditText = view?.findViewById<EditText>(R.id.todo_text_edittext)
        val todoText = descriptionEditText?.text.toString()

        if (isToCreate)
            return TodoItemBuilder(todoText, TodoItem.IMPORTANCE_STATUS.toEnum(status)!!, Calendar.getInstance().time).setDeadline(deadlineDate).build()
        else
        {
            val item = viewModel.itemToEdit.value
            item?.importenceStatus = TodoItem.IMPORTANCE_STATUS.toEnum(status)!!
            item?.deadline = deadlineDate
            item?.text = todoText
            return item
        }
    }

    private val viewModel: TodoViewModel by activityViewModels {
        TodoViewModelFactory((activity?.application as MyApplication).repository)
    }

    override fun onStart() {
        super.onStart()

        val isToCreate = NewOrEditTodoItemFragmentArgs.fromBundle(requireArguments()).isToCreateNew

        // ----------- общие настройки

        val closeImageView = view?.findViewById<ImageView>(R.id.close_editinig_button)
        closeImageView?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val navController = (activity as MainActivity).navController
                navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
            }

        })

        val descriptionEditText = view?.findViewById<EditText>(R.id.todo_text_edittext)

        val importanceSpinner = view?.findViewById<Spinner>(R.id.importance_spinner)
        context?.let {
            ArrayAdapter.createFromResource(
                it, R.array.importance_spinner_vars, android.R.layout.simple_spinner_item)
        }.also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            importanceSpinner?.adapter = adapter
        }
        importanceSpinner?.setSelection(TodoItem.IMPORTANCE_STATUS.NO.toInt())

        val deadlineSwitch = view?.findViewById<Switch>(R.id.deadline_switch)
        val deadlineTextView = view?.findViewById<TextView>(R.id.deadline_textview_text)
        var deadlineDate : Date? = null
        val datePicker : DatePickerDialog = DatePickerDialog(requireActivity())

        // --------- доп. настройки для редактирования

        if (!isToCreate) {

            //    val repo = viewModel.repository.getTodoList()
            //    val idx = viewModel.itemToEdit.value
            val todoItemToEdit = viewModel.itemToEdit.value

            descriptionEditText?.setText(todoItemToEdit?.text)

            importanceSpinner?.setSelection(todoItemToEdit?.importenceStatus!!.toInt())

            if (todoItemToEdit?.deadline != null)
            {
                deadlineSwitch?.isChecked = true
                deadlineTextView?.setText(todoItemToEdit.deadline.toString())
            }

            //val deleteImageView = view?.findViewById<ImageView>(R.)
            val deleteTextView = view?.findViewById<TextView>(R.id.delete_textview)
            deleteTextView?.setTextColor(resources.getColor(R.color.red))
            deleteTextView?.setOnClickListener(object : View.OnClickListener
            {
                override fun onClick(v: View?) {
                    viewModel.removeItem(todoItemToEdit!!)
                    (activity as MainActivity).navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
                }

            })
        }

        deadlineSwitch?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked)
                {
                    datePicker.show()
                    datePicker.setOnDateSetListener(object : DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            deadlineDate = Date(year, month, dayOfMonth)
                            deadlineTextView?.setText(deadlineDate.toString())
                        }
                    })
                }
                else{
                    deadlineTextView?.text = ""
                    deadlineDate = null
                }
            }

        })

        // ------- SAVING

        val saveButton = view?.findViewById<Button>(R.id.save_editing_button)

        saveButton?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val todoItemToSave = collectInfo(deadlineDate, isToCreate)
                if (isToCreate) {
                    if (todoItemToSave != null) {
                        viewModel.addItem(todoItemToSave)
                    }
                }
                else {
                    if (todoItemToSave != null) {
                        viewModel.changeItem(todoItemToSave)
                    }
                }
                (activity as MainActivity).navController?.navigate(R.id.action_newOrEditTodoItemFragment_to_todoListFragment)
            }
        })
    }


}