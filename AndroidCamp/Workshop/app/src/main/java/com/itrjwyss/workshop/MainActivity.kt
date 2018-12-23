package com.itrjwyss.workshop

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View;
import android.widget.TextView;
import android.widget.Toast

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

class MainActivity : AppCompatActivity() {

    lateinit var token: TextView
    lateinit var auth0: Auth0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        token = findViewById(R.id.tv_token)

        auth0 = Auth0(this)
        auth0.isOIDCConformant = true
    }

    fun login(view: View) {
        WebAuthProvider.init(auth0)
            .withScheme("demo")
            .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
            .start(this@MainActivity, object : AuthCallback {
                override fun onFailure(dialog: Dialog) {
                    dialog.show()
                }

                override fun onFailure(exception: AuthenticationException) {
                    showMessage(exception.message!!);
                }

                override fun onSuccess(credentials: Credentials) {
                    Log.i("Credentials", ": " + credentials.accessToken);
                    showMessage(credentials.accessToken!!)
                }
            })
    }



    fun showMessage(msj: String){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show()
    }

}
