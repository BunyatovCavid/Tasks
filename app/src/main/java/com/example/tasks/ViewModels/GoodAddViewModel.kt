package com.example.tasks.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tasks.DataClass.ItemDataClass
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GoodAddViewModel: ViewModel() {

    suspend fun getDatas(): List<ItemDataClass> {
        val db = FirebaseFirestore.getInstance()
        val datas = mutableListOf<ItemDataClass>()

        try {
            val result = db.collection("items").get().await()
            for (document in result) {
                val item = document.toObject(ItemDataClass::class.java)
                datas.add(item)
            }
        } catch (e: Exception) {
            Log.w("Data111", "Error getting documents.", e)
        }

        return datas
    }



}