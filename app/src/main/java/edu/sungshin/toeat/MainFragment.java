package edu.sungshin.toeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    Button langjango01;
    Button langjango02;
    Button langjango03;
    Button langjango04;
    Button langjango05;
    Button langjango06;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        langjango01=rootView.findViewById(R.id.langjango01);
        langjango02=rootView.findViewById(R.id.langjango02);
        langjango03=rootView.findViewById(R.id.langjango03);
        langjango04=rootView.findViewById(R.id.langjango04);
        langjango05=rootView.findViewById(R.id.langjango05);
        langjango06=rootView.findViewById(R.id.langjango06);

        langjango01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0);
            }
        });

        langjango02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(1);
            }
        });

        langjango03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(2);
            }
        });

        langjango04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(3);
            }
        });

        langjango05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(4);
            }
        });

        langjango06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(5);
            }
        });

        return rootView;
    }
}