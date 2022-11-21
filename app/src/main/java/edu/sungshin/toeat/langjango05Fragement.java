package edu.sungshin.toeat;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class langjango05Fragement extends Fragment{
    View diaLogView,diaLogView2;
    EditText foodName,count,market,memo;
    Button setDate,back,foodAdd,btnSubtract,btnAdd;
    CalendarView calendarView;
    int year,month,day;
    int x;
    ArrayList <Food>foodList;
    String no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_langjango05_fragement, container, false);
        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);
        foodAdd=rootView.findViewById(R.id.foodAddButton);
        back=rootView.findViewById(R.id.back);
        foodList=new ArrayList<Food>();

        //DB
        UserAdapter5 userAdapter=new UserAdapter5(getActivity().getApplicationContext());
        userAdapter.createDatabase();
        userAdapter.open();
        foodList=userAdapter.getTableData();

        //Adapter
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        FoodAdapter5 adapter=new FoodAdapter5(foodList);
        recyclerView.setAdapter(adapter);

        foodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //대화상자 만들기
                diaLogView = (View) View.inflate(getContext(), R.layout.direct_register, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(rootView.getContext());
                dlg.setTitle("식품 정보 입력");

                foodName = diaLogView.findViewById(R.id.foodName);
                setDate = diaLogView.findViewById(R.id.setDate);
                count = diaLogView.findViewById(R.id.numCount);
                market = diaLogView.findViewById(R.id.market);
                btnSubtract = diaLogView.findViewById(R.id.numSubtract);
                btnAdd = diaLogView.findViewById(R.id.numAdd);
                memo = diaLogView.findViewById(R.id.memo);

                setDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        diaLogView2 = (View) View.inflate(getContext(), R.layout.expiration_cal, null);
                        calendarView = diaLogView2.findViewById(R.id.calendarView);
                        AlertDialog.Builder dlg2 = new AlertDialog.Builder(rootView.getContext());
                        dlg2.setView(diaLogView2);
                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                                year = i;
                                month = i1;
                                day = i2;
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, month);
                                c.set(Calendar.DAY_OF_MONTH, day);
                                setDate.setText(String.valueOf(year) + "년" + String.valueOf(month + 1) + "월" + String.valueOf(day) + "일");

                                MainActivity activity = (MainActivity) getActivity();
                                activity.startAlarm(c, foodName.getText().toString());
                            }
                        });
                        dlg2.setPositiveButton("확인", null);
                        dlg2.setNegativeButton("취소", null);
                        dlg2.show();
                    }
                });

                btnSubtract.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int cnt = Integer.parseInt(count.getText().toString()) - 1;
                        count.setText(String.valueOf(cnt));
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int cnt = Integer.parseInt(count.getText().toString()) + 1;
                        count.setText(String.valueOf(cnt));
                    }
                });

                dlg.setView(diaLogView);

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        x++;
                        no = String.valueOf(x);

                        userAdapter.insert(no, foodName.getText().toString(), setDate.getText().toString(), count.getText().toString(), market.getText().toString(), memo.getText().toString());
                        foodList = userAdapter.getTableData();
                        FoodAdapter5 adapter = new FoodAdapter5(foodList);//FoodAdapter에 foodList전달
                        recyclerView.setAdapter(adapter);
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity=(MainActivity) getActivity();
                activity.onFragmentChanged(6);
            }
        });

        return rootView;
    }
}