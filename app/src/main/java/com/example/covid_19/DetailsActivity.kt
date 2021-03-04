package com.example.covid_19

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.databinding.ActivityDetailsBinding
import org.eazegraph.lib.models.PieModel
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        with(binding)
        {
            loader.visibility = View.VISIBLE
            val myModel=intent.getSerializableExtra("Value") as CountriesModelClass
            CountryText.text=myModel.Country!!
            CasesText.text = myModel.Cases!!
            RecoveredText.text = myModel.Recovered!!
            CriticalText.text = myModel.Critical!!
            ActiveText.text = myModel.Active!!
            TodayCasesText.text = myModel.TodayCases!!
            TotalDeathsText.text = myModel.Deaths!!
            TodayDeathsText.text = myModel.TodayDeaths!!
            piechart.addPieSlice(PieModel("Cases", myModel.Cases!!.toFloat(), Color.parseColor("#FFA726")))
            piechart.addPieSlice(PieModel("Recovered", myModel.Recovered!!.toFloat(), Color.parseColor("#66BB6A")))
            piechart.addPieSlice(PieModel("Deaths", myModel.Deaths!!.toFloat(), Color.parseColor("#EF5350")))
            piechart.addPieSlice(PieModel("Active", myModel.Active!!.toFloat(), Color.parseColor("#29B6F6")))
            piechart.startAnimation()
            loader.visibility = View.GONE
            ScrollState.visibility = View.VISIBLE
            OkCountriesButton.setOnClickListener {
                startActivity(Intent(this@DetailsActivity,MainActivity::class.java))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}