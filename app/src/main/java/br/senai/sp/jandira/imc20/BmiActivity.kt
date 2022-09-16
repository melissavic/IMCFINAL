package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import br.senai.sp.jandira.imc20.databinding.ActivityBmiBinding
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding
import br.senai.sp.jandira.imc20.model.User

class BmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadProfile()
        binding.buttonCalculate.setOnClickListener{
            bmiCalculate()
        }
    }

    private fun bmiCalculate() {
        val openResult = Intent(this, ResultBmiActivity::class.java)
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        if(binding.editTextWeightCalculate.text.isEmpty()){
            openResult.putExtra("weight", dados.getInt("weight", 0))
        }
        if (binding.editTextHighCalculate.text.isEmpty()){
            openResult.putExtra("high", dados.getFloat("height", 00F))
        }
        else{
            //enviar resultados de uma activity para outra
            openResult.putExtra("weight", binding.editTextWeightCalculate.text.toString().toDouble())
            openResult.putExtra("high", binding.editTextHighCalculate.text.toString().toDouble())
        }
        startActivity(openResult)
    }


    private fun loadProfile() {
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        binding.textViewUsername.text = dados.getString("name", "")
        binding.textViewHigh.text = "High: ${dados.getFloat("height", 0.0F)}"
        binding.textViewWeight.text = "Weight: ${dados.getInt("weight", 0)}"
        binding.textViewEmail.text = dados.getString("email", "")

    }
}