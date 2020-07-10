package kr.ac.kpu.coronacheck

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_student.*


class ListStudent : AppCompatActivity() {
    var studentList= arrayListOf<student>(
    )
    var mDatabase: FirebaseDatabase? = null
    var mReference: DatabaseReference? = null
    var mChild: ChildEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)
        initDatabase()

        mReference = mDatabase!!.getReference("child 이름") // 변경값을 확인할 child 이름

        mReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (messageData in dataSnapshot.children) {
                    // child 내에 있는 데이터만큼 반복합니다.
                    val msg2 = messageData.value
                    studentList.add(msg2 as student)
                }
                (studentRV.adapter as adapterStudent).notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError){
            }

        })

        var data:Any
        var bundle:Bundle
        var intent: Intent

        val mAdapter=adapterStudent(this,studentList)
        studentRV.adapter=mAdapter

        val lm=LinearLayoutManager(this)
        studentRV.layoutManager=lm
        studentRV.setHasFixedSize(true)

    }


    fun initDatabase() {

        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase!!.getReference("log")
        mReference!!.child("log").setValue("check")

        mChild = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        mReference!!.addChildEventListener(mChild as ChildEventListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        this!!.mChild?.let { mReference?.removeEventListener(it) }
    }

} // 메인

