package apps.sami.todofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class AnonSignIn : AppCompatActivity() {
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anon_sign_in)
        supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btnSignIn).setOnClickListener{
            anonymousAuth()
        }

    }
    private fun anonymousAuth(){
        firebaseAuth
            .signInAnonymously()
            .addOnSuccessListener {
                startActivity(Intent(this,MainActivity::class.java))
            }
            .addOnFailureListener {
                Log.d ("TAG", "anonymousAuth: $it")
            }
    }
}