package com.ubaya.adv160421055week4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.adv160421055week4.model.Student
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    private val requestQueue: RequestQueue = Volley.newRequestQueue(application)

    fun fetch(studentId: String) {
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val student = gson.fromJson(response.toString(), Student::class.java)
                studentLD.value = student
            },
            { error ->
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}

