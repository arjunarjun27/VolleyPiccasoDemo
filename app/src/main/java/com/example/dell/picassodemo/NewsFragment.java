package com.example.dell.picassodemo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.picassodemo.Model.DatabaseModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    ListView listView;

    List<String> mNewsList;

    List<DatabaseModel> modelList;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        listView = (ListView) view.findViewById(R.id.mylistview);

        modelList = new ArrayList<DatabaseModel>();

        for (int i = 0; i < 50; i++) {
            modelList.add(new DatabaseModel("a","fgdsfg"));
            modelList.add(new DatabaseModel("b","fgdsfg"));
            modelList.add(new DatabaseModel("c","fgdsfg"));
            modelList.add(new DatabaseModel("4","fgdsfg"));
            modelList.add(new DatabaseModel("5","fgdsfg"));
        }



        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseModel databaseModel = modelList.get(position);
                String name = databaseModel.getName();
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                //openBrowser(url);
            }
        });

        return view;
    }

    public void openBrowser(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return modelList.size();
        }

        @Override
        public Object getItem(int position) {
            return modelList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.list_row, null);

            DatabaseModel databaseModel = modelList.get(position);

            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(databaseModel.getName());

            //textView.setText(modelList.get(position).getName());

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

            Picasso.with(getActivity()).load("http://i.imgur.com/DvpvklR.png").into(imageView);
            return view;
        }
    }


}
