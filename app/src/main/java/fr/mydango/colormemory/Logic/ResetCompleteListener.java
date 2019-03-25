package fr.mydango.colormemory.Logic;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import fr.mydango.colormemory.R;

/**
 * listener called during reset password transaction complete
 * toast a message to infom if sucess or not
 */
public class ResetCompleteListener implements OnCompleteListener<Void> {

    /**
     * current activity
     */
    private final Activity _activity;

    public  ResetCompleteListener(Activity activity){
        _activity = activity;
    }
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        // on success
        if (task.isSuccessful()) {
            Toast.makeText(_activity, R.string.reset_password_success, Toast.LENGTH_SHORT).show();
        // on fail
        } else {
            Toast.makeText(_activity, R.string.reset_password_fail, Toast.LENGTH_SHORT).show();
        }
    }
}
