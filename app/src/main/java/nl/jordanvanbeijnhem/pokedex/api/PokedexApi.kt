package nl.jordanvanbeijnhem.pokedex.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexApi {

    companion object {

        const val PAGE_SIZE = 50
        private const val baseUrl = "https://pokeapi.co/api/v2/"

        fun createApi(): PokedexApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val pokedexApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return pokedexApi.create(PokedexApiService::class.java)
        }
    }
}