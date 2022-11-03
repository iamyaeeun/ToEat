package edu.sungshin.toeat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class langjango01Fragment extends Fragment{
    View diaLogView,diaLogView2;
    EditText foodName,count,market,memo;
    Button setDate;
    FloatingActionButton btnSubtract,btnAdd;
    CalendarView calendarView;
    int year,month,day;
    int selected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_langjango01_fragement, container, false);
        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);
        FloatingActionButton foodAdd=rootView.findViewById(R.id.foodAddButton);
        Button back=rootView.findViewById(R.id.back);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        FoodAdapter adapter=new FoodAdapter();
        adapter.addItem(new Food("감자","2022년10월29일","2","마트","베란다 보관"));
        adapter.addItem(new Food("감자","2022년10월29일","2","마트","베란다 보관"));
        adapter.addItem(new Food("감자","2022년10월29일","2","마트","베란다 보관"));
        recyclerView.setAdapter(adapter);
        


        foodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //대화상자 만들기
                AlertDialog.Builder select=new AlertDialog.Builder(rootView.getContext());
                select.setTitle("식품 등록 방법");

                String[] Array=new String[]{"직접 입력","바코드 입력"};
                boolean[] Check=new boolean[]{true,false};
                select.setSingleChoiceItems(Array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        selected=i;
                    }
                });

                select.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(selected==0){
                            diaLogView=(View)View.inflate(getContext(),R.layout.direct_register,null);
                            AlertDialog.Builder dlg=new AlertDialog.Builder(rootView.getContext());
                            dlg.setTitle("식품 정보 입력");

                            foodName=diaLogView.findViewById(R.id.foodName);
                            setDate=diaLogView.findViewById(R.id.setDate);
                            count=diaLogView.findViewById(R.id.numCount);
                            market=diaLogView.findViewById(R.id.market);
                            btnSubtract=diaLogView.findViewById(R.id.numSubtract);
                            btnAdd=diaLogView.findViewById(R.id.numAdd);
                            memo=diaLogView.findViewById(R.id.memo);

                            setDate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    diaLogView2=(View)View.inflate(getContext(),R.layout.expiration_cal,null);
                                    calendarView=diaLogView2.findViewById(R.id.calendarView);
                                    AlertDialog.Builder dlg2=new AlertDialog.Builder(rootView.getContext());
                                    dlg2.setView(diaLogView2);
                                    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                        @Override
                                        public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                                            year=i; month=i1; day=i2;
                                            Calendar c=Calendar.getInstance();
                                            c.set(Calendar.YEAR,year);
                                            c.set(Calendar.MONTH,month);
                                            c.set(Calendar.DAY_OF_MONTH,day);
                                            setDate.setText(String.valueOf(year)+String.valueOf(month+1)+String.valueOf(day));

                                            MainActivity activity=(MainActivity) getActivity();
                                            activity.startAlarm(c,foodName.getText().toString());
                                        }
                                    });
                                    dlg2.setPositiveButton("확인",null);
                                    dlg2.setNegativeButton("취소",null);
                                    dlg2.show();
                                }
                            });

                            btnSubtract.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int cnt=Integer.parseInt(count.getText().toString())-1;
                                    count.setText(String.valueOf(cnt));
                                }
                            });

                            btnAdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int cnt=Integer.parseInt(count.getText().toString())+1;
                                    count.setText(String.valueOf(cnt));
                                }
                            });

                            dlg.setView(diaLogView);

                            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    adapter.addItem(new Food(foodName.getText().toString(),setDate.getText().toString(),count.getText().toString(),market.getText().toString(),memo.getText().toString()));
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            dlg.setNegativeButton("취소",null);
                            dlg.show();
                        }
                        else{

                        }
                    }
                });

                select.setNegativeButton("등록 취소",null);
                select.show();
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