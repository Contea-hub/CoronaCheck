package kr.ac.kpu.coronacheck


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    // 비밀번호 정규식
    private val PASSWORD_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$")

    // 파이어베이스 인증 객체 생성
    private var firebaseAuth: FirebaseAuth? = null
    val firebaseInput = UserInfo("21312312","박다수")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance()

        btnlogin.setOnClickListener {
            singIn()
        } //버튼


          btnlogin2.setOnClickListener{
              singUp()
          }

    } //onCreate 닫는 괄호


   data class UserInfo(
       val id: String = "",
       val name: String = ""
   )


    fun singUp() {
        var studentnum = editTextTextEmailAddress!!.text.toString()+"@gogle.com"
        var password = editTextTextPassword!!.text.toString()
        if (isValidEmail() && isValidPasswd()) {
            createUser(studentnum, password)
        }
    }

    //로그인 함수
    fun singIn(): Boolean {
        var studentnum = editTextTextEmailAddress?.text.toString()+"@gogle.com"
        var password = editTextTextPassword?.text.toString()

        if (isValidEmail() && isValidPasswd()) {
            loginUser(studentnum, password)
            return true
        }
        else{
            return false
        }
    }



    private fun isValidEmail(): Boolean {
        return if (editTextTextEmailAddress?.text.toString().isEmpty()) {
            // 학번 공백
            Toast.makeText(this@MainActivity, "학번을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text.toString()+"@gogle.com").matches()) {
            // 학번 불일치
            false
        } else {
            true
        }
    }

    // 비밀번호 유효성 검사
    private fun isValidPasswd(): Boolean {
        return if (editTextTextPassword?.text.toString().isEmpty()) {
            // 비밀번호 공백
            Toast.makeText(this@MainActivity, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            false
        } else if (!PASSWORD_PATTERN.matcher(editTextTextPassword.text.toString()).matches()) {
            // 비밀번호 형식 불일치
            false
        } else {
            true
        }
    }


    private fun createUser(email: String, password: String) {
        firebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공
                    Toast.makeText(this@MainActivity, "회원가입 완료", Toast.LENGTH_SHORT).show()
                } else {
                    // 회원가입 실패
                    Toast.makeText(this@MainActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun loginUser(email: String, password: String) {
        firebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    val intent = Intent(this, CheckList::class.java)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    // 로그인 실패
                    Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

} //메인문