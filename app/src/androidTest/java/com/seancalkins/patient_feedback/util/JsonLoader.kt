package com.seancalkins.patient_feedback.todo_list.data.remote

import android.content.Context
import android.util.Log

object JsonLoader {

    val TAG: String = JsonLoader::class.java.simpleName

    fun loadJsonIntoMemory(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e(TAG, "Exception", e)
            "Invalid File Name: $fileName"
        }
    }
}