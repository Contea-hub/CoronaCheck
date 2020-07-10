package kr.ac.kpu.coronacheck

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_student.*


class ListStudent : AppCompatActivity() {
    var studentList= arrayListOf<student>(
        student("박다수","2016150016","firebase",true),
    student("정지운","2016150036","firebase",true),
    student("응옌뒤홍","2016150041","iOS",true)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)

        var data:Any
        var bundle:Bundle
        var intent: Intent

        val mAdapter=adapterStudent(this,studentList)
        studentRV.adapter=mAdapter

        val lm=LinearLayoutManager(this)
        studentRV.layoutManager=lm
        studentRV.setHasFixedSize(true)



    }

}
