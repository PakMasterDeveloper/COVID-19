package com.example.covid_19.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.Repository.MyRepository

class AffectedCountryViewModel(application: Application) : AndroidViewModel(application) {
    private var myRepository: MyRepository

    init {
        myRepository= MyRepository.GetInstance(application)
    }
    public fun AffectedCountryData():LiveData<List<CountriesModelClass>>
    {
        return myRepository.getLiveAllData()
    }
}