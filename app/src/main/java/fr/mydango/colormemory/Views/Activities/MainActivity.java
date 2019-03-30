package fr.mydango.colormemory.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.mydango.colormemory.Logic.GameSettings;
import fr.mydango.colormemory.Logic.GameTask;
import fr.mydango.colormemory.Models.Combinaison;
import fr.mydango.colormemory.R;
import fr.mydango.colormemory.Views.Fragments.ButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.EightButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.FiveButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.FourButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.NineButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.SevenButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.SixButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.TenButtonsFragment;

public class MainActivity extends AppCompatActivity{
    private int block;
    private int level;
    private double multiP;

    private int nbCombi;
    private List<Combinaison> ListeCombi;
    private int indiceCombi;

    String mode_id;
    private List<ButtonsFragment> _allFragments;

    private int life;

    private GameSettings gameS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mode_id = intent.getStringExtra(MenuActivity.KEY);
        gameS = new GameSettings(mode_id);

        multiP = gameS.GetScoreMultiplier();

        _allFragments = new ArrayList<>();
        _allFragments.add(new FourButtonsFragment());
        _allFragments.add(new FiveButtonsFragment());
        _allFragments.add(new SixButtonsFragment());
        _allFragments.add(new SevenButtonsFragment());
        _allFragments.add(new EightButtonsFragment());
        _allFragments.add(new NineButtonsFragment());
        _allFragments.add(new TenButtonsFragment());

        ListeCombi = new ArrayList<>();

        level = 1;
        block = 4;

        nbCombi = gameS.GetMinCombi();
        indiceCombi = 0;

        life = gameS.GetLives();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateFragment(block);
    }

    private void addListener(){
        for (Button btn : _allFragments.get(block - 4).getAllButtons())
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // ListeCombi
                    int id = v.getId();
                    if (ListeCombi.get(indiceCombi).idBtn == id)
                    {
                        if (ListeCombi.size() - 1 == indiceCombi)
                            game(true);
                        else
                            indiceCombi++;
                    }
                    else
                    {
                        life--;
                        TextView vie = findViewById(R.id.vie);
                        vie.setText("Vous avez " + life + " vie");
                        if (life > 0)
                            vie.setText(vie.getText() + "s");
                        if (life == 0)
                        {
                            sendScore((block - 4) * multiP);
                        }
                        else {
                            game(false);
                        }
                    }
                }
            });
    }

    private void game(boolean noZero){
        GameTask gt = new GameTask(ListeCombi, _allFragments.get(block - 4).getAllButtons(), block);
        if (noZero && nbCombi != gameS.GetMaxCombi())
        {
            gt.execute(nbCombi);
            nbCombi++;
        }
        else
            gt.execute(0);
        if (nbCombi == gameS.GetMaxCombi())
        {
            nbCombi = gameS.GetMinCombi();
                addBtn();
                game(true);
                addListener();
        }
        indiceCombi = 0;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        addListener();
        game(true);
    }

    public void addBtn() {
        life = gameS.GetLives();
        TextView vie = findViewById(R.id.vie);
        vie.setText("Vous avez " + life + " vies");

        block++;
        if (block == 11)
        {
            sendScore(7*multiP);
        }
        else {
            updateFragment(block);
        }
        ListeCombi = new ArrayList<>();

    }

    private void updateFragment(int block) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        supportFragmentManager.beginTransaction()
                .replace(R.id.FragmentContainer, getCurrentFragment(block))
                .commit();

        supportFragmentManager.executePendingTransactions();
    }

    private ButtonsFragment getCurrentFragment(int block)
    {
        return _allFragments.get(block - 4);
    }

    public static final String KEY2 = "fr.mydango.colormemory.Views.Activities.KEY2";
    public void sendScore(double score)
    {
        Intent intentScore = new Intent(this, ScoreActivity.class);
        intentScore.putExtra(KEY2, ("" + score));
        startActivity(intentScore);
        finish();
    }
}
