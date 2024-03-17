package com.example.contactmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanagerapp.databinding.ActivityLoginBinding
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
//    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

//        firebaseAuth = FirebaseAuth.getInstance()
        auth = Firebase.auth


        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.regester.setOnClickListener{
            val main = Intent(this,MainActivity::class.java)
            startActivity(main)
        }

        binding.btnlogin.setOnClickListener{
            val email = binding.ltemail.text.toString()
            val password = binding.ltPass.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){
                    if(it.isSuccessful){
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "inc", Toast.LENGTH_SHORT).show()

                    }
                }


            } else {
                Toast.makeText(this, "Empty Fields are Note allowed", Toast.LENGTH_SHORT).show()

            }
        }

    }

//    private fun readData(UniqueID: String) {
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//        databaseReference.child(UniqueID).get().addOnSuccessListener {
//            if(it.exists()){
//                val savecontactscreen = Intent(this,HomeActivity::class.java)
//                startActivity(savecontactscreen)
//            }
//            else{
//                val resignin = Intent(this,MainActivity::class.java)
//                Toast.makeText(this,"Please First SignUP \n" +
//                        "As User Do Not Exist", Toast.LENGTH_LONG).show()
//                startActivity(resignin)
//            }
//        }.addOnFailureListener{
//            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
//        }
//
//
//    }

}