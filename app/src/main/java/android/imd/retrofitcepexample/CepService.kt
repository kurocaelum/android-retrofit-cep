package android.imd.retrofitcepexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {

    @GET("{cep}/json/")
    fun recuperarCep(@Path("cep")cep: String): Call<Cep>
}