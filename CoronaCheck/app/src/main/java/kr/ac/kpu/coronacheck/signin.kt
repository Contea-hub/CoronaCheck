package kr.ac.kpu.coronacheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*

class signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val intent = getIntent()
        var name : String? = null
        var stunum : String? =null
        var subsel : String? =null
        var password : String? =null

        btnfinish.setOnClickListener {
            if(Edtname.text.toString() == "" || edtnum.text.toString() == "" || rdgsubject.checkedRadioButtonId == -1 ||
                    edtpassword.text.toString() == "" || edtckpassword.text.toString() == ""){
                Toast.makeText(this, "Not Fill Question",Toast.LENGTH_SHORT).show()
            }else if(edtpassword.text.toString() != edtckpassword.text.toString()){
                Toast.makeText(this, "Input Same Password",Toast.LENGTH_SHORT).show()
            }else {
                name = Edtname.text.toString()
                stunum = edtnum.text.toString()
                if (pos2.isChecked) {
                    subsel = "Firebase"
                } else if (neg2.isChecked) {
                    subsel = "IOS Swift"
                }
                password = edtpassword.text.toString()
                val rIntent = Intent(this, MainActivity::class.java)
                rIntent.putExtra("name", name)
                rIntent.putExtra("stunum",stunum)
                rIntent.putExtra("subsel", subsel)
                rIntent.putExtra("password", password)
                setResult(Activity.RESULT_OK, rIntent)
                Toast.makeText(this, "ID Create Complete",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}