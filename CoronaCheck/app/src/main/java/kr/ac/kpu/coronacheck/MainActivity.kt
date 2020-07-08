package kr.ac.kpu.coronacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlogin.setOnClickListener{
            val intent= Intent(this,CheckList::class.java)
            startActivity(intent)
        }
        btnsignin.setOnClickListener{
            val intent= Intent(this,ListStudent::class.java)
            startActivity(intent)
        }
    }
}