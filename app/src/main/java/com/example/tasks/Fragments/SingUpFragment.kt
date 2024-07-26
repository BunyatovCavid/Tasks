package com.example.tasks.Fragments

import android.app.ActivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tasks.Activities.MainActivity
import com.example.tasks.Activities.MainActivity3
import com.example.tasks.R
import com.example.tasks.ViewModels.AuthorizationViewModel
import com.example.tasks.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var firebaseauth: FirebaseAuth

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

       var view = inflater.inflate(R.layout.fragment_sing_up, container,false)

        var btn = view.findViewById<Button>(R.id.registerbtn)
        var go = view.findViewById<TextView>(R.id.initialSingUp)

        var vm = AuthorizationViewModel()


        btn.setOnClickListener{
            firebaseauth = FirebaseAuth.getInstance()
            var email = view.findViewById<EditText>(R.id.usernameinput2).text.toString()
            var password = view.findViewById<EditText>(R.id.passwordinput2).text.toString()
            vm.SignUp(email, password, this)
        }

        go.setOnClickListener{
            Log.d("Test", "Click listener is working")
            val transaction =requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerMain3,InitiallyFragment())
            transaction.commit()
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
         * @return A new instance of fragment SingUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}