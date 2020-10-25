package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance();
        val signIn = findViewById<Button>(R.id.button2)
        val name = findViewById<EditText>(R.id.editTextTextPersonName)
        val email2 = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val password2 = findViewById<EditText>(R.id.editTextTextPassword3)
        val password3 = findViewById<EditText>(R.id.editTextTextPassword2)
        signIn.setOnClickListener {
            if (email2.text.trim().toString()
                    .isNotEmpty() || password2.text.trim().toString()
                    .isNotEmpty() || name.text.trim().toString()
                    .isNotEmpty() || password3.text.toString()
                    .isNotEmpty() && (password2.text.toString() == password3.text.toString())
            ) {
                createUser(
                    email2.text.trim().toString(),
                    password2.text.trim().toString()
                )

            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun createUser(email: String, password: String) {
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
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}

