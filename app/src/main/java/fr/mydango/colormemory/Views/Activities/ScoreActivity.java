package fr.mydango.colormemory.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import fr.mydango.colormemory.R;

public class ScoreActivity extends AppCompatActivity {
    public String score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        score = intent.getStringExtra(MainActivity.KEY2);

        TextView t2 = findViewById(R.id.textView3);
        t2.setText(score + "Points");
    }

    public void retour(View view)
    {
        Intent Iretour = new Intent(this, MenuActivity.class);
        startActivity(Iretour);
    }
}
