package kr.ac.kpu.coronacheck

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class adapterStudent(val context: Context, val rowItems:List<student>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        var holder: ViewHolder? = null

        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        holder = ViewHolder()

        val view:View = LayoutInflater.from(context).inflate(R.layout.item_student,null)

        holder.name=view.findViewById<TextView>(R.id.txtname)
        holder.number=view.findViewById<TextView>(R.id.txtnum)
        holder.subject=view.findViewById<TextView>(R.id.txtsubject)
        holder.attend=view.findViewById<CheckBox>(R.id.check)


        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_student, null)

            holder.name = convertView.findViewById(R.id.txtname) as TextView
            holder.number = convertView.findViewById(R.id.txtnum) as TextView
            holder.subject = convertView.findViewById(R.id.txtsubject) as TextView
            holder.attend = convertView.findViewById(R.id.check) as CheckBox

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val rowItem = rowItems[position]
        val name : String
        val number : String
        val subject : String
        val attend : Boolean

        holder.name!!.text = rowItem.name
        holder.number!!.text = rowItem.number
        holder.subject!!.text = rowItem.subject
        when(rowItem.checking){
            true->holder.attend!!.isChecked=true
            false->holder.attend!!.isChecked=false
        }

        holder.name!!.setOnClickListener(object : View.OnClickListener {
            internal var buttonClickFlag: Boolean = false

            override fun onClick(v: View) {           //The Onclick function allows one to click the button on the list item and set/reset the canViewYouOnline flag. It is working fine.
                Toast.makeText(context, "이건 ${holder.name!!.text} ", Toast.LENGTH_SHORT).show()

            }

        })



        return convertView
    }

    override fun getItem(position: Int): Any {
        return rowItems[position]
    }

    override fun getItemId(position: Int): Long {
        return rowItems.indexOf(getItem(position)).toLong()
    }

    override fun getCount(): Int {
        return rowItems.size
    }

    private inner class ViewHolder {
        internal var name : TextView? = null
        internal var number : TextView? = null
        internal var subject : TextView? = null
        internal var attend : CheckBox? = null
    }

}