package com.example.michishirube.data

import DestinationListQuery
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class Repository {
//
//    //ここら辺でApolloClientを宣言して
//    private fun apolloClient(context: Context): ApolloClient {
//        return ApolloClient.builder()
//            .serverUrl("https://miraikeitai2020-bff.herokuapp.com/query")
//            .okHttpClient (
//                OkHttpClient.Builder()
//                    .addInterceptor(AuthorizationInterceptor(context))
//                    .build()
//            )
//            .build()
//    }
//
//    fun inputDestination(context: Context, latitude: Double, longitude: Double, worktime: Int, emotion: Int){
//        CoroutineScope(Dispatchers.Default).launch {
//            val res = try {
//                apolloClient(context).query(
//                    DestinationListQuery(
//                        deviceLatitude = latitude,
//                        deviceLongitude = longitude,
//                        worktime = worktime,
//                        emotion = emotion
//                    )
//                ).toDeferred().await()
//            }catch (ex: ApolloException){
//                Log.e("checker",ex.toString())
//                return@launch
//            }
//            val destinationsName = res?.data?.spots?.spots?.map{ it?.name }?:return@launch
//            withContext(Dispatchers.Main){
//                val randomDestinationName = destinationsName.shuffled().first()
//            }
//
//        }
//    }
//
//    private class AuthorizationInterceptor(val context: Context): Interceptor{
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val request = chain.request().newBuilder()
//                .addHeader("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOiIyMDIwLTEwLTEyVDE3OjA1OjAyLjY1MDA4NDY4M1oiLCJzdWIiOiJuYW5haXNvYWlzdSJ9.Cjp90e5PJmWbqWBwXDAf2HNYdvSwEb69INNggX0tOHg")
//                .build()
//
//            return chain.proceed(request)
//        }
//    }

}