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
import java.util.concurrent.TimeUnit;

import fr.mydango.colormemory.Logic.GameTask;
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

    private List<ButtonsFragment> _allFragments;

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

        level = 1;
        block = 4;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateFragment(block);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    public void addBtn(View view) {
        /*block++;
        updateFragment(block);*/
    }


    public void startGame(View view) {
        GameTask gt = new GameTask(_allFragments.get(0).getAllButtons());
        gt.execute(2);
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
