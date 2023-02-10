package com.example.wetterbericht.data.local.database

import android.content.Context
import com.example.wetterbericht.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object LoadDataFromJson {
    fun loadHabitsJson(context: Context): JSONArray?{
        val builder = StringBuilder()
        val resources = context.resources.openRawResource(R.raw.habit)
        val reader = BufferedReader(InputStreamReader(resources))
        var line : String?
        try {
            while (reader.readLine().also { line = it } != null){
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("habits")
        }catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }

    fun loadIconHabits(context: Context): JSONArray?{
        val builder = StringBuilder()
        val resources = context.resources.openRawResource(R.raw.iconhabits)
        val reader = BufferedReader(InputStreamReader(resources))
        var line : String?
        try {
            while (reader.readLine().also { line = it } != null){
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("icons")
        }catch (e : IOException){
            e.printStackTrace()
        }catch (e : JSONException){
            e.printStackTrace()
        }
        return null
    }

    fun loadAlarmChip(context: Context): JSONArray?{
        val builder = StringBuilder()
        val resources = context.resources.openRawResource(R.raw.chipalarm)
        val reader = BufferedReader(InputStreamReader(resources))
        var line : String?
        try {
            while (reader.readLine().also { line = it } != null){
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("alarm")
        }catch (e : IOException){
            e.printStackTrace()
        }catch (e : JSONException){
            e.printStackTrace()
        }
        return null
    }

    fun loadSubTaskJson(context: Context): JSONArray?{
        val builder = StringBuilder()
        val resources = context.resources.openRawResource(R.raw.task)
        val reader = BufferedReader(InputStreamReader(resources))
        var line : String?
        try {
            while (reader.readLine().also { line = it } != null){
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("subtask")
        }catch (e : IOException){
            e.printStackTrace()
        }catch (e : JSONException){
            e.printStackTrace()
        }
        return null
    }
    fun loadColoHabitsJson(context: Context): JSONArray?{
        val builder = StringBuilder()
        val resources = context.resources.openRawResource(R.raw.colorhabits)
        val reader = BufferedReader(InputStreamReader(resources))
        var line : String?
        try {
            while (reader.readLine().also { line = it } != null){
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("colors")
        }catch (e : IOException){
            e.printStackTrace()
        }catch (e : JSONException){
            e.printStackTrace()
        }
        return null
    }
}