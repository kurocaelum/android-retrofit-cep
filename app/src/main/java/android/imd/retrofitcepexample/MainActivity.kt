package android.imd.retrofitcepexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        btn_carregar.setOnClickListener {
            acessarCep(edt_cep.text.toString())
        }
    }

    fun acessarCep(cep: String){

        var cepService: CepService =
            retrofit!!.create(CepService::class.java)

        var call: Call<Cep> = cepService.recuperarCep(cep) // Retorna requisição

        call.enqueue(object : Callback<Cep>{
            override fun onFailure(call: Call<Cep>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                if(response.isSuccessful){
                    var cep: Cep? = response.body()

                    txt_resultado.text = "${cep?.logradouro} / ${cep?.bairro} / ${cep?.localidade}"
                }
            }
        })
    }
}
