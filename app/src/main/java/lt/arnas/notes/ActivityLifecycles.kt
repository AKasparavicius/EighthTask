package lt.arnas.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

    open class ActivityLifeCycles : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onStart() {
            super.onStart()
        }

        override fun onResume() {
            super.onResume()
        }

        override fun onPause() {
            super.onPause()
        }

        override fun onStop() {
            super.onStop()
        }

        override fun onDestroy() {
            super.onDestroy()
        }

        override fun onRestart() {
            super.onRestart()
        }
    }