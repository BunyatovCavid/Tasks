package com.example.tasks.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tasks.Activities.MainActivity
import com.example.tasks.R
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InitiallyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitiallyFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var firebaseauth: FirebaseAuth
    lateinit var btn:Button

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
        var view =inflater.inflate(R.layout.fragment_initially, container, false)
        btn = view.findViewById<Button>(R.id.signinbtn)
        var go = view.findViewById<TextView>(R.id.initialSingUp)


        var parent = container!!.parent
        parent.clearChildFocus(container)


        btn.setOnClickListener{
            firebaseauth = FirebaseAuth.getInstance()
            var email = view.findViewById<EditText>(R.id.usernameinput).text.toString()
            var password = view.findViewById<EditText>(R.id.passwordinput).text.toString()
            SingIn(email, password)

        }
        go.setOnClickListener(){
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerMain3,SingUpFragment())
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
         * @return A new instance of fragment InitiallyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InitiallyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun SingIn( email:String, password:String) {
        firebaseauth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(){task->
                if(task.isSuccessful)
                {
                   var intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(context, "SingIn is Successful", Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(context, "SingIn is Failed", Toast.LENGTH_LONG ).show()
                }

            }

    }


}