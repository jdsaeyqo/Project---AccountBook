package com.example.mymoneybook.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mymoneybook.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mOAuthLoginInstance : OAuthLogin

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    //google client
    private lateinit var googleSignInClient: GoogleSignInClient

    //private const val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //firebase auth 객체
        firebaseAuth = FirebaseAuth.getInstance()

        btn_signin.setOnClickListener {
            loginEmail()
        }
        btn_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        button_googlesignin.setOnClickListener { googleSignIn() }

        naverLogin()


    }

    private fun naverLogin() {
        val naver_client_id = "O1mW7xHOd2X95n7IbJ_C"
        val naver_client_secret = "8x5P5P_mHB"
        val naver_client_name = "네아로 테스트"


        val handler : OAuthLoginHandler = @SuppressLint("HandlerLeak")
        object :OAuthLoginHandler(){
            override fun run(success: Boolean) {
                if(success){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    val errorCode: String = mOAuthLoginInstance.getLastErrorCode(this@LoginActivity).code
                    val errorDesc = mOAuthLoginInstance.getLastErrorDesc(this@LoginActivity)

                    Toast.makeText(
                        baseContext, "errorCode:" + errorCode
                                + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        mOAuthLoginInstance  = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(this,naver_client_id,naver_client_secret,naver_client_name)
        button_naverlogin.setOAuthLoginHandler(handler)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LoginActivity", "Google sign in failed", e)
            }
        }
    } // onActivityResult End

    // firebaseAuthWithGoogle
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("LoginActivity", "firebaseAuthWithGoogle:" + acct.id!!)

        //Google SignInAccount 객체에서 ID 토큰을 가져와서 Firebase Auth로 교환하고 Firebase에 인증
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 성공", task.exception)
                    toMainActivity(firebaseAuth?.currentUser)
                } else {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 실패", task.exception)

                }
            }
    }// firebaseAuthWithGoogle END

    //이메일 로그인
    private fun loginEmail() {
        firebaseAuth.signInWithEmailAndPassword(
            edit_login.text.toString(),
            edit_pass.text.toString()
        )
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {

                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()

                }
            }
    }
        // toMainActivity
        fun toMainActivity(user: FirebaseUser?) {
            if (user != null) { // MainActivity 로 이동
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        } // toMainActivity End

        // signIn
        private fun googleSignIn() {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // signIn End
        private fun signOut() {
            FirebaseAuth.getInstance().signOut()

            googleSignInClient.signOut().addOnCompleteListener(this) {

            }
        }

        private fun revokeAccess() {
            FirebaseAuth.getInstance().signOut()

            googleSignInClient.revokeAccess().addOnCompleteListener(this) {

            }
        }

    }
