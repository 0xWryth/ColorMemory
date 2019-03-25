package fr.mydango.colormemory.Views.Activities.Authent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import fr.mydango.colormemory.Logic.LoginCompleteListener;
import fr.mydango.colormemory.R;

/**
 * activity to login existing user
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * input the write email
     */
    private EditText _inputEmail;

    /**
     * input the write password
     */
    private EditText _inputPassword;

    private FirebaseAuth _auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get Firebase auth instance
        _auth = FirebaseAuth.getInstance();

        // direct redirection to next page if already login
        if (_auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.ACTIVITY_AFTER_AUTHENT));
            finish();
        }

        _inputEmail = (EditText) findViewById(R.id.email);
        _inputPassword = (EditText) findViewById(R.id.password);
    }


    public void onSignupClick(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }

    public void onResetClick(View view) {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    /**
     * on complete login
     * @param view
     */
    public void onLoginClick(View view) {
        // get values
        final String email = _inputEmail.getText().toString();
        final String password = _inputPassword.getText().toString();

        // check values not null
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.adress_missing, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.passwordMissing, Toast.LENGTH_SHORT).show();
            return;
        }

        //authenticate user
        _auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new LoginCompleteListener(this,_inputPassword));
    }
}