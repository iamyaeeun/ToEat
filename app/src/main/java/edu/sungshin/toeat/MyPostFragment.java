package edu.sungshin.toeat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyPostFragment extends Fragment {
    FirebaseFirestore db;
    ArrayList<Communitydata> comList;
    Button back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_my_post, container, false);
        back=rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity=(MainActivity) getActivity();
                activity.onFragmentChanged(40);
            }
        });

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String userId=user.getUid();
        comList=new ArrayList<>();
        db=FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("publisher").toString().equals(userId)){
                                    comList.add(new Communitydata(
                                            document.getData().get("publisher").toString(),
                                            document.getData().get("nickname").toString(),
                                            document.getData().get("content").toString()));
                                }
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
        return rootView;
    }
}