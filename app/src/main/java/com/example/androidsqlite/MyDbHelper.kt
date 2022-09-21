package com.example.androidsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object Contact: BaseColumns {
    const val TABLE_NAME = "contacts"
    const val COLUMN_NAME = "name"
    const val COLUMN_EMAIL = "email"
}

private const val SQL_CREATE = """
    create table if not exists ${Contact.TABLE_NAME}
    (${BaseColumns._ID} integer primary key autoincrement,
    ${Contact.COLUMN_NAME} text,
    ${Contact.COLUMN_EMAIL} text);"""

private const val SQL_DELETE = "drop table ${Contact.TABLE_NAME};"

class MyDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "my_database.db"
    }

}

