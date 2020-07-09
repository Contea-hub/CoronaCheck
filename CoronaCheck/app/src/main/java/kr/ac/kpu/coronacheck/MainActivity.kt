package kr.ac.kpu.coronacheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 9001       //google login result
    private var firebaseAuth: FirebaseAuth? = null      //firebase auth
    //var firestore : FirebaseFirestore? = FirebaseFirestore.getInstance()           //firestroe 선언
    private lateinit var database : DatabaseReference
    private var mDatabase : DatabaseReference? = null
    private var mMessageReference : DatabaseReference? =null
    data class UserInfo(var stunum : String? = null, var temp : String? =null, var q1 : Boolean? = null, var q2 : Boolean? =null,
    var q3 : Boolean? = null, var q4 : Boolean? =null)      //DB 유저 정보 저장 유형
    private fun createEmail(){
        /*
        if(editTextTextEmailAddress.text.toString() == "" || editTextTextPassword.text.toString() == ""){
            Toast.makeText(this, "Authentication fail", Toast.LENGTH_SHORT).show()
        }else {
            firebaseAuth!!.createUserWithEmailAndPassword(
                editTextTextEmailAddress.text.toString() + "@google.com",
                editTextTextPassword.text.toString()
            ).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = firebaseAuth?.currentUser
                    Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, signin::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Authentication fail", Toast.LENGTH_SHORT).show()
                }
            }
        }*/

    }
    private fun loginEmail(){
        if(editTextTextEmailAddress.text.toString() == "" || editTextTextPassword.text.toString() == ""){
            Toast.makeText(this, "signInWithEmail failed.", Toast.LENGTH_SHORT).show()
        }else {
            firebaseAuth!!.signInWithEmailAndPassword(
                editTextTextEmailAddress.text.toString() + "@google.com",
                editTextTextPassword.text.toString()
            ).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "signInWithEmail success.", Toast.LENGTH_SHORT).show()
                    val user = firebaseAuth?.currentUser
                    val intent = Intent(this, CheckList::class.java)
                    intent.putExtra("stunum", editTextTextEmailAddress.text.toString())
                    startActivityForResult(intent, 0)
                } else {
                    Toast.makeText(this, "signInWithEmail failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val stunum : String?
        var q1 : Boolean?
        var q2 : Boolean?
        var q3 : Boolean?
        var q4 : Boolean?
        val temp : String?

        if(resultCode == Activity.RESULT_OK){
            stunum = data!!.getStringExtra("stunum")
            q1 = data!!.getBooleanExtra("q1", true)
            q2 = data!!.getBooleanExtra("q2", true)
            q3 = data!!.getBooleanExtra("q3", true)
            q4 = data!!.getBooleanExtra("q4", true)
            temp = data!!.getStringExtra("temp")
            //Toast.makeText(this, "${stunum}, ${q1}, ${q2},${q3},${q4},${temp}", Toast.LENGTH_SHORT).show()
            var userInfo = UserInfo(stunum, temp, q1, q2, q3, q4)
            database = Firebase.database.reference
            database.child(stunum).setValue(userInfo)
            val postListener = object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val post = dataSnapshot.getValue()
                    Log.d("han",dataSnapshot.child(stunum).value.toString())        //로그에 학번 정보 표시
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            mMessageReference!!.addValueEventListener(postListener)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()//
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance()
            .getReference("${editTextTextEmailAddress.text.toString()}")
        btnlogin.setOnClickListener {        //로그인 버튼
            loginEmail()
        }
        btnsignin.setOnClickListener {       //회원가입 버튼
            createEmail()
        }
    }
}
