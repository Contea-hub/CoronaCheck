package kr.ac.kpu.coronacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_student.*

class ListStudent : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)

        var data:Any
        var bundle:Bundle
        var intent: Intent

        val studentList: MutableList<student> = ArrayList()
        studentList.add(student("박다수","2016150016","firebase",true))
        studentList.add(student("정지운","2016150036","firebase",true))
        studentList.add(student("응옌뒤홍","2016150041","iOS",true))

        val adapter = ArrayAdapter<student>(this,android.R.layout.simple_list_item_1,studentList)
        val studentAdapter = adapterStudent(baseContext, studentList)
        studentListview.adapter = studentAdapter

        studentListview.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id->
           val selecteditem = parent.getItemAtPosition(position) as student
            Toast.makeText(this, "이건 ${selecteditem.name} ", Toast.LENGTH_SHORT).show()
        }
    }
}

