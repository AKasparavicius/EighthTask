package lt.arnas.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import lt.arnas.notes.databinding.ActivityMainBinding
import lt.arnas.notes.databinding.NoteBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private var noteIndex = -1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notes = mutableListOf<Note>()
        generateListOfNotes(notes)

        addAdapter(notes)
        binding.noteListView.adapter = adapter

        setClickOpenNoteDetails()
        setClickOpenNoteActivity()


    }
    private fun addAdapter(notes: MutableList<Note>) {
        adapter = CustomAdapter(this)
        adapter.add(notes)
    }

    private fun generateListOfNotes(notes: MutableList<Note>) {
        for (note in 1..5) {
            notes.add(
                Note(
                    note,
                    "text01%04x".format(note),
                    "text02%09x".format(note)
                )
            )
        }
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note
            noteIndex = position

            val itemIntent = Intent(this, EditNote::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, note.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, note.name)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, note.details)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_DATE, note.updateDetails(note.name,note.details))

            startActivityForResult.launch(itemIntent)
        }
    }

    private fun setClickOpenNoteActivity() {
        binding.addNote.setOnClickListener {
            startActivityForResult.launch(Intent(this, EditNote::class.java))
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                EditNote.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    val note = Note(
                        id = 111,
                        name = result.data
                            ?.getStringExtra(EditNote.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        details = result.data
                            ?.getStringExtra(EditNote.SECOND_ACTIVITY_ITEM_TEXT02) ?: "",
                    )
                    adapter.add(note)
                }
                EditNote.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    val note = Note(
                        id = result.data
                            ?.getIntExtra(EditNote.SECOND_ACTIVITY_ITEM_ID, 0) ?: 0,
                        name = result.data
                            ?.getStringExtra(EditNote.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        details = result.data
                            ?.getStringExtra(EditNote.SECOND_ACTIVITY_ITEM_TEXT02) ?: ""
                    )
                    if (noteIndex >= 0) {
                        adapter.update(noteIndex, note)
                    }
                }
            }
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.arnas.notes_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.arnas.notes_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.arnas.notes_item_text02"
        const val MAIN_ACTIVITY_ITEM_DATE = "package lt.arnas.notes_item_text02"
    }
}