package parra.daniel.mydigimind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = Firebase.auth
        btn_registrar.setOnClickListener {
            valida_registro()
        }
    }

    private fun valida_registro() {
        val correo = et_correo_reg.text.toString()
        val contra1 = et_contra_reg.text.toString()
        val contra2 = et_contra2_reg.text.toString()

        if (!(correo.isNullOrBlank() || contra1.isNullOrBlank() || contra2.isNullOrBlank())) {
            if (contra1.equals(contra2)) {
                registrarFirebase(correo, contra1)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication successful.", Toast.LENGTH_SHORT).show()
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    // updateUI(null)
                }
            }
    }

}