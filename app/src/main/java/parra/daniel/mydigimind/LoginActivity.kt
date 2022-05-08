package parra.daniel.mydigimind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        btn_ingresar.setOnClickListener {
            valida_ingreso()
        }

        tv_olvidasteContra.setOnClickListener {
            val intent: Intent = Intent(this, ContrasenaActivity::class.java)
            startActivity(intent)
        }

        btn_registrarse.setOnClickListener {
            val intent: Intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

    }

    private fun valida_ingreso() {
        val correo = et_correo.text.toString()
        val contra = et_contra.text.toString()

        if (!(correo.isNullOrBlank() && contra.isNullOrBlank())) {
            ingresaFirebase(correo, contra)
        } else {
            Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresaFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}