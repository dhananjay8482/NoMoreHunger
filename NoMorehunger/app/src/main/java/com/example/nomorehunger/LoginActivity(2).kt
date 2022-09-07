package com.example.nomorehunger

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    //  DATABASE CONNECTION
    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null

    var is_match = false
    var acc_name = ""
    var acc_address = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        title = "Curb The Hunger"

        val progressDialog = ProgressDialog(this@LoginActivity)


        val input_number = findViewById<EditText>(R.id.input_login_phone_number)
        val input_password = findViewById<EditText>(R.id.input_login_password)
        val btn_login = findViewById<Button>(R.id.btn_login_login)
        val btn_signup = findViewById<Button>(R.id.btn_login_signup)

        btn_signup.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish();
        }

        btn_login.setOnClickListener {
            is_match = false
//          Start Progress Bar
            progressDialog.show()
            progressDialog.setContentView(R.layout.progres_dialogue)

            var validate = true
            val sNumber = input_number.text.toString()
            val sPassword = input_password.text.toString()

            if(sNumber=="" || sPassword=="" ){
                validate = false
                progressDialog.dismiss()
                Toast.makeText(this, "First Fill the Above Data ", Toast.LENGTH_SHORT).show()
            }

            mDatabase = FirebaseDatabase.getInstance().reference
            mMessageReference = FirebaseDatabase.getInstance().getReference("users")


            if (validate){

                val query = mMessageReference!!.orderByChild("phone").equalTo(sNumber)

                query.addValueEventListener((object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            for(userSnapshot in  snapshot.children){
                                val user = userSnapshot.getValue(User::class.java)
                                if (user != null) {
                                    if(user.password == sPassword){
                                        progressDialog.dismiss()
                                        is_match = true
                                        acc_name = user.name.toString()
                                        acc_address = user.address.toString()
                                        password_correct()
                                        break
                                    }
                                }
                            }

                        }
                        progressDialog.dismiss()
                        password_correct()
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }))

            }

        }

    }

    fun password_correct() {

        if(is_match){
//            Toast.makeText(this, "Correct Password", Toast.LENGTH_SHORT).show()
            val input_number = findViewById<EditText>(R.id.input_login_phone_number)
            intent = Intent(this,MainActivity::class.java)
            intent.putExtra("NUMBER",input_number.text.toString())
            intent.putExtra("NAME",acc_name)
            intent.putExtra("ADDRESS",acc_address)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        finish()
    }

}