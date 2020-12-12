package com.tauxis.projet_android_ccm.firebase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.util.ArrayUtils.contains
import com.google.firebase.auth.FirebaseUser
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.firebase.viewmodel.FirebaseAuthViewModel
import kotlinx.android.synthetic.main.activity_firebase_login.*

class FirebaseLoginActivity : AppCompatActivity() {
    private lateinit var mViewModel: FirebaseAuthViewModel

    private var mObserverUser = Observer<FirebaseUser> {
        updateUser(it)
    }

    private var mObserverError = Observer<Int> {
        updateError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_login)

        mViewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]
        firebaseButtonRegister.setOnClickListener { register() }
        firebaseButtonLogin.setOnClickListener { login() }
        firebaseButtonDisconnect.setOnClickListener { disconnect() }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mCurrentUser.observe(this, mObserverUser)
        mViewModel.mErrorProcess.observe(this, mObserverError)
    }

    override fun onStop() {
        mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)
        super.onStop()
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true
        firebaseError.text = ""

        if (TextUtils.isEmpty(firebaseUserEmail.text.toString())) {
            firebaseError.text = getString(R.string.email_empty)
            isValid = false
        }else if(!firebaseUserEmail.text.toString().contains('@')){
            firebaseError.text = getString(R.string.no_arobase)
            isValid = false
        }

        if (TextUtils.isEmpty(firebaseUserPassword.text.toString())) {
            if(firebaseError.text.toString()==""){
            firebaseError.text= getString(R.string.passwd_empty)}
            isValid = false
        }else if (firebaseUserPassword.text.count()<6){
            if(firebaseError.text.toString()==""){
                firebaseError.text= getString(R.string.passwd_short)}
            isValid = false
        }

        return isValid
    }

    private fun login() {
        if (checkConformityFields()) {
            mViewModel.loginUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
            firebaseError.text = ""
        }
    }

    private fun disconnect() {
        mViewModel.disconnectUser()
    }

    private fun register() {
        if (checkConformityFields()) {
            mViewModel.registerNewUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
            firebaseError.text = ""
        }
    }

    private fun updateUser(user : FirebaseUser) {
        user.let {
            firebaseLog.text = "${user.uid}-${user.email}"
        }
    }

    private fun updateError(code : Int) {
        when(code) {
            0-> {
                firebaseError.text =getString(R.string.connected)
                firebaseButtonRegister.isEnabled=false
                firebaseButtonLogin.isEnabled=false
                firebaseButtonDisconnect.isEnabled=true
            }
            5 -> {
                firebaseError.text = "disconnected"
                firebaseLog.text = "No logs"
                firebaseButtonRegister.isEnabled=true
                firebaseButtonLogin.isEnabled=true
                firebaseButtonDisconnect.isEnabled=false
            }
            9 -> {
                firebaseError.text = getString(R.string.no_user)
                firebaseButtonDisconnect.isEnabled=false
            }
            10 -> firebaseError.text = getString(R.string.error_create)
            11 -> firebaseError.text = getString(R.string.error_login)
            else -> firebaseError.text = "All is good"
        }
    }


}