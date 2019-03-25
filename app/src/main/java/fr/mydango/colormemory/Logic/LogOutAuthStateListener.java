package fr.mydango.colormemory.Logic;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.mydango.colormemory.Views.Activities.Authent.SignupActivity;

/**
 * listener called during logout
 * close current activity
 * open authent activity
 */
public class LogOutAuthStateListener implements FirebaseAuth.AuthStateListener {

    /**
     * current activity
     */
    private final Activity _activity;

    public LogOutAuthStateListener(Activity activity){
        _activity = activity;
    }
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            // user auth state is changed - user is null
            // launch login activity
            _activity.startActivity(new Intent(_activity, SignupActivity.class));
            _activity.finish();
        }
    }
}
