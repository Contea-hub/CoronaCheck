package kr.ac.kpu.coronacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 9001       //google login result
    private var firebaseAuth: FirebaseAuth? = null      //firebase auth

    private fun createEmail(){
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
        }
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
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "signInWithEmail failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()

        btnlogin.setOnClickListener{        //로그인 버튼
            loginEmail()
        }
        btnsignin.setOnClickListener{       //회원가입 버튼
            createEmail()
        }
    }
}