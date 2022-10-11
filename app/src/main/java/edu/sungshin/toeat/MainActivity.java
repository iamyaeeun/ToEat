package edu.sungshin.toeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    MainFragment mainFragment;
    SnsFragment snsFragment;
    MypageFragment mypageFragment;
    langjango01Fragement langjango01Fragement;
    langjango02Fragement langjango02Fragement;
    langjango03Fragement langjango03Fragement;
    langjango04Fragement langjango04Fragement;
    langjango05Fragement langjango05Fragement;
    langjango06Fragement langjango06Fragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment=new MainFragment();
        snsFragment=new SnsFragment();
        mypageFragment=new MypageFragment();
        langjango01Fragement=new langjango01Fragement();
        langjango02Fragement=new langjango02Fragement();
        langjango03Fragement=new langjango03Fragement();
        langjango04Fragement=new langjango04Fragement();
        langjango05Fragement=new langjango05Fragement();
        langjango06Fragement=new langjango06Fragement();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,snsFragment).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mypageFragment).commit();
                        return true;
                }
                return false;
            }
        });
        /*
        mFirebaseAuth=FirebaseAuth.getInstance();

        Button btn_logout=findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃 하기
                mFirebaseAuth.signOut();

                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        */
    }
    public void onFragmentChanged(int index){
        if(index==0) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango01Fragement).commit();
        else if(index==1) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango02Fragement).commit();
        else if(index==2) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango03Fragement).commit();
        else if(index==3) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango04Fragement).commit();
        else if(index==4) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango05Fragement).commit();
        else if(index==5) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango06Fragement).commit();
    }
}