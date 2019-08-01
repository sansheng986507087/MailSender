package teprinciple.yang.sendmaildemo

import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

import com.shidian.mail.SendMailUtil

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.toAddEt) as EditText
    }


    fun senTextMail(view: View) {
        SendMailUtil.send(editText!!.text.toString())
    }

    fun sendFileMail(view: View) {

        val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + "test.txt")
        var os: OutputStream? = null
        try {
            os = FileOutputStream(file)
            val str = "hello world"
            val data = str.toByteArray()
            os.write(data)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
            } catch (e: IOException) {
            }

        }
        SendMailUtil.send(file, editText!!.text.toString())
    }
}
