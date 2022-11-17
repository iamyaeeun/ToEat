package edu.sungshin.toeat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter2 extends RecyclerView.Adapter<FoodAdapter2.ViewHolder> {
    ArrayList<Food> items=new ArrayList<>();

    public FoodAdapter2(ArrayList<Food> foodList){ items=foodList;}//생성자:전달한 FoodList 받아 items리스트에 대입

    public void addItem(Food item){ items.add(item); }
    public void setItems(ArrayList<Food> items){ this.items=items; }
    public Food getItem(int position){ return items.get(position); }
    public void setItem(int position, Food item){ items.set(position,item); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.food_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food item=items.get(position);
        holder.setItem(item);

        holder.foodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food item=items.get(position);
                items.remove(item);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,items.size());

                UserAdapter2 mDbHelper=new UserAdapter2(view.getContext());
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.delete(item.getName());
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View diaLogView;
                EditText foodName,expiration,num,market,memo;

                diaLogView=(View)View.inflate(view.getContext(), R.layout.info,null);
                AlertDialog.Builder dlg=new AlertDialog.Builder(view.getContext());
                dlg.setTitle("저장된 식품 정보");

                Food item=items.get(position);

                foodName=diaLogView.findViewById(R.id.editText1);
                expiration=diaLogView.findViewById(R.id.editText2);
                num=diaLogView.findViewById(R.id.editText3);
                market=diaLogView.findViewById(R.id.editText4);
                memo=diaLogView.findViewById(R.id.editText5);

                foodName.setText(item.getName());
                expiration.setText(item.getExpiration());
                num.setText(item.getNum());
                market.setText(item.getMarket());
                memo.setText(item.getMemo());

                dlg.setView(diaLogView);
                dlg.setPositiveButton("수정 완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        item.setName(foodName.getText().toString());
                        item.setExpiration(expiration.getText().toString());
                        item.setNum(num.getText().toString());
                        item.setMarket(market.getText().toString());
                        item.setMemo(memo.getText().toString());
                        notifyItemChanged(position);

                        UserAdapter2 mDbHelper=new UserAdapter2(view.getContext());
                        mDbHelper.createDatabase();
                        mDbHelper.open();
                        mDbHelper.update(item.getName(),item.getExpiration(),item.getNum(),item.getMarket(),item.getMemo());
                    }
                }); //확인 버튼 누르면 수정
                dlg.setNegativeButton("닫기",null);
                dlg.show();
            }
        });
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button foodDelete;
        TextView textView,textView2,textView3,textView4,textView5;

        public ViewHolder(View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            foodDelete=itemView.findViewById(R.id.deleteBtn);

            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textView4);
            textView5=itemView.findViewById(R.id.textView5);
        }

        public void setItem(Food item){
            textView.setText(item.getName());
            textView2.setText(item.getExpiration());
            textView3.setText(item.getNum());
            textView4.setText(item.getMarket());
            textView5.setText(item.getMemo());
        }
    }
}