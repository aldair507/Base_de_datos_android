package com.example.basededatos.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class ManagerDb(val context:Context) {
    lateinit var bd:SQLiteDatabase

    val bdHelper=BdHelper(context) // llamando mi conexion

    // metodo para abrir la base de datos
    fun openDbWr(){
       bd=bdHelper.writableDatabase
    }
    // abre  la base de datos  en modo lectura
    fun openBdRd(){
        bd=bdHelper.readableDatabase
    }

    fun insertData(dep: Int,nombre: String, codedep:Int):Long{

        openDbWr() // abrir en modo escritura

        // se crea contenedores para insertar datos
        val contenedor= ContentValues()
        contenedor.put("cod","$dep")
        contenedor.put("nombre","$nombre")
        contenedor.put("codeDep","$codedep")

        // llamo metodo insert
        val result:Long=bd.insert("ciudad", null, contenedor)
        return result


    }

    fun inserData2(code:Int,nombre:String,apellido:String,telefono:Int,direccion:String,ciudad:String): Long {

        openDbWr() // abrir en modo escritura

        // se crea contenedores para insertar datos
        val contenedor= ContentValues()
        contenedor.put("cod","$code")
        contenedor.put("nombre","$nombre")
        contenedor.put("apellido","$apellido")
        contenedor.put("telefono","$telefono")
        contenedor.put("direccion","$direccion")

        contenedor.put("ciudad","$ciudad")

        // llamo metodo insert
        val result:Long=bd.insert("data", null, contenedor)
        return result
    }

    fun insertData3(nombre: String, latitude: Double, longitude: Double): Long {
        openDbWr()// abrir bd en modo escritura
        //Creo contenedor de valores para insertar data
        val contenedor = ContentValues()
        contenedor.put("nombre", nombre)
        contenedor.put("latitude", latitude)
        contenedor.put("longitude", longitude)
        //llamo metodo insert

        val resul = bd.insert("places", null, contenedor)

        return resul

    }

}