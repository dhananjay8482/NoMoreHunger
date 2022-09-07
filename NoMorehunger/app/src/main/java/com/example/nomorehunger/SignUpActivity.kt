package com.example.nomorehunger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spinner: Spinner
    lateinit var address:String
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    var validate = true

//  DATABASE CONNECTION
    internal lateinit var rootNode: FirebaseDatabase
    internal lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        title = "Curb The Hunger"

//        input_address = findViewById(R.id.signup_input_address)
        spinner = findViewById(R.id.signup_spinner_address)
        radioGroup = findViewById(R.id.radioGroup)

        val btn_signup = findViewById<Button>(R.id.btn_signup_signup)
        val btn_login = findViewById<Button>(R.id.btn_signup_login)

        val name = findViewById<EditText>(R.id.input_signup_name)!!
        val phone = findViewById<EditText>(R.id.input_signup_phone_number)!!
        val password = findViewById<EditText>(R.id.input_signup_password)!!



        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.address_dropdown,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this










        btn_signup.setOnClickListener {
            rootNode = FirebaseDatabase.getInstance()
            reference = rootNode.getReference("users")
            validate = true

            var intSelectButton: Int = 2
            intSelectButton = radioGroup!!.checkedRadioButtonId

            radioButton = findViewById(intSelectButton)
//            Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()
            val account_type = radioButton.text.toString()

            val sName = name.text.toString()
            val sPhone = phone.text.toString()
            val sPassword = password.text.toString()

            if(sName ==""){
                validate = false
                name.setError("Name Can not be Empty!")
            }

            if(sPhone ==""){
                validate = false
                name.setError("Contact Can not be Empty!")
            }

            if(sPassword ==""){
                validate = false
                name.setError("Password Can not be Empty!")
            }

            if(address ==""){
                validate = false
                Toast.makeText(this, "Select Address", Toast.LENGTH_SHORT).show()
            }

            if (validate){
                val helperclass = UserHelperClass(sName,sPhone,address,account_type,sPassword)
                reference.child(sPhone).setValue(helperclass)

                Toast.makeText(this, "New Account Created Successfully!", Toast.LENGTH_LONG).show()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish();

            }

        }

        btn_login.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish();
        }


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
//        input_address.text = text
//        Toast.makeText(this, "$text selected", Toast.LENGTH_SHORT).show()
        address = text
    }

    override fun onBackPressed() {
        finish()
    }


}