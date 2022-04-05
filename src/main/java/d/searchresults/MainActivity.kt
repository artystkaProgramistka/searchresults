package d.searchresults

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //private var question: TextView? = null
    private var answer: TextView? = null
    private var text: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*text?.setOnClickListener {
            // your code to perform when the user clicks on the TextView
            this.setText()
        }*/


    }

    fun getSet(view: View) {
        var question = findViewById<EditText>(R.id.question)
        text = question.text.toString();
        answer = findViewById<TextView>(R.id.answer)
        val dw = WebScrap()
        dw.execute()
    }

    inner class WebScrap : AsyncTask<Void, Void, Void>() {
        private lateinit var words: String
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            var document: Document? = null
            try {
                text = "https://www.google.com/search?q=" + text
                //var text = question.toString() + "https://www.google.com/search?q=domi"
                document = Jsoup.connect(text).get()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val elements = document!!.getElementsByClass("LHJvCe")
            words = elements.text();

            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            answer?.text = words
        }
    }
}