package fr.mydango.colormemory.Views.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fr.mydango.colormemory.R;

public class TenButtonsFragment extends ButtonsFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ten_buttons, container, false);
    }

    @Override
    public List<Button> getAllButtons() {
        View view = getView();
        List<Button> result = new ArrayList<Button>();

        Button btn = view.findViewById(R.id.btnBlue);
        result.add(btn);

        btn = view.findViewById(R.id.btnRed);
        result.add(btn);

        btn = view.findViewById(R.id.btnGreen);
        result.add(btn);

        btn = view.findViewById(R.id.btnYellow);
        result.add(btn);

        btn = view.findViewById(R.id.btnPurple);
        result.add(btn);

        btn = view.findViewById(R.id.btnOrange);
        result.add(btn);

        btn = view.findViewById(R.id.btnPink);
        result.add(btn);

        btn = view.findViewById(R.id.btnIndigo);
        result.add(btn);

        btn = view.findViewById(R.id.btnGrey);
        result.add(btn);

        btn = view.findViewById(R.id.btnTeal);
        result.add(btn);

        return result;

    }
}
