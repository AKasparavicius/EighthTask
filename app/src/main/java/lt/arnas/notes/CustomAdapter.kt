package lt.arnas.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(context: Context): BaseAdapter(){

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    fun add (vararg note: Note) {
        list.addAll(note)
        notifyDataSetChanged()
    }
    fun add (notes: List<Note>) {
        list.addAll(notes)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: inflater.inflate(R.layout.note, parent, false)

        view.findViewById<TextView>(R.id.idTextView).text = list[position].id.toString()
        view.findViewById<TextView>(R.id.text01TextView).text = list[position].name
        view.findViewById<TextView>(R.id.text02TextView).text = list[position].details
        view.findViewById<TextView>(R.id.text03TextView).text = list[position].creationDate.toString()

        return view
    }
}