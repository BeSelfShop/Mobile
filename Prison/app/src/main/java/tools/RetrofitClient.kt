package tools

import android.util.Base64
import com.example.prison.BuildConfig
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.also as also

object RetrofitClient {

    private const val BASE_URL = " https://wiezienie202l.azurewebsites.net/api/"


    private fun okHttpClient(token: String?) : OkHttpClient
    {
         return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization" ,"Bearer " + token)
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
            .client(okHttpClient(authToken))
            .build()
            .create(api)
    }

}

