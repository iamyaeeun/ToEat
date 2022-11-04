package edu.sungshin.toeat;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<Food> items=new ArrayList<>();

    public FoodAdapter(ArrayList<Food> foodList){ items=foodList;}//생성자:전달한 FoodList 받아 items리스트에 대입

    public void addItem(Food item){
        items.add(item);
    }
    public void setItems(ArrayList<Food> items){ this.items=items; }
    public Food getItem(int position){
        return items.get(position);
    }
    public void setItem(int position, Food item){
        items.set(position,item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.food_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button foodDelete;
        TextView textView,textView2,textView3,textView4,textView5;

        View diaLogView;
        EditText foodName,expiration,num,market,memo;

        public ViewHolder(View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            foodDelete=itemView.findViewById(R.id.deleteBtn);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textView4);
            textView5=itemView.findViewById(R.id.textView5);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    diaLogView=(View)View.inflate(view.getContext(), R.layout.info,null);
                    AlertDialog.Builder dlg=new AlertDialog.Builder(view.getContext());
                    dlg.setTitle("저장된 식품 정보");

                    foodName=diaLogView.findViewById(R.id.editText1);
                    expiration=diaLogView.findViewById(R.id.editText2);
                    num=diaLogView.findViewById(R.id.editText3);
                    market=diaLogView.findViewById(R.id.editText4);
                    memo=diaLogView.findViewById(R.id.editText5);

                    foodName.setText(textView.getText().toString());
                    expiration.setText(textView2.getText().toString());
                    num.setText(textView3.getText().toString());
                    market.setText(textView4.getText().toString());
                    memo.setText(textView5.getText().toString());

                    dlg.setView(diaLogView);
                    dlg.setPositiveButton("확인",null);
                    dlg.setNegativeButton("취소",null);
                    dlg.show();
                }
            });

            foodDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserAdapter mDbHelper=new UserAdapter(foodDelete.getContext());
                    mDbHelper.createDatabase();
                    mDbHelper.open();
                    mDbHelper.delete(textView.getText().toString());
                    //notifyDataSetChanged
                }
            });
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
