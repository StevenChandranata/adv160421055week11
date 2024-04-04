package com.ubaya.adv160421055week4.view

import android.os.Bundle
import android.util.Log
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
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


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
        observeViewModel()
    }
    fun observeViewModel() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            Picasso.get().load(student.photoUrl).into(binding.imageProfile)
            binding.txtID.setText(student.id)
            binding.txtName.setText(student.name)
            binding.txtBod.setText(student.dob)
            binding.txtPhone.setText(student.phone)
        })
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it

            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_2_24)
                    }
            }


        })
    }
}
