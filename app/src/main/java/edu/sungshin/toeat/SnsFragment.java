package edu.sungshin.toeat;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SnsFragment extends Fragment {
    Button postAdd;
    ArrayList<Communitydata> comList;
    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sns, container, false);
        postAdd = rootView.findViewById(R.id.plusBtn);

        user= FirebaseAuth.getInstance().getCurrentUser();
        comList=new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                comList.add(new Communitydata(
                                        user.getUid(),
                                        document.getData().get("nickname").toString(),
                                        document.getData().get("content").toString()));
                            }
                            //Adapter
                            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                            RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(layoutManager);
                            CommunityAdapter adapter=new CommunityAdapter(comList);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.d("MyTag", "Error getting documents: ", task.getException());
                        }
                    }
                });

        postAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity=(MainActivity) getActivity();
                activity.onFragmentChanged(10);
            }
        });

        return rootView;
    }
}