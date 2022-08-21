package com.example.davaleba_16.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitObject {
    private const val BASE_URL = "https://reqres.in"
    private val retrofitbuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun getRetrofitState() = retrofitbuilder.create(GetUser::class.java)

    interface GetUser {

        @GET("api/users")
       suspend fun gerUser(@Query("page") page:Int): Response<UsersData>
    }



    class UserRepository {

        suspend fun getInfo(page: Int): UsersData? {
            val response = RetrofitObject.getRetrofitState().gerUser(page)
            return if (response.isSuccessful && response.body() != null)
                response.body()!!
            else null
        }

    }
}