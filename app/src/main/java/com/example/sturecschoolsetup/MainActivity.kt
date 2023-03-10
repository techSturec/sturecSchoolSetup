package com.example.sturecschoolsetup

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.sturecschoolsetup.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Activity created")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val schoolName = findViewById<EditText>(R.id.profile_schoolName)
        val email = findViewById<EditText>(R.id.profile_email)
        val address = findViewById<EditText>(R.id.profile_address)
        val city = findViewById<EditText>(R.id.profile_city)
        val phone = findViewById<EditText>(R.id.profile_phoneNumber)
        val telephoneNumber = findViewById<EditText>(R.id.profile_telephoneNumber)
        val schoolCode = findViewById<EditText>(R.id.profile_schoolCode)
        val password = findViewById<EditText>(R.id.profile_password)

//        val teacherCode = findViewById<EditText>(R.id.profile_teacherCode)
//        val principalCode = findViewById<EditText>(R.id.profile_principalCode)
//        val studentCode = findViewById<EditText>(R.id.profile_studentCode)
//        val driverCode = findViewById<EditText>(R.id.profile_driverCode)
//        val employeeCode = findViewById<EditText>(R.id.profile_employeeCode)

//        val tCode = findViewById<EditText>(R.id.profile_teacherCode)
//        val pCode = findViewById<EditText>(R.id.profile_principalCode)
//        val stCode = findViewById<EditText>(R.id.profile_studentCode)
//        val dCode = findViewById<EditText>(R.id.profile_driverCode)
//        val scCode = findViewById<EditText>(R.id.profile_schoolCode)
//        val eCode = findViewById<EditText>(R.id.profile_employeeCode)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE

        val saveButton = findViewById<Button>(R.id.profile_saveButton)

//        saveButton.setOnClickListener {
//
//            Log.d(TAG, "button clicked")
//
//            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//
//            val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("school data")
//            val code: DatabaseReference = FirebaseDatabase.getInstance().reference.child("codes")
//
//
//            val userMap = HashMap<String, Any>()
//            userMap["email"] = email?.text.toString().lowercase()
//            userMap["fullname"] = fullName?.text.toString().lowercase()
//            userMap["city"] = city?.text.toString().lowercase()
//            userMap["address"] = address?.text.toString().lowercase()
//            userMap["phone"] = phone?.text.toString().lowercase()
//            userMap["telephone"] = telephoneNumber?.text.toString().lowercase()
//            userMap["school code"] = schoolCode?.text.toString().lowercase()
//            userMap["principal code"] = principalCode?.text.toString().lowercase()
//            userMap["teacher code"] = teacherCode?.text.toString().lowercase()
//            userMap["student code"] = studentCode?.text.toString().lowercase()
//            userMap["driver code"] = driverCode?.text.toString().lowercase()
//            userMap["employee code"] = employeeCode?.text.toString().lowercase()
//
//
//            val codes = HashMap<String, Any>()
//            codes["school code"] = schoolCode?.text.toString().lowercase()
//            codes["principal code"] = principalCode?.text.toString().lowercase()
//            codes["teacher code"] = teacherCode?.text.toString().lowercase()
//            codes["student code"] = studentCode?.text.toString().lowercase()
//            codes["driver code"] = driverCode?.text.toString().lowercase()
//            codes["employee code"] = employeeCode?.text.toString().lowercase()
//
//
//            usersRef.child(phone?.text.toString()).setValue(userMap)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(
//                            this,
//                            "Account has been created successfully",
//                            Toast.LENGTH_LONG
//                        )
//
//                        Log.d(TAG, "data added")
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        startActivity(intent)
//                        finish()
//                    } else {
//                        val message = task.exception!!.toString()
//                        Toast.makeText(this, "Error" + message, Toast.LENGTH_LONG)
//                        FirebaseAuth.getInstance().signOut()
//
//                    }
//
//                    code.child(schoolCode?.text.toString()).setValue(codes)
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                Toast.makeText(
//                                    this,
//                                    "Account has been created successfully",
//                                    Toast.LENGTH_LONG
//                                )
//
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                                startActivity(intent)
//                                finish()
//                            } else {
//                                val message = task.exception!!.toString()
//                                Toast.makeText(this, "Error" + message, Toast.LENGTH_LONG)
//
//                            }
//                        }
//                }
//
//
//        }

        saveButton.setOnClickListener{
            /*add checks for validation/isValid() and isEmpty() for all EditTexts */
            if(email.text.toString().isEmpty())
            {
                email.error = "Enter mail first"
                return@setOnClickListener
            }
            if(password.text.toString().isEmpty() || password.text.toString().length<8)
            {
                password.error = "Enter valid password of length more than 8"
                return@setOnClickListener
            }
            if(schoolName.text.toString().isEmpty())
            {
                schoolName.error = "Enter school name"
                return@setOnClickListener
            }
            if(schoolCode.text.toString().isEmpty())
            {
                schoolCode.error = "Enter school code"
                return@setOnClickListener
            }
            if(city.text.toString().isEmpty())
            {
                city.error = "Enter City Name"
                return@setOnClickListener
            }
            if(address.text.toString().isEmpty())
            {
                address.error = "Enter Address first"
                return@setOnClickListener
            }
            if(phone.text.toString().isEmpty())
            {
                address.error = "Enter Phone Number first"
                return@setOnClickListener
            }
            if(telephoneNumber.text.toString().isEmpty())
            {
                telephoneNumber.error = "Enter Telephone Number first"
                return@setOnClickListener
            }


            progressBar.visibility = View.VISIBLE
            val reference = FirebaseDatabase.getInstance().reference.child("schools")
                .child(schoolCode.text.toString())
                .child("schoolData")

            val userMap: HashMap<String, Any> = Util().makeHashMap(listOf(
                Pair("schoolName", schoolName),
                Pair("email", email),
                Pair("address", address),
                Pair("city", city),
                Pair("phone", phone),
                Pair("telephoneNumber", telephoneNumber),
                Pair("schoolCode", schoolCode)
            ))

            Log.e("check", "check")


            reference.get().addOnCompleteListener{
                val check = it.result.value==null
                Log.e("checking", check.toString())
                    if(!check)
                    {
                        Toast.makeText(this, "This School Code is already taken", Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }else
                    {
                        Log.e("is", "here")
                        val auth = FirebaseAuth.getInstance()
                        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnSuccessListener {result->
                            result.user?.let { it1 ->
                                FirebaseDatabase.getInstance().reference.child("adminList").child(
                                    it1.uid)
                                    .setValue(schoolCode.text.toString())
                            }

                            userMap["uid"] = result.user!!.uid

                            reference.setValue(userMap).addOnCompleteListener{
                                if(it.isSuccessful)
                                {
                                    Toast.makeText(
                                        this,
                                        "Created Successfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    progressBar.visibility = View.GONE
                                }else
                                {
                                    Toast.makeText(
                                        this,
                                        "Failed to create school dataset",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    progressBar.visibility = View.GONE
                                }
                            }
                                .addOnFailureListener{
                                    Toast.makeText(
                                        this,
                                        "Sign Up successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    progressBar.visibility = View.GONE
                                }
                        }
                    }
            }

        }


    }
}