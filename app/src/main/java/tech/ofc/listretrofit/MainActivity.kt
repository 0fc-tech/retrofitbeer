package tech.ofc.listretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service: IBeerService = retrofit.create(IBeerService::class.java)
        service.listBeer().enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                Log.i(TAG, "onResponse: ${response.body()}")
                response.body()?.let {
                    for (beer in it){
                        Log.i(TAG, "${beer.name} : ${beer.alcoolUnit}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })

    }
}