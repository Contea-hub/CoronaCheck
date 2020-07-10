package kr.ac.kpu.coronacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list_student.*
import kotlinx.android.synthetic.main.activity_main.*

class ListStudent : AppCompatActivity() {
    var studentList= arrayListOf<student>(
        student("박다수","2016150016","firebase",true),
    student("정지운","2016150036","firebase",true),
    student("응옌뒤홍","2016150041","iOS",true)
    )

    private lateinit var database : DatabaseReference
    private var mDatabase : DatabaseReference? = null
    private var mMessageReference : DatabaseReference? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference()
        database = Firebase.database.reference
        //database.child("change").child("change").setValue("0")


        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val name = it.child("name").value.toString()
                    val stunum = it.child("stunum").value.toString()
                    val subsel = it.child("subsel").value.toString()
                    studentList.add(student(name, stunum, subsel, true ))
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        mMessageReference!!.addValueEventListener(postListener)

        refreshButton.setOnClickListener {
            val mAdapter=adapterStudent(this,studentList)
            studentRV.adapter=mAdapter
            val lm=LinearLayoutManager(this)
            studentRV.layoutManager=lm
            studentRV.setHasFixedSize(true)
        }

        val mAdapter=adapterStudent(this,studentList)
        studentRV.adapter=mAdapter

        val lm=LinearLayoutManager(this)
        studentRV.layoutManager=lm
        studentRV.setHasFixedSize(true)

    }
}