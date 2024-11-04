package com.paul.newsapp.api


import com.paul.newsapp.models.NewsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//acbf05cb82074db292a102a8dcf3816b
//"https://newsapi.org/"
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "acbf05cb82074db292a102a8dcf3816b"

interface NewsService {
           //@GET("v2/top-headlines?apiKey$API_KEY")
         @GET("v2/top-headlines?apiKey=$API_KEY")
         fun getHeadlines(@Query("country") country: String, @Query("page") page:Int) :retrofit2.Call<NewsModel>
}
object NewsServiceObj{
    val newsService:NewsService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsService = retrofit.create(NewsService::class.java)

    }
}