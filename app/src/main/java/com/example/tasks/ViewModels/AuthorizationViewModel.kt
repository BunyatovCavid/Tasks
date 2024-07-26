package com.example.tasks.ViewModels

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.tasks.Activities.MainActivity
import com.example.tasks.Fragments.InitiallyFragment
import com.example.tasks.R
import com.google.firebase.auth.FirebaseAuth

class AuthorizationViewModel:ViewModel(){

    lateinit var firebaseauth:FirebaseAuth

    public fun SingIn( email:String, password:String, f:Fragment) {
        firebaseauth = FirebaseAuth.getInstance()
            firebaseauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        var intent = Intent(f.context, MainActivity::class.java)
                        startActivity(f.context!!, intent, Bundle())
                        Toast.makeText(f.context, "SingIn is Successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(f.context, "SingIn is Failed", Toast.LENGTH_LONG).show()
                    }

                }


    }

    public  fun SignUp(email:String, password: String, f:Fragment)
    {
        firebaseauth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(){task->
                if(task.isSuccessful)
                {
                    val transaction = f.parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.containerMain3, InitiallyFragment())
                    transaction.commit()
                }
                else
                {
                    Toast.makeText(f.context, "SingUp is Failed", Toast.LENGTH_LONG ).show()
                }

            }
    }



}