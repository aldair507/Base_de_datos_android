package com.example.basededatos.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BdHelper(
    context: Context?

) : SQLiteOpenHelper(context,Constants.NOM_DB,null,Constants.VERSION_BD) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.TABLA)
        db?.execSQL(Constants.TABLA2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db?.execSQL( "DROP TABLE IF EXISTS data")
        db?.execSQL( "DROP TABLE IF EXISTS ciudad")
        onCreate(db)
    }

}