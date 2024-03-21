package com.example.contactmanagerapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    private lateinit var binding: ActivityMainBinding

    lateinit var process: ProgressBar
//    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        firebaseAuth = FirebaseAuth.getInstance()
        // Initialize Firebase Auth
        auth = Firebase.auth

        dialog = Dialog(this)
        dialog.setContentView(R.layout.customedilogue)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.alert_bg))

        binding.allready.setOnClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }

        binding.btnSignUP.setOnClickListener {
            binding.progressBar.visibility=ProgressBar.VISIBLE


            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            val Cpassword = binding.etCPass.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty()) {

                if (password.equals(Cpassword)) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) {
                           if (it.isSuccessful)  {
                               Toast.makeText(this, "Account created success fully", Toast.LENGTH_SHORT).show()
                                val intent1 = Intent(this, LoginActivity::class.java)
                                startActivity(intent1)
                               binding.progressBar.visibility=ProgressBar.GONE
                            }
                        }


                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }

            } else {
                binding.progressBar.visibility=ProgressBar.GONE
                Toast.makeText(this, "Empty Fields are Note allowed", Toast.LENGTH_SHORT).show()

            }


        }
    }

}