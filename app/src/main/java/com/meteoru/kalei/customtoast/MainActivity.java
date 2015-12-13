package com.meteoru.kalei.customtoast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private final String url = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&rsz=8&q=Android";
    private ArrayList<Article> arrayArticles;
    private ArticlesAdapter articlesAdapter;
    private ListView aListView;
    private String tmpString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arrayArticles = new ArrayList<Article>();
        articlesAdapter = new ArticlesAdapter(this, arrayArticles);
        aListView = (ListView) findViewById(R.id.articlesView);
        aListView.setAdapter(articlesAdapter);

        fetchJsonData();

    }

    private void fetchJsonData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // Handle success
                // Log.d(TAG, response.toString());
                JSONArray articlesJson = null;
                try {
                    articlesJson = response.getJSONObject("responseData").getJSONArray("results");  // Array of articles
                    for (int i = 0; i < articlesJson.length(); i++){
                        Article article = new Article();
                        JSONObject articleJson = new JSONObject();
                        articleJson = articlesJson.getJSONObject(i);
                        article.setTitle(articleJson.getString("title"));
                        article.setUrl(articleJson.getString("url"));
                        article.setImageUrl(articleJson.getJSONObject("image").getString("url"));
                        articlesAdapter.add(article);

                    }
                } catch (JSONException e) {
                    Log.d(TAG, "exxception");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // Handle failure
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
