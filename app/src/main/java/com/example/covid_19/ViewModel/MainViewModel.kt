package com.example.covid_19.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covid_19.Model.MyModelClass
import com.example.covid_19.Repository.MyRepository
import kotlin.math.log

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var myRepository: MyRepository
    init {
        myRepository = MyRepository.GetInstance(application)
    }

    fun FetchData():LiveData<MyModelClass>{
        return myRepository.getLiveData()
    }
}



