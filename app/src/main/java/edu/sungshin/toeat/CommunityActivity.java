package edu.sungshin.toeat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    ListView listView;
    listAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_main);

        ArrayList<Communitydata> list = new ArrayList<>();
        list.add(new Communitydata("강민서 님의 toeat", "내용 작성"));
        list.add(new Communitydata("권지민 님의 toeat", "내용 작성"));
        list.add(new Communitydata("서유진 님의 toeat", "내용 작성"));
        list.add(new Communitydata("윤선미 님의 toeat", "내용 작성"));
        list.add(new Communitydata("허예은 님의 toeat", "내용 작성"));

        listView = findViewById(R.id.list_post);
        adapter = new listAdapter(list);

        listView.setAdapter(adapter);
    }

    class listAdapter extends BaseAdapter {
        List<Communitydata> lists;

        public listAdapter (List<Communitydata> lists) {
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem (int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId (int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.community01, null);
            }

            TextView commuNickname = v.findViewById(R.id.username);
            TextView commuContent = v.findViewById(R.id.content);

            Communitydata data = lists.get(i);

            commuNickname.setText(data.getNickname());
            commuContent.setText(data.getContent());

            return v;

        }

    }

}
