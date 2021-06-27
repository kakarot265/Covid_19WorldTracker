package com.example.covidtracker

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.Serializable
import java.util.ArrayList

class CountryActivity : AppCompatActivity() {

var originalList = ArrayList<CountryData>()
    var list = ArrayList<CountryData>()
    var adapter = CountryAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

countries.apply {
    layoutManager = LinearLayoutManager(this@CountryActivity)
    adapter = this@CountryActivity.adapter
    hasFixedSize()
}
        adapter.onItemClick = { it ->
          val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("country", it)
            startActivity(intent)

        }



        // ADDING SEARCH OPTION
        searchView.isSubmitButtonEnabled= true
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    searchUsers(it)
                }
                return true
            }


        })

        searchView.setOnCloseListener {
            adapter.swapData(originalList)
            true
        }



        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){ApiUtilities.api.getCountryData()}

            if(response.isSuccessful){
                response.body()?.let {
                    list.addAll(it)
                    adapter.swapData(list)
                }
            }

        }




     /*   ApiUtilities.api.getCountryData().enqueue(object : retrofit2.Callback<List<CountryData>> {
            override fun onResponse(call: retrofit2.Call<List<CountryData>>, response: retrofit2.Response<List<CountryData>>) {
                response.body()?.let { list.addAll(it) }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: retrofit2.Call<List<CountryData>>, t: Throwable) {
                Toast.makeText(this@CountryActivity, ""+t.message, Toast.LENGTH_SHORT).show()
            }

        })

      */

    }

    fun searchUsers(query:String){


                    for(items:CountryData in list){

                        if(items.country!!.toLowerCase()!!.contains(query.toLowerCase())){
                            originalList.add(items)

                        }


                    }
        adapter.swapData(originalList)



                }
            }




