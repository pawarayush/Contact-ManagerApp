package com.example.contactmanagerapp


import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanagerapp.databinding.ActivityHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {
    lateinit var dataBase: DatabaseReference
    lateinit var binding: ActivityHomeBinding
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dialog = Dialog(this)
        dialog.setContentView(R.layout.customedilogue)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.alert_bg))

        binding.btnADD.setOnClickListener {

            val name = binding.ltName.text.toString()
            val phone = binding.ltPhoneNo.text.toString()

            val user = User(name, phone)


            if (name.isNotEmpty() && phone.isNotEmpty()) {
//


                dataBase = FirebaseDatabase.getInstance().getReference("Contacts")
                dataBase.child(phone).setValue(user).addOnSuccessListener {
                    binding.ltName.text?.clear()
                    binding.ltPhoneNo.text?.clear()
                    dialog.show()
                }.addOnFailureListener {

                    Toast.makeText(this, "User Not regester", Toast.LENGTH_SHORT).show()


                }

            } else {
                Toast.makeText(this, "Please Enter the Values", Toast.LENGTH_SHORT).show()
            }


            val buttonok = dialog.findViewById<Button>(R.id.btnok)
            val buttoncancle = dialog.findViewById<Button>(R.id.btncancel)

            buttonok.setOnClickListener {
                dialog.dismiss()

            }
            buttoncancle.setOnClickListener {
                finish()

            }
        }
    }
}