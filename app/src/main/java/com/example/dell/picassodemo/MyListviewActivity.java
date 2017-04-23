package com.example.dell.picassodemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.picassodemo.Database.RedditDAO;
import com.example.dell.picassodemo.Model.Listing;
import com.example.dell.picassodemo.Model.Post;
import com.example.dell.picassodemo.Model.RedditAdapter;
import com.google.gson.Gson;

import java.util.List;

public class MyListviewActivity extends AppCompatActivity implements RedditAdapter.MyListItemClickListener {

    private static final String TAG = "MyListviewActivity";
    private final String url = "https://www.reddit.com/r/Android.json?limit=5";
    TextView textView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        textView = (TextView) findViewById(R.id.t1);

        //    RequestQueue requestQueue= Volley.newRequestQueue(this);

        RequestQueue queue = ConnectionManager.getInstance(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Listing listing = new Gson().fromJson(response, Listing.class);

                List<Post> postList = listing.getPostList();

                RedditAdapter redditAdapter = new RedditAdapter(listing.getPostList(), MyListviewActivity.this, MyListviewActivity.this);

                boolean a = RedditDAO.getMinstance().storePosts(MyListviewActivity.this, postList);

                if (a) {
                    Toast.makeText(MyListviewActivity.this, "" + a, Toast.LENGTH_SHORT).show();
                }


                recyclerView.setAdapter(redditAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                List<Post> postList = RedditDAO.getMinstance().getPostsFromDB(MyListviewActivity.this);
                RedditAdapter adapter = new RedditAdapter(postList, MyListviewActivity.this, MyListviewActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });

        queue.add(stringRequest);


//        SyncMyOkhttp myAsyncTask = new SyncMyOkhttp();
//        myAsyncTask.execute();


        //       okhttp();


    }

    @Override
    public void OnItemClick(Post itemClicked) {
        Toast.makeText(MyListviewActivity.this, "" + itemClicked.getThumbnail(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MyListviewActivity.this, WebActivity.class);
        intent.putExtra("url", itemClicked.getPermalink());
        startActivity(intent);

    }


//    private void okhttp() {
//        OkHttpClient client = new OkHttpClient();
//
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    System.out.println("reddit  " + "name :" + responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
//            }
//        });
//    }

    // async with network
//    private class SyncMyOkhttp extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            OkHttpClient okHttpClient = new OkHttpClient();
//
//            Request request = new Request.Builder().url(url).build();
//
//            Response response = null;
//
//
//            try {
//                response = okHttpClient.newCall(request).execute();
//                return response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Toast.makeText(MyListviewActivity.this, s, Toast.LENGTH_SHORT).show();
//        }
//    }


    // aysnc test
    private class MyAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            //return params[1];
            return "My name is arjun";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }


}
