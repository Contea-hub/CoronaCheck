package kr.ac.kpu.coronacheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_check_list.*

class CheckList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list)
        val intent = getIntent()
        var stunum = intent.getStringExtra("stunum")
        var q1 : Boolean? = null
        var q2 : Boolean? = null
        var q3 : Boolean? = null
        var q4 : Boolean? = null
        if(pos1.isChecked){
            q1 = true
        }else if(neg1.isChecked){
            q1 = false
        }   //2번 선택지

        if(pos2.isChecked){
            q2 = true
        }else if(neg2.isChecked){
            q2 = false
        }   //3번 선택지

        if(pos3.isChecked){
            q3 = true
        }else if(neg3.isChecked){
            q3 = false
        }   //4번 선택지

        if(pos4.isChecked){
            q4 = true
        }else if(neg4.isChecked){
            q4 = false
        }   //5번 선택지

        btnsubmit.setOnClickListener {      //누르면 설문 결과가 메인 액티비티에 전송
            if(Edtname.equals("") || rdgcheck2.checkedRadioButtonId == -1 || rdgsubject.getCheckedRadioButtonId() == -1 ||
                rdgcheck4.getCheckedRadioButtonId() == -1 || rdgcheck5.getCheckedRadioButtonId() == -1 ){
                Toast.makeText(this, "선택지가 다 선택되지 않았습니다", Toast.LENGTH_SHORT).show()
            }else {
                val rIntent = Intent(this, MainActivity::class.java)
                rIntent.putExtra("stunum", stunum)
                rIntent.putExtra("q1", q1)
                rIntent.putExtra("q2", q2)
                rIntent.putExtra("q3", q3)
                rIntent.putExtra("q4", q4)
                rIntent.putExtra("temp", Edtname.text.toString())
                setResult(Activity.RESULT_OK, rIntent)
                finish()
            }
        }
    }
}