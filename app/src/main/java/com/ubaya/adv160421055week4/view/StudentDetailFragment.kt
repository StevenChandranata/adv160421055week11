package com.ubaya.adv160421055week4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.adv160421055week4.R
import com.ubaya.adv160421055week4.databinding.FragmentStudentDetailBinding
import com.ubaya.adv160421055week4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        detailViewModel.fetch()

        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer { student1 ->
            Picasso.get().load(student1.photoUrl).into(binding.imageProfile)
            binding.txtID.setText(student1.id)
            binding.txtName.setText(student1.name)
            binding.txtBod.setText(student1.dob)
            binding.txtPhone.setText(student1.phone)
        })
    }
}
