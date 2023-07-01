package com.example.animallist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    lateinit var myAdapter: MyAdapter

    var BASE_URL = "https://data.coa.gov.tw/api/v1/AnimalRecognition/"

    private var data: List<UsersItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.setHasFixedSize(true)
        myAdapter = MyAdapter(this, data)
        rvMain.adapter = myAdapter

        getAllData()
    }
    private fun getAllData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retroData = retrofit.getData()



        retroData.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()

                    if (data != null && data.isNotEmpty()){
                        //*myAdapter = MyAdapter(baseContext, data)*//*
                        myAdapter = MyAdapter(this@MainActivity, data)

                        if (::myAdapter.isInitialized && myAdapter.itemCount > 0) {
                        rvMain.adapter = myAdapter
                        rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                        Log.i("data", data.toString())
                        } else {
                            Toast.makeText(this@MainActivity, "适配器未正确设置或数据为空", Toast.LENGTH_SHORT).show()
                            // 处理适配器未正确设置或数据为空的情况，例如显示错误提示或进行其他逻辑处理
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "数据为空", Toast.LENGTH_SHORT).show()
                        // 处理空数据的情况
                    }
                } else {
                    Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
                    // 处理请求失败的情况，根据需要进行错误处理
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                // 处理请求失败的情况，根据需要进行错误处理
            }
        })
    }}









