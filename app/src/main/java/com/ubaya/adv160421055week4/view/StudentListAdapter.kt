package com.ubaya.adv160421055week4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.adv160421055week4.databinding.FragmentStudentListItemBinding
import com.ubaya.adv160421055week4.model.Student

class StudentListAdapter (val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var binding: FragmentStudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentListAdapter.StudentViewHolder {
        val binding = FragmentStudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentListAdapter.StudentViewHolder(binding)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            val studentId = studentList[position].id
            val action = StudentListFragmentDirections.actionStudentDetail(studentId.toString())
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl)
            .into(holder.binding.imagePhoto, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressBar.visibility = View.INVISIBLE
                    holder.binding.imagePhoto.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })


    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return studentList.size
    }
}



