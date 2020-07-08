package kr.ac.kpu.coronacheck

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView

class adapterStudent(val context: Context, val studentList:ArrayList<student>) : BaseAdapter(){
    override fun getView(position: Int, convertiew: View?, parent: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_student,null)

        val name=view.findViewById<TextView>(R.id.txtname)
        val number=view.findViewById<TextView>(R.id.txtnum)
        val subject=view.findViewById<TextView>(R.id.txtsubject)
        val attend=view.findViewById<CheckBox>(R.id.check)

        val student=studentList[position]
        name.text=student.name
        number.text=student.number
        subject.text=student.subject
        when(student.checking){
            true->attend.isChecked=true
            false->attend.isChecked=false
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return studentList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return studentList.size
    }

}