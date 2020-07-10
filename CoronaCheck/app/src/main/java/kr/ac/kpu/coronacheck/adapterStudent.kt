package kr.ac.kpu.coronacheck

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class adapterStudent(val context: Context, val studentList: ArrayList<student>) :
    RecyclerView.Adapter<adapterStudent.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterStudent.Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_student,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: adapterStudent.Holder, position: Int) {
        holder?.bind(studentList[position],context)
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Clicked: ${studentList.get(position).name}",Toast.LENGTH_SHORT).show()
        }
    }
    inner class Holder(itemView: View?/*, itemClick: (student)->Unit*/): RecyclerView.ViewHolder(itemView!!){
        val name=itemView?.findViewById<TextView>(R.id.txtname)
        val number=itemView?.findViewById<TextView>(R.id.txtnum)
        val subject=itemView?.findViewById<TextView>(R.id.txtsubject)
        val attend=itemView?.findViewById<CheckBox>(R.id.check)

        fun bind(stu:student,context:Context){
            name?.text=stu.name
            number?.text=stu.number
            subject?.text=stu.subject
            if(stu.checking)
                attend?.isChecked=true
            else
                attend?.isChecked=false
            //itemView.setOnClickListener{itemClick(studentList)}
        }
    }
}

