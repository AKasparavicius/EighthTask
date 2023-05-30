package lt.arnas.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditNote : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var text01EditText: EditText
    lateinit var text02EditText: EditText
    lateinit var closeButton: Button
    lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)

        getIntentExtra()
        clickListenerSaveButton()
        clickListenerCloseButton()
    }

    private fun getIntentExtra() {

        val itemId: Int =
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)

        val itemText01 =
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01) ?: ""

        val itemText02 =
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02) ?: ""

        if (itemId >= 0) {
            idEditText.setText(itemId.toString())
            text01EditText.setText(itemText01)
            text02EditText.setText(itemText02)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
        }
    }

    private fun clickListenerSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }

    private fun clickListenerCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }

        companion object {
            const val SECOND_ACTIVITY_ITEM_ID = "package lt.arnas.notes.secondactivity_item_id"
            const val SECOND_ACTIVITY_ITEM_TEXT01 =
                "package lt.arnas.notes.secondactivity_item_text01"
            const val SECOND_ACTIVITY_ITEM_TEXT02 = "lt.arnas.notes.secondactivity_item_text02"
            const val SECOND_ACTIVITY_ITEM_DATE = "lt.arnas.notes.secondactivity_item_text03"
            const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
            const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
        }
}