package edu.sungshin.toeat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MypageFragment extends Fragment {
    private FirebaseAuth mFirebaseAuth;
    FirebaseUser user;
    Button myPost,logout,withdrawal;
    TextView myEmail;
    String email;
    SharedPreferences sharedpreferences;
    MainActivity activity;
    public static final String mypreference = "mytoeat";
    public static final String Email = "emailKey";
    public static final String PassWord = "passWordKey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);
        myEmail=rootView.findViewById(R.id.userid);
        myPost=rootView.findViewById(R.id.myPost);
        logout=rootView.findViewById(R.id.logout);
        withdrawal=rootView.findViewById(R.id.withdrawal);

        activity=(MainActivity)getActivity();
        sharedpreferences = activity.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        user=FirebaseAuth.getInstance().getCurrentUser();
        email=user.getEmail();
        myEmail.setText(email);

        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity=(MainActivity) getActivity();
                activity.onFragmentChanged(30);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth=FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();

                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove(Email);
                editor.remove(PassWord);
                editor.commit();
            }
        });

        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth=FirebaseAuth.getInstance();
                mFirebaseAuth.getCurrentUser().delete();

                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }
}