package com.example.covid_19.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountriesModelClass(
    @SerializedName("updated")
    var Update: String? = null,
    @SerializedName("country")
    var Country: String? = null,
    @SerializedName("countryInfo")
    var Details:MoreDetails?=null,
    @SerializedName("cases")
    var Cases: String? = null,
    @SerializedName("todayCases")
    var TodayCases: String? = null,
    @SerializedName("deaths")
    var Deaths: String? = null,
    @SerializedName("todayDeaths")
    var TodayDeaths: String? = null,
    @SerializedName("recovered")
    var Recovered: String? = null,
    @SerializedName("todayRecovered")
    var TodayRecovered: String? = null,
    @SerializedName("active")
    var Active: String? = null,
    @SerializedName("critical")
    var Critical: String? = null,
    @SerializedName("casesPerOneMillion")
    var CasesPerOneMillion: String? = null,
    @SerializedName("deathsPerOneMillion")
    var DeathsPerOneMillion: String? = null,
    @SerializedName("tests")
    var Test: String? = null,
    @SerializedName("testsPerOneMillion")
    var TestPerOneMillion: String? = null,
    @SerializedName("population")
    var Population: String? = null,
    @SerializedName("continent")
    var Continent: String? = null,
    @SerializedName("oneCasePerPeople")
    var OneCasePerPeople: String? = null,
    @SerializedName("oneDeathPerPeople")
    var OneDeathPerPeople: String? = null,
    @SerializedName("oneTestPerPeople")
    var OneTestPerPeople: String? = null,
    @SerializedName("activePerOneMillion")
    var ActivePerOneMillion: String? = null,
    @SerializedName("recoveredPerOneMillion")
    var RecoveredPerOneMillion: String? = null,
    @SerializedName("criticalPerOneMillion")
    var CriticalPerOneMillion: String? = null,
):Serializable
data class MoreDetails(
    @SerializedName("_id")
    var ID:String?=null,
    @SerializedName("iso2")
    var ISO2:String?=null,
    @SerializedName("iso3")
    var ISO3:String?=null,
    @SerializedName("lat")
    var Lat:String?=null,
    @SerializedName("long")
    var Long:String?=null,
    @SerializedName("flag")
    var Image: String? = null
):Serializable