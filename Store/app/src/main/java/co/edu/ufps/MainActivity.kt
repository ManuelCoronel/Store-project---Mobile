package co.edu.ufps

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.edu.ufps.vista.HomeActivity
import co.edu.ufps.vista.RegistroActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    val TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usuario  = findViewById<TextInputEditText>(R.id.textUsuario);
        val clave = findViewById<TextInputEditText>(R.id.textClave)
        val sesion = findViewById<Button>(R.id.sesion)
        mAuth = FirebaseAuth.getInstance();
        sesion.setOnClickListener {
            signIn(usuario.text.toString(),clave.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
      //  updateUI(currentUser)
    }

    fun signIn(email:String, password:String){
        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth?.currentUser
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                    irHome()
                    //  updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //  updateUI(null)
                }
            }

    }

    fun irRegistro(view: android.view.View){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    fun irHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}