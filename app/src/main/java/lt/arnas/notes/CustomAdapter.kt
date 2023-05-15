package lt.arnas.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class CustomAdapter(context: Context): BaseAdapter(){

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}