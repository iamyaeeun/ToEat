package edu.sungshin.toeat;

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
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    ArrayList<Communitydata> items=new ArrayList<>();

    public CommunityAdapter(ArrayList<Communitydata> ComList){ items=ComList;}//생성자:전달한 comList 받아 items리스트에 대입

    public void addItem(Communitydata item){
        items.add(item);
    }
    public void setItems(ArrayList<Communitydata> items){ this.items=items; }
    public Communitydata getItem(int position){
        return items.get(position);
    }
    public void setItem(int position, Communitydata item){
        items.set(position,item);
    }

    @NonNull
    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.sns_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAdapter.ViewHolder holder, int position) {
        Communitydata item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView writer,contents;

        public ViewHolder(View itemView) {
            super(itemView);

            writer=itemView.findViewById(R.id.postWriter);
            contents=itemView.findViewById(R.id.postContents);
        }

        public void setItem(Communitydata item){
            writer.setText(item.getNickname());
            contents.setText(item.getContent());
        }
    }
}