package tools

import com.squareup.okhttp.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://wiezienie202l.azurewebsites.net/api/"


    private fun okHttpClient() : OkHttpClient
    {
         return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "")
                    .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
    }


    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(api)
    }

}

