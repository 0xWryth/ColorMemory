package fr.mydango.colormemory.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fr.mydango.colormemory.Logic.GameSettings;
import fr.mydango.colormemory.Logic.GameTask;
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

    private int nbCombi;
    private List<Integer> ListeCombi;
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
                    if (ListeCombi.get(indiceCombi) == id)
                    {
                        if (ListeCombi.size() - 1 == indiceCombi)
                            game();
                        else
                            indiceCombi++;
                    }
                    else
                    {
                        System.out.println("fail");
                        life--;
                        if (life == 0)
                        {
                            //You died
                        }
                    }
                }
            });
    }

    private void game(){
        GameTask gt = new GameTask(ListeCombi, _allFragments.get(block - 4).getAllButtons());
        gt.execute(nbCombi);
        nbCombi++;
        if (nbCombi == gameS.GetMaxCombi())
        {
            nbCombi = 0;
            addBtn();
        }
        indiceCombi = 0;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        addListener();
        game();
    }

    public void addBtn() {
        block++;
        updateFragment(block);
        addListener();
    }

    public void BtnClick(View view) {
        // ListeCombi
        int id = view.getId();
        if (ListeCombi.get(indiceCombi) == id)
        {
            System.out.println("pas fail");
            /**if (ListeCombi.size() - 1 == indiceCombi)
             game();
             else
             indiceCombi++;**/
        }
        else
        {
            System.out.println("fail");
            /**life--;
             if (life == 0)
             {
             // You died
             }**/
        }
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
}
