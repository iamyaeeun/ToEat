package edu.sungshin.toeat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WritePostFragment extends Fragment {
    EditText postWriter,postContents;
    Button upload,noWrite;
    FirebaseUser user;
    FirebaseFirestore db;
    MainActivity activity;
    ArrayList<Communitydata> comList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_write_post, container, false);
        activity=(MainActivity)getActivity();
        comList=new ArrayList<>();
        db=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        postWriter=rootView.findViewById(R.id.postWriter);
        postContents=rootView.findViewById(R.id.postContents);
        upload=rootView.findViewById(R.id.postUpload);
        noWrite=rootView.findViewById(R.id.noWrite);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUpdate();
            }
        });

        noWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChanged(80);
            }
        });

        return rootView;
    }

    private void postUpdate(){
        String id=user.getUid();
        String writer=postWriter.getText().toString();
        String contents=postContents.getText().toString();

        if(postWriter.length()>0&&postContents.length()>0){
            Communitydata writeInfo=new Communitydata(id,writer,contents);
            uploader(writeInfo);
            back();
        }
    }

    private void uploader(Communitydata writeInfo){
        db.collection("posts").add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("msg","성공");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("msg","실패");
                    }
                });
    }

    private void back(){
        MainActivity activity=(MainActivity) getActivity();
        activity.onFragmentChanged(20);
    }
}