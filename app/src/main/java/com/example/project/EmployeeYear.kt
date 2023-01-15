package com.example.project

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap

 class EmployeeYear() : ContentProvider() {

    companion object {
        val PROVIDER_NAME = "com.example.MyApplication.StudentsProvider"
        val URL = "content://" + PROVIDER_NAME + "/students"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val NAME = "name"
        val GRADE = "grade"
        private val STUDENTS_PROJECTION_MAP: HashMap<String, String>? = null
        val STUDENTS = 1
        val STUDENT_ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "College"
        val STUDENTS_TABLE_NAME = "students"
        val DATABASE_VERSION = 1
        val CREATE_DB_TABLE = " CREATE TABLE " + STUDENTS_TABLE_NAME +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL, " +
                " grade TEXT NOT NULL);"
        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        init    {
            sUriMatcher.addURI(PROVIDER_NAME, "employeeyear", STUDENTS);
            sUriMatcher.addURI(PROVIDER_NAME, "employeeyear/#", STUDENT_ID);
        }
    }
     private var db: SQLiteDatabase? = null

     private class DatabaseHelper internal constructor(context: Context?) :
         SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



         override fun onCreate(db: SQLiteDatabase) {
         db.execSQL(CREATE_DB_TABLE)
        }    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
         db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME)
         onCreate(db)
        }
         override fun onCreate(): Boolean {
             val context = context
             val dbHelper = DatabaseHelper(context)


             db = dbHelper.writableDatabase
             return if (db == null) false else true
         }

         override fun getType(uri: Uri): String? {
             when (uriMatcher!!.match(uri)) {
             STUDENTS -> return "vnd.android.cursor.dir/vnd.example.students"
             STUDENT_ID -> return "vnd.android.cursor.item/vnd.example.students"
             else -> throw IllegalArgumentException("Unsupported URI: $uri")
         }
         }
         override fun update(
             uri: Uri,
             values: ContentValues?,
             selection: String?,
             selectionArgs: Array<String>?
         ): Int {
             var count = 0
             when (uriMatcher!!.match(uri)) {
             STUDENTS -> count = db!!.update(
             STUDENTS_TABLE_NAME, values, selection,
             selectionArgs
             )
             STUDENT_ID -> count = db!!.update(
             STUDENTS_TABLE_NAME,
             values,
             _ID + " = " + uri.pathSegments[1] + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""),
             selectionArgs
             )
             else -> throw IllegalArgumentException("Unknown URI $uri")
         }
             context!!.contentResolver.notifyChange(uri, null)
             return count
         }

     }

     }


