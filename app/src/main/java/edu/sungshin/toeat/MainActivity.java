package edu.sungshin.toeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    SnsFragment snsFragment;
    MypageFragment mypageFragment;
    langjango01Fragment langjango01Fragment;
    langjango02Fragement langjango02Fragement;
    langjango03Fragement langjango03Fragement;
    langjango04Fragement langjango04Fragement;
    langjango05Fragement langjango05Fragement;
    langjango06Fragement langjango06Fragement;
    WritePostFragment writePostFragment;
    MyPostFragment myPostFragment;
    private DatabaseReference mDatabaseRef;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mytoeat";
    public static final String Email = "emailKey";
    public static final String PassWord = "passWordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment=new MainFragment();
        snsFragment=new SnsFragment();
        mypageFragment=new MypageFragment();
        langjango01Fragment=new langjango01Fragment();
        langjango02Fragement=new langjango02Fragement();
        langjango03Fragement=new langjango03Fragement();
        langjango04Fragement=new langjango04Fragement();
        langjango05Fragement=new langjango05Fragement();
        langjango06Fragement=new langjango06Fragement();
        writePostFragment=new WritePostFragment();
        myPostFragment=new MyPostFragment();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("ToEat");
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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

        if(!sharedpreferences.contains(Email)||!sharedpreferences.contains(PassWord)){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onFragmentChanged(int index){
        if(index==0) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango01Fragment).commit();
        else if(index==1) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango02Fragement).commit();
        else if(index==2) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango03Fragement).commit();
        else if(index==3) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango04Fragement).commit();
        else if(index==4) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango05Fragement).commit();
        else if(index==5) getSupportFragmentManager().beginTransaction().replace(R.id.container,langjango06Fragement).commit();

        else if(index==6) getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        else if(index==10) getSupportFragmentManager().beginTransaction().replace(R.id.container,writePostFragment).commit();
        else if(index==20) getSupportFragmentManager().beginTransaction().replace(R.id.container,snsFragment).commit();
        else if(index==30) getSupportFragmentManager().beginTransaction().replace(R.id.container,myPostFragment).commit();
        else if(index==40) getSupportFragmentManager().beginTransaction().replace(R.id.container,mypageFragment).commit();
        else if(index==80) getSupportFragmentManager().beginTransaction().replace(R.id.container,snsFragment).commit();
    }

    public void startAlarm(Calendar c, String foodName){
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_MUTABLE);

        c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-3);
        c.set(Calendar.HOUR_OF_DAY,17);
        c.set(Calendar.MINUTE,18);
        c.set(Calendar.SECOND,30);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한 번 더 누르면 앱이 종료됩니다 ! ", Toast.LENGTH_SHORT).show();
        }
    }
}