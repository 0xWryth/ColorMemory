package fr.mydango.colormemory.Views.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fr.mydango.colormemory.R;

public class EightButtonsFragment extends ButtonsFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eight_buttons, container, false);
    }

    @Override
    public List<Button> getAllButtons(){
        View view = getView();
        List<Button> result = new ArrayList<>();

        /*result.add(view.findViewById(R.id.btnBlue));
        result.add(view.findViewById(R.id.btnGreen));
        result.add(view.findViewById(R.id.btnYellow));*/

        return result;
    }

}
