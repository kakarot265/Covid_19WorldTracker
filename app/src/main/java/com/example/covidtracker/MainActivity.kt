package com.example.covidtracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtracker.ApiUtilities.api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.country_item_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eazegraph.lib.models.PieModel
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setOnClickListener {
            val i = Intent(this, CountryActivity::class.java)
            startActivity(i)
        }

   val country = intent.getStringExtra("country")



        // ADDING PIE CHART


        GlobalScope.launch(Dispatchers.Main) {

            val response = withContext(Dispatchers.IO){
             // FOR OTHER COUNTRIES
                if (country != null) {
                    ApiUtilities.api.searchCountry(country)
                }

                // FOR INDIA
                else{
                    ApiUtilities.api.searchCountry("India")
                }


            }

            if(response.isSuccessful){
                response.body().let {

                  piechart.addPieSlice(PieModel("Confirm",it?.cases!!.toFloat() , Color.parseColor("#FBC233")))
                    piechart.addPieSlice(PieModel("Active", it?.active!!.toFloat(), Color.parseColor("#007afe")))
                    piechart.addPieSlice(PieModel("Death", it?.deaths!!.toFloat(), Color.parseColor("#F6404F")))
                    piechart.addPieSlice(PieModel("Recovered", it?.recovered!!.toFloat(), Color.parseColor("#08a045")))

                    piechart.startAnimation()

                    tv.text = it.country
                    active.text = it.active
                    tests.text = it.tests
                    confirm.text = it.cases
                    recovered.text = it.recovered
                    death.text = it.deaths

                    tconfirm.text = "+"+it.todayCases
                    ttest.text = "+"+it.oneTestPerPeople
                    trecovered.text = "+"+it.todayRecovered
                    tdeath.text = "+"+it.todayDeaths

                }
            }
        }







            }
        }
