package fr.mydango.colormemory.Views.Activities.Authent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.mydango.colormemory.R;

/**
 * activity to reset password
 */
public class ResetPasswordActivity extends AppCompatActivity {

    /**
     * edit text where email is write
     */
    private EditText _inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        _inputEmail = (EditText) findViewById(R.id.email);
    }

    public void onBackClick(View view) {
        finish();
    }

    public void onResetClick(View view) {
        String email = _inputEmail.getText().toString().trim();

        // if email empty => toast message
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), R.string.email_missing, Toast.LENGTH_SHORT).show();
            return;
        }

    }

}