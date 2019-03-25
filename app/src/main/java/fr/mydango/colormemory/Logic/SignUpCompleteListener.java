package fr.mydango.colormemory.Logic;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.mydango.colormemory.Views.Activities.Authent.SignupActivity;

/**
 * listener called during signup transaction complete
 * if transaction fail => toast a message
 * else => show next activities
 */
public class SignUpCompleteListener implements OnCompleteListener<AuthResult> {

    /**
     * current activity
     */
    private final Activity _activity;

    public SignUpCompleteListener(Activity activity){
        _activity = activity;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Toast.makeText(_activity, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.

        // on error
        if (!task.isSuccessful()) {
            Toast.makeText(_activity, "Authentication failed." + task.getException(),
                    Toast.LENGTH_SHORT).show();
        } else {
            // on sucess
            _activity.startActivity(new Intent(_activity, SignupActivity.ACTIVITY_AFTER_AUTHENT));
            _activity.finish();
        }
    }
}
