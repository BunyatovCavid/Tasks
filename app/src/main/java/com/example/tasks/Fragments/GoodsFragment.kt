package com.example.tasks.Fragments

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.Adapters.ItemAdapterclass
import com.example.tasks.DataClass.ItemDataClass
import com.example.tasks.R
import com.example.tasks.ViewModels.GoodAddViewModel
import com.example.tasks.databinding.FragmentGoodsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
class GoodsFragment : Fragment(), ItemAdapterclass.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var _data :MutableList<ItemDataClass>

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

        var view =inflater.inflate(R.layout.fragment_goods, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var recycle = view.findViewById<RecyclerView>(R.id.recycle)

        _data = mutableListOf<ItemDataClass>()
        var vm = GoodAddViewModel()
        lateinit var adapter:ItemAdapterclass

 lifecycleScope.launch {
     val data = vm.getDatas()
     withContext(Dispatchers.Main) {
         data.forEach() { item ->
             _data.add(item)
         }
     }

     adapter = ItemAdapterclass(_data)
     recycle.layoutManager = GridLayoutManager(context, 2)
     recycle.adapter = adapter
     super.onViewCreated(view, savedInstanceState)
     adapter.a = {
        var bundle:Bundle = Bundle()
         bundle.putString("itemtitle", adapter.datas[it].title)
         bundle.putString("itemimage",adapter.datas[it].image)
         var detailfrg:GoodDetailFragment = GoodDetailFragment()
         detailfrg.arguments = bundle
         fragmentManager?.beginTransaction()?.replace(R.id.container, detailfrg)?.commit()


//        var intent = Intent(context, GoodDetailFragment::class.java)
//         var bundle = bundleOf("data" to adapter.datas[it])
//         intent.putExtras(bundle)
//         startActivity(intent)
     }
 }
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

    override fun onItemClick(position: Int){
    }


}