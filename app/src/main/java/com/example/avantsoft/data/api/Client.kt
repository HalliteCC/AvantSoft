package com.example.avantsoft.data.api


import com.example.avantsoft.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created as singleton to be instantiated just one time
 */
object Client {
    private var retrofit: Retrofit? = null

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Const.CLIENT.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun <S> getService(serviceClass: Class<S>): S {
        return getClient().create(serviceClass)
    }
}





