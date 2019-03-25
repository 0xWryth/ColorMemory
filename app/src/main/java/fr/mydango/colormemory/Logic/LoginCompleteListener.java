package fr.mydango.colormemory.Logic;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult

import fr.mydango.colormemory.R;
import fr.mydango.colormemory.Views.Activities.Authent.SignupActivity;

/**
 * listener called during login transaction complete
 * if transaction fail => toast a message
 * else => show next activities
 */
public class LoginCompleteListener implements OnCompleteListener<AuthResult> {

    /**
     * edit text where password is informed
     */
    private final  EditText _inputPassword;

    /**
     * current activity
     */
    private final Activity _activity;

    public LoginCompleteListener(Activity activity, EditText inputPassword){
        _activity = activity;
        _inputPassword = inputPassword;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        Resources resources = _activity.getResources();
        String password = _inputPassword.getText().toString();

        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        if (!task.isSuccessful()) { // there was an error

            // password less than min length
            if (password.length() < SignupActivity.PASSWORD_MIN_LENGTH) {
                _inputPassword.setError(resources.getString(R.string.minimum_password));
            // authentication fail
            } else {
                Toast.makeText(_activity, resources.getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
            }
        // success
        } else {
            // start next activity
            Intent intent = new Intent(_activity, SignupActivity.ACTIVITY_AFTER_AUTHENT);

            // close current
            _activity.startActivity(intent);
            _activity.finish();
        }
    }

}
