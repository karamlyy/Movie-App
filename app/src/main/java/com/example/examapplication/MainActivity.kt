package com.example.examapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.examapplication.adapter.Adapter
import com.example.examapplication.api.buildAPI
import com.example.examapplication.databinding.ActivityMainBinding
import com.example.examapplication.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,false)
    }
    fun onClickListener(){
        binding.popularButton.setOnClickListener {
            getPopularMovie()
        }
        binding.upcomingButton.setOnClickListener {
            getUpcomingMovie()
        }
        binding.searchButton.setOnClickListener {
            searchMovie()
        }
    }
    fun getPopularMovie(){
        val call = buildAPI().getPopularMovie(Constants.API_KEY)
        call?.enqueue(object : Callback<Result?>{
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                var result : Result? = response.body()
                binding.recyclerView.adapter = Adapter(result!!.results)
            }
            override fun onFailure(call: Call<Result?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getUpcomingMovie(){
        val call = buildAPI().getUpcomingMovie(Constants.API_KEY)
        call?.enqueue(object : Callback<Result?>{
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                var result : Result? = response.body()
                binding.recyclerView.adapter = Adapter(result!!.results)
            }
            override fun onFailure(call: Call<Result?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun searchMovie(){
        val call = buildAPI().searchMovie(Constants.API_KEY, binding.searchField.text.toString())
        call?.enqueue(object : Callback<Result?>{
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                var result : Result? = response.body()
                binding.recyclerView.adapter = Adapter(result!!.results)
            }
            override fun onFailure(call: Call<Result?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }
}