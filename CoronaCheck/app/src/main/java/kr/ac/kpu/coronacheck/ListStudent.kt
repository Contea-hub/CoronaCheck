package kr.ac.kpu.coronacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_list_student.*

class ListStudent : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)

        var data:Any
        var bundle:Bundle
        var intent: Intent
        val studentAdapter=adapterStudent(this,studentList)
        val listview: ListView =findViewById(R.id.studentListview)
        studentListview.adapter=studentAdapter

        listview.setOnItemClickListener{ parent, view, position, id->
            val selectedItem=studentAdapter.getItemId(position)
            intent= Intent(this,studentinformation::class.java)
            startActivity(intent)

        val adapter = ArrayAdapter<student>(this,android.R.layout.simple_list_item_1,studentList)
        val studentAdapter = adapterStudent(baseContext, studentList)
        studentListview.adapter = studentAdapter

        studentListview.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id->
           val selecteditem = parent.getItemAtPosition(position) as student
            Toast.makeText(this, "이건 ${selecteditem.name} ", Toast.LENGTH_SHORT).show()
        }
    }
}

