package com.example.covid_19

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covid_19.Model.MyModelClass
import com.example.covid_19.ViewModel.MainViewModel
import com.example.covid_19.databinding.ActivityMainBinding
import org.eazegraph.lib.models.PieModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        with(binding)
        {
            animationView.visibility=View.GONE
            CustomCardView.visibility=View.VISIBLE
            MyCardView.visibility=View.VISIBLE
            TrackCountriesButton.visibility=View.VISIBLE
            if(checkInternet()==true)
            {
                mainViewModel.FetchData().observe(this@MainActivity, Observer<MyModelClass> {
                    if (it != null) {
                        CasesText.text = it.Cases
                        RecoveredText.text = it.Recovered
                        CriticalText.text = it.Critical
                        ActiveText.text = it.Active
                        TodayCasesText.text = it.TodayCases
                        TotalDeathsText.text = it.Deaths
                        TodayDeathsText.text = it.TodayDeaths
                        AffectedCountriesText.text = it.AffectedCountries
                        piechart.addPieSlice(PieModel("Cases", it.Cases!!.toFloat(), Color.parseColor("#FFA726")))
                        piechart.addPieSlice(PieModel("Recovered", it.Recovered!!.toFloat(), Color.parseColor("#66BB6A")))
                        piechart.addPieSlice(PieModel("Deaths", it.Deaths!!.toFloat(), Color.parseColor("#EF5350")))
                        piechart.addPieSlice(PieModel("Active", it.Active!!.toFloat(), Color.parseColor("#29B6F6")))
                        piechart.startAnimation()
                        loader.visibility = View.GONE
                        ScrollState.visibility = View.VISIBLE
                    } else {
                        Log.d("MyTag", "Error :Empty")
                    }
                })
            }
            else
            {
                animationView.visibility=View.VISIBLE
                CustomCardView.visibility=View.GONE
                MyCardView.visibility=View.GONE
                TrackCountriesButton.visibility=View.GONE
            }
            TrackCountriesButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, AffectedCountries::class.java))
            }
        }
    }
    fun checkInternet():Boolean
    {
       val conections:ConnectivityManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info:NetworkInfo?=conections.activeNetworkInfo
        val isConnective:Boolean=info?.isConnectedOrConnecting==true
        return isConnective
    }
}