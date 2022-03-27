package com.example.samuraicase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samuraicase.network.service.ApiClient
import com.example.samuraicase.network.service.pokeurllist.IPokenameList
import com.example.samuraicase.network.service.pokeurllist.PokenameList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var pokemonService: IPokenameList
    lateinit var pokenamelist: PokenameList;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonService = ApiClient.getClient().create(IPokenameList::class.java)
        getUrls()
    }


    fun getUrls() {
        val urls = pokemonService.getPokenameList(20)
        urls.enqueue(object : Callback<PokenameList> {
            override fun onResponse(call: Call<PokenameList>, response: Response<PokenameList>) {
                println("onResponse")
                println(response.body()!!.count.toString())
            }
            override fun onFailure(call: Call<PokenameList>, t: Throwable) {

            }
        })
    }
}