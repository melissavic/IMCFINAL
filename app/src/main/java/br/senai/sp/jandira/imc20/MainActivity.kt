package br.senai.sp.jandira.imc20

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.textSignup.setOnClickListener{
            var openSignupActivity = Intent(this, SignupActivity::class.java)

            startActivity(openSignupActivity)
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }



    private fun login() {
        if(validar()){
            val email = binding.editTextEmailLogin.text.toString()
            val pass = binding.editTextPasswordLogin.text.toString()

            //Passo 1 = Obter instäncia do SharedPreferences
            val dados = getSharedPreferences("dados", MODE_PRIVATE)
            val emailSp = dados.getString("email", "Email não encontrado")
            val passSp = dados.getString("password", "Senha não encontrada")

            //verificar se os dados de autentocação estão corretos
            if(email == emailSp && pass == passSp) {
                val openBmiActivity = Intent(this, BmiActivity::class.java)

                startActivity(openBmiActivity)
            }else{
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validar(): Boolean {
        if(binding.editTextEmailLogin.text.isEmpty()){
            binding.editTextEmailLogin.error = "Email is required"
            return false
        }
        if(binding.editTextPasswordLogin.text.isEmpty()){
            binding.editTextPasswordLogin.error = "Password is required"
            return false
        }
        return true
    }
}