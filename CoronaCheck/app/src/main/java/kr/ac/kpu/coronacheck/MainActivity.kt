package kr.ac.kpu.coronacheck

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    // 비밀번호 정규식
    private val PASSWORD_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$")

    // 파이어베이스 인증 객체 생성
    private var firebaseAuth: FirebaseAuth? = null

    // 이메일과 비밀번호
    private var editTextStudentNum: TextView? = null
    private var editTextPassword: TextView? = null

    private var studentnum = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance()

        editTextStudentNum = editTextTextEmailAddress
        editTextPassword = editTextTextPassword

        btnlogin.setOnClickListener {
            val intent = Intent(this, CheckList::class.java)
            startActivity(intent)
        } //버튼
    } //onCreate 닫는 괄호


    //회원가입 버튼 누르면
    fun singUp(view: View?) {
        studentnum = editTextStudentNum?.getText().toString()
        password = editTextPassword!!.text.toString()
        if (isValidEmail() && isValidPasswd()) {
            createUser(studentnum, password)
        }
    }

    //로그인 함수
    fun signIn(view: View?) {
        studentnum = editTextStudentNum?.getText().toString()
        password = editTextPassword!!.text.toString()
        if (isValidEmail() && isValidPasswd()) {
            loginUser(studentnum, password)
        }
    }


    // 학번 유효성 검사
    private fun isValidEmail(): Boolean {
        return if (studentnum.isEmpty()) {
            // 학번 공백
            Toast.makeText(this@MainActivity, "학번을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(studentnum).matches()) {
            // 학번 불일치
            false
        } else {
            true
        }
    }

    // 비밀번호 유효성 검사
    private fun isValidPasswd(): Boolean {
        return if (password.isEmpty()) {
            // 비밀번호 공백
            false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            false
        } else {
            true
        }
    }

    // 회원가입
    private fun createUser(email: String, password: String) {
        firebaseAuth!!.createUserWithEmailAndPassword(studentnum, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공
                    Toast.makeText(this@MainActivity, "회원가입 완료", Toast.LENGTH_SHORT).show()
                } else {
                    // 회원가입 실패
                    Toast.makeText(this@MainActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //로그인
    private fun loginUser(email: String, password: String) {
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    // 로그인 실패
                    Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

} //메인문