package com.example.mymoneybook.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mymoneybook.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var firebaseauth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseauth = FirebaseAuth.getInstance()

        button_signup.setOnClickListener {
            createEmail()
        }

    }

    private fun createEmail(){
        firebaseauth!!.createUserWithEmailAndPassword(edit_email.text.toString(),edit_pass.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val user = firebaseauth?.currentUser
                    Toast.makeText(this,"회원가입 성공", Toast.LENGTH_SHORT).show()
                    var intent  = Intent(this,
                        LoginActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }else{
                    Toast.makeText(this,"회원가입 실패", Toast.LENGTH_SHORT).show()

                }
            }
    }
}