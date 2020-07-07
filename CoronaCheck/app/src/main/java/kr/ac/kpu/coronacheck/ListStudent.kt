package kr.ac.kpu.coronacheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)

        var studentList= arrayListOf<student>()

    }
}