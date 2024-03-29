package gla.ac.`in`.miniproject1.tripwhiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance();
        val signIn = findViewById<Button>(R.id.button2)

        signIn.setOnClickListener {
            if (editTextTextEmailAddress2.text.trim().toString()
                    .isNotEmpty() || editTextTextPassword2.text.trim().toString()
                    .isNotEmpty() || editTextTextPersonName.text.trim().toString()
                    .isNotEmpty() || editTextTextPassword3.text.toString()
                    .isNotEmpty() && (editTextTextPassword2.text.toString() == editTextTextPassword3.text.toString())
            ) {
                createUser(
                    editTextTextEmailAddress2.text.trim().toString(),
                    editTextTextPassword2.text.trim().toString()
                )

                nextActivity()

            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.e("Task message", "Successfull...")
                Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Log.e("Task message", "Failed..." + task.exception);
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun nextActivity() {
        val signIn = findViewById<Button>(R.id.button2)

        signIn.setOnClickListener(View.OnClickListener {
            val activity2Intent = Intent(applicationContext, Preferences::class.java)
            startActivity(activity2Intent)
        })
    }
}

