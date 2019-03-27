package fr.mydango.colormemory.Logic;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTask extends AsyncTask<Integer, Integer, Object> {
    List<Button> listBtn;
    private List<Integer> defaultColor;

    List<Integer> combinaison;

    private int nbBlockMax;

    public GameTask(List<Integer> listeCombinaison, List<Button> listBtn) {
        combinaison = listeCombinaison;


        this.listBtn = listBtn;
        nbBlockMax = 4;
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

    @Override
    protected Void doInBackground(Integer... i) {
        if (i[0] != 0)
        {
            try {
                Thread.sleep(775);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int rand = 0;
            if (combinaison.size() == 0)
            {
                rand = randInt(0, nbBlockMax - 1);
            }
            else {
                do {
                    rand = randInt(0, nbBlockMax - 1);
                } while( rand == combinaison.get(combinaison.size() - 1) );
            }

            publishProgress(rand);

            rand = listBtn.get(rand).getId();

            combinaison.add(rand);

            try {
                Thread.sleep(775);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //int indiceMin = i[0] - 1;
            //doInBackground(indiceMin);
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
            //b.setEnabled(false);
        }
        griserTousBtn();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        int i = 0;
        for (Button b : listBtn)
        {
            //b.setEnabled(true);
            b.setBackgroundColor(defaultColor.get(i));
            i++;
        }
    }
}
