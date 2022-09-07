package com.example.nomorehunger.modules

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nomorehunger.MainActivity
import com.example.nomorehunger.MyVAdapter
import com.example.nomorehunger.R
import com.google.firebase.database.*

class VolunteerActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Volunteer_List>

    lateinit var progressDialog: ProgressDialog

    internal lateinit var rootNode: FirebaseDatabase
    internal lateinit var reference: DatabaseReference

    val empty_string = ""
    var primary_number= ""
    var primary_name = ""
    var primary_address = ""


    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        title = "NGO LIST"
        primary_number = intent.getStringExtra("NUMBER").toString()
        primary_name = intent.getStringExtra("NAME").toString()
        primary_address = intent.getStringExtra("ADDRESS").toString()

        val btn_add = findViewById<Button>(R.id.volunteer_btn_add)
        val btn_show_list = findViewById<Button>(R.id.volunteer_btn_back)
        val btn_delete = findViewById<Button>(R.id.volunteer_btn_delete)

        progressDialog = ProgressDialog(this@VolunteerActivity)
        progressDialog.show()
        progressDialog.setContentView(R.layout.progres_dialogue)

        userRecyclerView = findViewById(R.id.recycler_volunteer_list)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<Volunteer_List>()
        getUserdata()


        btn_add.setOnClickListener {
            val btn_submit = findViewById<Button>(R.id.volunteer_btn_save)
            val volunteer_list = findViewById<LinearLayout>(R.id.volunteer_list_linear_layout)
            val rec = findViewById<RecyclerView>(R.id.recycler_volunteer_list)

            val sPhone = findViewById<TextView>(R.id.volunteer_tv_phone)
            val sName = findViewById<TextView>(R.id.volunteer_tv_name)
            sName.text =  "Name    :  $primary_name"
            sPhone.text = "Contact :  $primary_number"


            rec.visibility = View.GONE
            volunteer_list.visibility = View.VISIBLE

            btn_submit.setOnClickListener {
                var validate = true

//                var sName = findViewById<EditText>(R.id.ngo_list_input_name)
                val sDesc = findViewById<EditText>(R.id.ngo_list_input_desc)
//                val sPhone = findViewById<TextView>(R.id.ngo_list_tv_phone)




                sPhone.text = primary_number


                if(sDesc.text.toString()==""){
                    validate = false
                    sDesc.setError("Time Slot Must.")
                }

                if(validate){
                    rootNode = FirebaseDatabase.getInstance()
                    reference = rootNode.getReference("VolunteerList")

                    val helperclass = VolunteerHelperClass(primary_name, primary_number,
                        sDesc.text.toString(),primary_address)
                    reference.child(primary_number).setValue(helperclass)



                    Toast.makeText(this, "Your Data Added Successfully !", Toast.LENGTH_LONG).show()
                    volunteer_list.visibility = View.GONE
                    rec.visibility = View.VISIBLE

//                    TO REFRESH ACTIVITY WITHOUT BLINK

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);

                }
            }

        }



        btn_delete.setOnClickListener {
            val myDatabase = FirebaseDatabase.getInstance().getReference("VolunteerList")
            myDatabase.child(primary_number).removeValue()
            Toast.makeText(this, "Value Deleted", Toast.LENGTH_SHORT).show()

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }


        btn_show_list.setOnClickListener {
            val ngo_list = findViewById<LinearLayout>(R.id.volunteer_list_linear_layout)
            val rec = findViewById<RecyclerView>(R.id.recycler_volunteer_list)
            ngo_list.visibility = View.GONE
            rec.visibility = View.VISIBLE

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }




    }

    private fun getUserdata() {
        dbref = FirebaseDatabase.getInstance().getReference("VolunteerList")
        dbref.addValueEventListener((object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    progressDialog.dismiss()
                    for(userSnapshot in  snapshot.children){

                        val user = userSnapshot.getValue(Volunteer_List::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = MyVAdapter(userArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }))
    }

    override fun onBackPressed() {
        finish()
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("NUMBER",primary_number)
        intent.putExtra("NAME",primary_name)
        intent.putExtra("ADDRESS",primary_address)
        startActivity(intent)
    }


}