package com.example.covid_19.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covid_19.Interfaces.MainInterfaces
import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.Model.MyModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MyRepository{
//    private var requestQueue: RequestQueue
    private val context: Context
    private var myList:MutableLiveData<MyModelClass>
    private var mainInterfaces: MainInterfaces
    private var myCountriesList:MutableLiveData<List<CountriesModelClass>>
    private val BASE_URL:String="https://disease.sh/"
    private var executor: Executor = Executors.newSingleThreadExecutor()
    private constructor(context: Context) {
        myList=MutableLiveData<MyModelClass>()
        myCountriesList=MutableLiveData<List<CountriesModelClass>>()
        this.context = context
        mainInterfaces=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(MainInterfaces::class.java)
        getDataList()
        getDataAllCountries()
    }

    companion object {
        private var instance: MyRepository? = null
        fun GetInstance(context: Context): MyRepository {
            if (instance == null) {
                instance = MyRepository(context)
            }
            return this.instance!!
        }
    }
    private fun getDataList()
    {
        executor.execute(Runnable {
            mainInterfaces.GetData().enqueue(object :Callback<MyModelClass>
            {
                override fun onResponse(call: Call<MyModelClass>, response: Response<MyModelClass>) {
                    myList.postValue(response.body())
                }
                override fun onFailure(call: Call<MyModelClass>, t: Throwable) {
                    Toast.makeText(context,"Error:${t.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            })
        })
    }
    fun getLiveData():LiveData<MyModelClass>
    {
        return myList
    }
    private fun getDataAllCountries()
    {
        executor.execute(Runnable {
            mainInterfaces.getDataByCountry().enqueue(object :Callback<List<CountriesModelClass>>
            {
                override fun onResponse(call: Call<List<CountriesModelClass>>, response: Response<List<CountriesModelClass>>) {
                    myCountriesList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<CountriesModelClass>>, t: Throwable) {
                    Toast.makeText(context,"Error:${t.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            })
        })
    }
    fun getLiveAllData():LiveData<List<CountriesModelClass>>
    {
        return myCountriesList
    }
}