package com.example.tripwhiz

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();

        val tv_click_me = findViewById(R.id.button3) as TextView
        tv_click_me.setOnClickListener {
            val intent = Intent(this, Registration::class.java);
            startActivity(intent);
        }
        val passwordReset = findViewById(R.id.fgtextview) as TextView
        passwordReset.setOnClickListener {
            val builder :AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view:View  = layoutInflater.inflate(R.layout.dialog_forgotpassword,null)
            val username = view.findViewById<EditText>(R.id.ed_Reset)
            builder.setView(view)
            builder.setPositiveButton("Reset",DialogInterface.OnClickListener { _, _ ->
                forgotPassword(username);
            } )
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> } )
            builder.show()
        }
        val login = findViewById<Button>(R.id.button)
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        login.setOnClickListener {
            if (email.text.trim().toString()
                            .isNotEmpty() || password.text.trim().toString().isNotEmpty()
            ) {
                loginUser(
                        email.text.trim().toString(),
                        password .text.trim().toString()
                );
            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }

    }
    fun forgotPassword(username:EditText) {
        if (username.text.trim().toString().isEmpty()) {
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return;
        }

        auth.sendPasswordResetEmail(username.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                    }
                }

    }

    fun loginUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener (this){ task ->
            if(task.isSuccessful){
                Log.e("Task message", "Successfull...");
                Toast.makeText(this,"Welcom!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            }
            else{
                Log.e("Task message", "Failed..."+task.exception);
            }
        }
    }
}