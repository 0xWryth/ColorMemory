package fr.mydango.colormemory.Views.Activities.Authent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import fr.mydango.colormemory.R;
import fr.mydango.colormemory.Views.Activities.MainActivity;

/**
 * activity to register new user
 */
public class SignupActivity extends AppCompatActivity {

    /**
     * min length of a password
     */
    public static final  int PASSWORD_MIN_LENGTH = 6;

    /**
     * type of the class direct after authent
     */
    public static final Class<? extends Activity> ACTIVITY_AFTER_AUTHENT = MainActivity.class;
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
        setContentView(R.layout.activity_signup);

        // Get Firebase auth instance
        _auth = FirebaseAuth.getInstance();

        // direct redirection to next page if already login
        if (_auth.getCurrentUser() != null) {
            startActivity(new Intent(this, ACTIVITY_AFTER_AUTHENT ));
            finish();
        }

        _inputEmail = findViewById(R.id.email);
        _inputPassword = findViewById(R.id.password);
    }

    /**
     * on reset password click
     * @param view
     */
    public void onResetClick(View view) {
        startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
    }

    /**
     * on sign in click
     * @param view
     */
    public void onSignInClick(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

    /**
     * on complete register
     * @param view
     */
    public void onSignUpClick(View view) {

        // get values
        String email = _inputEmail.getText().toString().trim();
        String password = _inputPassword.getText().toString().trim();

        //check value not empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.email_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.password_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        _auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new SignUpCompleteListener(this));
    }
}