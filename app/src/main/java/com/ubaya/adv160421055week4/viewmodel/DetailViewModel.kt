package com.ubaya.adv160421055week4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.adv160421055week4.model.Student

class DetailViewModel: ViewModel() {
    val studentLD = MutableLiveData<Student>()


    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        val student2 = Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
                ".jpg/5fa2dd/ffffff")
        studentLD.value = student1
    }
}
