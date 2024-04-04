package com.ubaya.adv160421055week4.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
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
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imagePhoto)

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
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



