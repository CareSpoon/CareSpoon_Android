package org.whocare.carespoon.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object RetrofitBuilder {
    private const val BASE_URL = "http://34.64.46.218:8080/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()


    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
            val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
            override fun convert(value: ResponseBody) = if (value.contentLength() == 0L) null else nextResponseBodyConverter.convert(value)
        }
    }

    private val careSpoonRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val friendService: FriendService = careSpoonRetrofit.create(FriendService::class.java)
    val mealService: MealService = careSpoonRetrofit.create(MealService::class.java)
    val userInfoService: UserInfoService = careSpoonRetrofit.create(UserInfoService::class.java)
    val userService: UserService = careSpoonRetrofit.create(UserService::class.java)

}