package fr.mydango.colormemory.Logic;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mydango.colormemory.Models.Combinaison;

public class GameTask extends AsyncTask<Integer, Integer, Object> {
    List<Button> listBtn;
    private List<Integer> defaultColor;

    List<Combinaison> combinaison;

    private int nbBlockMax;

    public GameTask(List<Combinaison> listeCombinaison, List<Button> listBtn, int nb) {
        combinaison = listeCombinaison;

        this.listBtn = listBtn;
        nbBlockMax = nb;
    }

    private void setDefaultColor()
    {
        defaultColor = new ArrayList<>();
;
        for (Button b : listBtn)
        {
            ColorDrawable cd = (ColorDrawable) b.getBackground();
            int colorId = cd.getColor();
            defaultColor.add(colorId);
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private void affichage(int publish)
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishProgress(publish);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Integer... i) {
        if (combinaison.size() != 0)
            for (Combinaison c : combinaison)
                affichage(c.idArray);

        if (i[0] != 0)
        {
            int rand = 0;
            if (combinaison.size() == 0)
            {
                rand = randInt(0, nbBlockMax - 1);
            }
            else {
                do {
                    rand = randInt(0, nbBlockMax - 1);
                } while( rand == combinaison.get(combinaison.size() - 1).idArray );
            }

            affichage(rand);

            combinaison.add(new Combinaison(listBtn.get(rand).getId(), rand));
        }

        return null;
    }

    private void griserTousBtn()
    {
        for (Button btn : listBtn)
            btn.setBackgroundColor(Color.GRAY);
    }

    private void griserBtn(int index) {
        listBtn.get(index).setBackgroundColor(Color.GRAY);
    }

    private void degriserTousBtn() {
        int i = 0;
        for (Button b : listBtn)
        {
            b.setBackgroundColor(defaultColor.get(i));
            i++;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... i) {
        super.onProgressUpdate(i);

        degriserTousBtn();
        griserBtn(i[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        for (Button b : listBtn)
        {
            setDefaultColor();
            b.setEnabled(false);
        }
        griserTousBtn();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        int i = 0;
        for (Button b : listBtn)
        {
            b.setEnabled(true);
            b.setBackgroundColor(defaultColor.get(i));
            i++;
        }
    }
}
