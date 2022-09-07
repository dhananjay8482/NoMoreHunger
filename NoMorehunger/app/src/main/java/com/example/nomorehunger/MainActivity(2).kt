package com.example.nomorehunger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.nomorehunger.modules.NgoListActivity
import com.example.nomorehunger.modules.VolunteerActivity
import com.example.nomorehunger.modules.WasteFoodActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        title = "DASHBOARD"
        val primary_number = intent.getStringExtra("NUMBER")
        val primary_name = intent.getStringExtra("NAME")
        val primary_address = intent.getStringExtra("ADDRESS")
        val tv = findViewById<TextView>(R.id.tv_1)
        tv.text = "Name : $primary_name\nNumber : $primary_number"

        val btn_ngo_list = findViewById<Button>(R.id.dashboard_btn_ngo_list)
        val btn_volunteer_list = findViewById<Button>(R.id.dashboard_btn_volunteer_list)
        val btn_waste_food = findViewById<Button>(R.id.dashboard_btn_waste_food)
        val daily_events = findViewById<Button>(R.id.dashboard_btn_daily_events)
        val btn_daily_food = findViewById<Button>(R.id.dashboard_btn_daily_food)

        btn_ngo_list.setOnClickListener {
            finish()
            intent = Intent(this, NgoListActivity::class.java)
            intent.putExtra("NUMBER",primary_number)
            intent.putExtra("NAME",primary_name)
            startActivity(intent)

        }

        btn_volunteer_list.setOnClickListener {
            finish()
            intent = Intent(this, VolunteerActivity::class.java)
            intent.putExtra("NUMBER",primary_number)
            intent.putExtra("NAME",primary_name)
            intent.putExtra("ADDRESS",primary_address)
            startActivity(intent)
        }

        btn_waste_food.setOnClickListener {
            finish()
            intent = Intent(this, WasteFoodActivity::class.java)
            intent.putExtra("NUMBER",primary_number)
            intent.putExtra("NAME",primary_name)
            intent.putExtra("ADDRESS",primary_address)
            startActivity(intent)
        }





    }

    override fun onBackPressed() {
        finish()
    }

}