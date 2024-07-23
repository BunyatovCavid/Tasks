package com.example.tasks.Fragments

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.Adapters.ItemAdapterclass
import com.example.tasks.DataClass.ItemDataClass
import com.example.tasks.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.reflect.typeOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [GoodsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GoodsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var _data :MutableList<ItemDataClass>
    lateinit var firebase:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goods, container, false)

        val recycle = view.findViewById<RecyclerView>(R.id.recycle)


        _data = mutableListOf<ItemDataClass>()
        CoroutineScope(Dispatchers.IO).launch {
            val data = getDatas()
            withContext(Dispatchers.Main) {
                data.forEach(){
                    item->
                    _data.add(item)
                }

                recycle.layoutManager = GridLayoutManager(context, 2)
                recycle.adapter = ItemAdapterclass(_data.toList())
                Log.d("Data1452", data.toString())
            }
        }



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GoodsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GoodsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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