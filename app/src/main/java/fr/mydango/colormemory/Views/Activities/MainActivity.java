package fr.mydango.colormemory.Views.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mydango.colormemory.Models.Colors;
import fr.mydango.colormemory.R;
import fr.mydango.colormemory.Views.Fragments.ButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.EightButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.FiveButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.FourButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.NineButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.SevenButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.SixButtonsFragment;
import fr.mydango.colormemory.Views.Fragments.TenButtonsFragment;

public class MainActivity extends AppCompatActivity {
    private int block;
    private int level;

    private int combinaison;
    private int combinaisonMax = 7;
    List<Colors> combinaisonPrecedante;

    private List<ButtonsFragment> _allFragments;

    private List<Integer> defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _allFragments = new ArrayList<>();
        _allFragments.add(new FourButtonsFragment());
        _allFragments.add(new FiveButtonsFragment());
        _allFragments.add(new SixButtonsFragment());
        _allFragments.add(new SevenButtonsFragment());
        _allFragments.add(new EightButtonsFragment());
        _allFragments.add(new NineButtonsFragment());
        _allFragments.add(new TenButtonsFragment());

        combinaisonPrecedante = new ArrayList<>();

        level = 1;
        block = 4;
        combinaison = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateFragment(block);
    }

    private void setDefaultColor()
    {
        defaultColor = new ArrayList<>();

        List<Button> btn = _allFragments.get(0).getAllButtons();
        for (Button b : btn)
        {
            ColorDrawable cd = (ColorDrawable) b.getBackground();
            int colorId = cd.getColor();
            defaultColor.add(colorId);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        setDefaultColor();
        griserBtn();
    }

    private void griserBtn() {
        List<Button> btn = _allFragments.get(0).getAllButtons();
        for (Button b : btn)
        {
            b.setTag(b.getBackground());
            b.setBackgroundColor(Color.rgb(1, 1 ,2));
        }
    }

    private void degriserBtn() {
        List<Button> btn = _allFragments.get(0).getAllButtons();
        int i = 0;
        for (Button b : btn)
        {
            b.setBackgroundColor(defaultColor.get(i));
            i++;
        }
    }

    public void addBtn(View view) {
        degriserBtn();
        /*block++;
        updateFragment(block);*/
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private void createRandomCombinaison() {
        List<Colors> combi;
        if (combinaisonPrecedante.size() == 0)
        {
            combi = new ArrayList<>(combinaison);
            for (int i = 0; i < combinaison; i++)
                combi.add(Colors.values()[randInt(0, block)]);
            combinaisonPrecedante = combi;
        }
        else
        {
            combi = combinaisonPrecedante;
            combi.add(Colors.values()[randInt(0, block)]);
        }
    }

    private void griserLesButtons() {
        for(Colors c : Colors.values())
        {
            String id = "btn" + c;
            //findViewById(id);
        }
    }

    private void displayCombinaison() {
        griserLesButtons();
    }

    public void startGame(View view) {
        createRandomCombinaison();
        displayCombinaison();
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
