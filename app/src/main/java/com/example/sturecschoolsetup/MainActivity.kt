package com.example.sturecschoolsetup

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Activity created")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fullName = findViewById<EditText>(R.id.profile_schoolName)
        val email = findViewById<EditText>(R.id.profile_email)
        val address = findViewById<EditText>(R.id.profile_address)
        val city = findViewById<EditText>(R.id.profile_city)
        val phone = findViewById<EditText>(R.id.profile_phoneNumber)
        val telephoneNumber = findViewById<EditText>(R.id.profile_telephoneNumber)
        val teacherCode = findViewById<EditText>(R.id.profile_teacherCode)
        val principalCode = findViewById<EditText>(R.id.profile_principalCode)
        val studentCode = findViewById<EditText>(R.id.profile_studentCode)
        val driverCode = findViewById<EditText>(R.id.profile_driverCode)
        val schoolCode = findViewById<EditText>(R.id.profile_schoolCode)
        val employeeCode = findViewById<EditText>(R.id.profile_employeeCode)

//        val tCode = findViewById<EditText>(R.id.profile_teacherCode)
//        val pCode = findViewById<EditText>(R.id.profile_principalCode)
//        val stCode = findViewById<EditText>(R.id.profile_studentCode)
//        val dCode = findViewById<EditText>(R.id.profile_driverCode)
//        val scCode = findViewById<EditText>(R.id.profile_schoolCode)
//        val eCode = findViewById<EditText>(R.id.profile_employeeCode)

        val saveButton = findViewById<Button>(R.id.profile_saveButton)



        saveButton.setOnClickListener {

            Log.d(TAG, "button clicked")

            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

            val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("school data")
            val code: DatabaseReference = FirebaseDatabase.getInstance().reference.child("codes")


            val userMap = HashMap<String, Any>()
            userMap["email"] = email?.text.toString().lowercase()
            userMap["fullname"] = fullName?.text.toString().lowercase()
            userMap["city"] = city?.text.toString().lowercase()
            userMap["address"] = address?.text.toString().lowercase()
            userMap["phone"] = phone?.text.toString().lowercase()
            userMap["telephone"] = telephoneNumber?.text.toString().lowercase()
            userMap["school code"] = schoolCode?.text.toString().lowercase()
            userMap["principal code"] = principalCode?.text.toString().lowercase()
            userMap["teacher code"] = teacherCode?.text.toString().lowercase()
            userMap["student code"] = studentCode?.text.toString().lowercase()
            userMap["driver code"] = driverCode?.text.toString().lowercase()
            userMap["employee code"] = employeeCode?.text.toString().lowercase()


            val codes = HashMap<String, Any>()
            codes["school code"] = schoolCode?.text.toString().lowercase()
            codes["principal code"] = principalCode?.text.toString().lowercase()
            codes["teacher code"] = teacherCode?.text.toString().lowercase()
            codes["student code"] = studentCode?.text.toString().lowercase()
            codes["driver code"] = driverCode?.text.toString().lowercase()
            codes["employee code"] = employeeCode?.text.toString().lowercase()


            usersRef.child(phone?.text.toString()).setValue(userMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Account has been created successfully",
                            Toast.LENGTH_LONG

                        )

                        Log.d(TAG, "data added")
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        val message = task.exception!!.toString()
                        Toast.makeText(this, "Error" + message, Toast.LENGTH_LONG)
                        FirebaseAuth.getInstance().signOut()

                    }

                    code.child(schoolCode?.text.toString()).setValue(codes)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Account has been created successfully",
                                    Toast.LENGTH_LONG
                                )

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            } else {
                                val message = task.exception!!.toString()
                                Toast.makeText(this, "Error" + message, Toast.LENGTH_LONG)

                            }
                        }
                }


        }
    }
}