package com.example.androidsqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import com.example.androidsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        db = MyDbHelper(this).writableDatabase

        binding.button.setOnClickListener {
            val values = ContentValues().apply {
                put(Contact.COLUMN_NAME, listOf("Ivan", "Egor", "Matvei").shuffled().first())
                put(Contact.COLUMN_EMAIL, listOf("asdf@wegfe.ry", "fweg@greh.ry", "gtrhres@grjhtr.ry").shuffled().first())
            }
            val newContact = db.insert(Contact.TABLE_NAME, null, values)

            val cursor = db.query(Contact.TABLE_NAME, null, "${BaseColumns._ID} = ?", arrayOf(newContact.toString()), null, null, null)

            val cursor2 = db.rawQuery("select * from ${Contact.TABLE_NAME} where _id = ?", arrayOf(newContact.toString()))

            cursor.moveToFirst()

            val txt = "$newContact: ${cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME))} ${cursor.getString(cursor.getColumnIndex(Contact.COLUMN_EMAIL))}"
            cursor.close()
            cursor2.close()
            binding.tvMain.text = txt

        }


    }
}