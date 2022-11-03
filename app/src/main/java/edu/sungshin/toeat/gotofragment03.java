package edu.sungshin.toeat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class gotofragment03 extends Fragment {
    Button button1,button2;
    TextView intoname01;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_gotofragment01_fragment, container, false);
        button1=rootView.findViewById(R.id.buttont01);
        button2=rootView.findViewById(R.id.button02);
        intoname01=rootView.findViewById(R.id.intoname01);

        return rootView;
    }
}