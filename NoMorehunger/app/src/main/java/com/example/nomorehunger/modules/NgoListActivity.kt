package com.example.nomorehunger.modules

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nomorehunger.MainActivity
import com.example.nomorehunger.MyAdapter
import com.example.nomorehunger.R
import com.google.firebase.database.*
import com.google.firebase.database.core.RepoManager.clear

class NgoListActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Ngo_List>

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
        setContentView(R.layout.activity_ngo_list)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        title = "NGO LIST"
        primary_number = intent.getStringExtra("NUMBER").toString()
        primary_name = intent.getStringExtra("NAME").toString()
        primary_address = intent.getStringExtra("ADDRESS").toString()

        val btn_add = findViewById<Button>(R.id.ngo_list_btn_add)
        val btn_show_list = findViewById<Button>(R.id.ngo_list_btn_back)
        val btn_delete = findViewById<Button>(R.id.ngo_list_btn_delete)


        progressDialog = ProgressDialog(this@NgoListActivity)
        progressDialog.show()
        progressDialog.setContentView(R.layout.progres_dialogue)

        userRecyclerView = findViewById(R.id.recycler_ngo_list)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<Ngo_List>()
        getUserdata()


        btn_add.setOnClickListener {
            val btn_submit = findViewById<Button>(R.id.ngo_list_btn_save)
            val ngo_list = findViewById<LinearLayout>(R.id.ngo_list_linear_layout)
            val rec = findViewById<RecyclerView>(R.id.recycler_ngo_list)

            val sPhone = findViewById<TextView>(R.id.ngo_list_tv_phone)
            sPhone.text = "Contact :  $primary_number"


            rec.visibility = View.GONE
            ngo_list.visibility = View.VISIBLE

            btn_submit.setOnClickListener {
                var validate = true

                var sName = findViewById<EditText>(R.id.ngo_list_input_name)
                var sDesc = findViewById<EditText>(R.id.ngo_list_input_desc)
                val sPhone = findViewById<TextView>(R.id.ngo_list_tv_phone)




                sPhone.text = primary_number


                if(sName.text.toString()==""){
                    validate = false
                    sName.setError("Name Can't be Empty")
                }

                if(validate){
                    rootNode = FirebaseDatabase.getInstance()
                    reference = rootNode.getReference("NgoList")

                    val helperclass = NgoHelperClass(sName.text.toString(), primary_number,
                        sDesc.text.toString())
                    if (primary_number != null) {
                        reference.child(primary_number).setValue(helperclass)
                    }



                    Toast.makeText(this, "Your Data Added Successfully !", Toast.LENGTH_LONG).show()
                    ngo_list.visibility = View.GONE
                    rec.visibility = View.VISIBLE

//                    TO REFRESH ACTIVITY WITHOUT BLINK

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);

                }
            }

        }

        btn_show_list.setOnClickListener {
            val ngo_list = findViewById<LinearLayout>(R.id.ngo_list_linear_layout)
            val rec = findViewById<RecyclerView>(R.id.recycler_ngo_list)
            ngo_list.visibility = View.GONE
            rec.visibility = View.VISIBLE

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

        btn_delete.setOnClickListener {
            val myDatabase = FirebaseDatabase.getInstance().getReference("NgoList")
            myDatabase.child(primary_number).removeValue()
            Toast.makeText(this, "Value Deleted", Toast.LENGTH_SHORT).show()

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }



    }

    private fun getUserdata() {
        dbref = FirebaseDatabase.getInstance().getReference("NgoList")
        dbref.addValueEventListener((object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    progressDialog.dismiss()
                    for(userSnapshot in  snapshot.children){

                        val user = userSnapshot.getValue(Ngo_List::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }))
    }

    override fun onBackPressed() {
        finish()
        intent = Intent(this,MainActivity::class.java)
        intent.putExtra("NUMBER",primary_number)
        intent.putExtra("NAME",primary_name)
        intent.putExtra("ADDRESS",primary_address)
        startActivity(intent)
    }



}