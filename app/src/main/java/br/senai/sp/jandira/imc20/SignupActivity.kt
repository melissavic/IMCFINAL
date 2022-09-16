package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.senai.sp.jandira.imc20.model.User

class SignupActivity : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        supportActionBar!!.hide()

        //Instanciar os componentes (views) dentro do onCreate
        editName = findViewById(R.id.edit_text_name)
        editEmail = findViewById(R.id.edit_text_email)
        editPassword = findViewById(R.id.edit_text_password_signup)
        editWeight = findViewById(R.id.edit_text_weight)
        editHeight = findViewById(R.id.edit_text_high)
        buttonSave = findViewById(R.id.button_record_user)

        //colocar click no botão

        buttonSave.setOnClickListener {
            saveUser()
        }
    }

    //Classe = receita
    private fun saveUser() {
        //construção do objeto usuário:
        val user = User()

        user.id = 1
        user.name = editName.text.toString()
        user.email = editEmail.text.toString()
        user.password = editPassword.text.toString()

        //converter primeiro em String para depois escolher o tipo do número

        user.wheight = editWeight.text.toString().toInt()
        user.height = editHeight.text.toString().toDouble()

        //Gravar usuário com SharedPreferences
        //Passo 1 = Obter instäncia do SharedPreferences
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        //Passo 2 = Criar um editor para o arquivo
        val editor = dados.edit()

        //Passo 3 = Inserindo dados no arquivo
        editor.putInt("id", user.id)
        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putInt("weight", user.wheight)
        editor.putFloat("height", user.height.toFloat())

        //commit(devolve um boolean) = executar gravação
        if(editor.commit()){
            Toast.makeText(this, "User registration was successful", Toast.LENGTH_SHORT).show()

            //Fecha a activity
            finish()
        }else{
            Toast.makeText(this, "User register is failure", Toast.LENGTH_SHORT).show()
        }
    }
}