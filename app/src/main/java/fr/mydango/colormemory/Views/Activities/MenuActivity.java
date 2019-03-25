package fr.mydango.colormemory.Views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import fr.mydango.colormemory.R;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY = "fr.mydango.colormemory.Views.Activities.KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void selectModeEasy(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY, "0");
        startActivity(intent);
    }
    public void selectModeHard(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY, "1");
        startActivity(intent);
    }
    public void selectModeExpert(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY, "2");
        startActivity(intent);
    }
}
