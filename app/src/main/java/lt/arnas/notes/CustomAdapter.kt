package lt.arnas.notes

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import lt.arnas.notes.databinding.NoteBinding

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
    fun update(index: Int, note: Note) {
        list.set(index, note)
        notifyDataSetChanged()
    }


    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val binding: NoteBinding

        if (view == null) {
            binding = NoteBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as NoteBinding
        }

        binding.idTextView.text = list[position].id.toString()
        binding.text01TextView.text = list[position].name
        binding.text02TextView.text = list[position].details

        return view
    }
}