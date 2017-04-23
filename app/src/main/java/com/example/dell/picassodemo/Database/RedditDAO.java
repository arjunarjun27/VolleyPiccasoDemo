package com.example.dell.picassodemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.picassodemo.Model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/15/2017.
 */
public class RedditDAO {

    private static RedditDAO sinstance = null;

    public static RedditDAO getMinstance() {
        if (sinstance == null) {
            sinstance = new RedditDAO();
        }
        return sinstance;
    }


    public boolean storePosts(Context context, List<Post> postList) {
        //List<Post> storedPost = RedditDAO.getMinstance().getPostsFromDB(context);
        try {
            SQLiteDatabase sqLiteDatabase = new DBOpenHelper(context).getWritableDatabase();

            sqLiteDatabase.beginTransaction();

            for (Post post : postList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.PostTable.TITLE, post.getTitle());
                contentValues.put(DatabaseContract.PostTable.LINK, post.getPermalink());
                contentValues.put(DatabaseContract.PostTable.IMAGELINK, post.getThumbnail());
                sqLiteDatabase.insert(DatabaseContract.PostTable.TABLE_NAME, null, contentValues);
            }
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public List<Post> getPostsFromDB(Context context) {
        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

        Cursor cursor = db.query(DatabaseContract.PostTable.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        List<Post> postList = new ArrayList<>();
        while (cursor.moveToNext()) {

            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.TITLE));
            String link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.LINK));
            String imagelink = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.IMAGELINK));

            Post post = new Post(title, link, imagelink);
            postList.add(post);

        }
        cursor.close();
        db.close();
        return postList;

    }


}
