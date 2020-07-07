package kr.ac.kpu.coronacheck

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    // 비밀번호 정규식
    private val PASSWORD_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$")

    // 파이어베이스 인증 객체 생성
    private val firebaseAuth : FirebaseAuth ? = null

    // 이메일과 비밀번호
    private val editTextEmail: EditText? = null
    private val editTextPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlogin.setOnClickListener{
            val intent= Intent(this,CheckList::class.java)
            startActivity(intent)
        }

    }
}