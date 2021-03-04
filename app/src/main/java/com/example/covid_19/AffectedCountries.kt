package com.example.covid_19
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.CountriesAdapter
import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.ViewModel.AffectedCountryViewModel
import com.example.covid_19.databinding.ActivityAffectedCountriesBinding
import org.w3c.dom.Text
class AffectedCountries : AppCompatActivity() {
    private lateinit var binding: ActivityAffectedCountriesBinding
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var affectedCountryViewModel: AffectedCountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAffectedCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Affected Countries"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        affectedCountryViewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(AffectedCountryViewModel::class.java)
        with(binding)
        {
            myLoader.visibility=View.VISIBLE
            affectedCountryViewModel.AffectedCountryData().observe(this@AffectedCountries, Observer {
                if(it!=null)
                {
                    countriesAdapter= CountriesAdapter(this@AffectedCountries,it as ArrayList<CountriesModelClass>)
                    ViewRecycler.layoutManager=LinearLayoutManager(this@AffectedCountries)
                    val decoration:DividerItemDecoration= DividerItemDecoration(ViewRecycler.context,LinearLayoutManager.VERTICAL)
                    ViewRecycler.addItemDecoration(decoration)
                    ViewRecycler.adapter=countriesAdapter
                    myLoader.visibility=View.GONE
                }
                else
                {
                    Toast.makeText(this@AffectedCountries,"Empty List",Toast.LENGTH_SHORT).show()
                }
            })

            SearchText.addTextChangedListener(textWatcher)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            countriesAdapter.filter.filter(s)
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }
}
