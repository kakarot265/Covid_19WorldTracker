package com.example.covidtracker

data class CountryData(


// getters and setters are autogenerated in Kotlin

	val country: String? = null,

	val cases: String? = null,


	val active: String? = null,


	val oneDeathPerPeople: String? = null,
	val recovered: String? = null,
	val oneTestPerPeople: String? = null,
	val tests: String? = null,

	val deathsPerOneMillion: String? = null,
	val todayRecovered: String? = null,
	val casesPerOneMillion: String? = null,
	val countryInfo: CountryInfo? = null,
	val updated: String? = null,
	val deaths: String? = null,

	val todayCases: Int? = null,
	val todayDeaths: Int? = null
)

data class CountryInfo(
	val flag: String? = null,
	val id: Int? = null,
	val iso2: String? = null,
	val lat: Int? = null,
	val jsonMemberLong: Int? = null,
	val iso3: String? = null
)
