package com.example.hoang.thenews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hoang.myapplication.R;
import com.example.hoang.thenews.Api.ArticleApi;
import com.example.hoang.thenews.util.EndlessScrollListener;
import com.example.hoang.thenews.adapter.ArticleAdapter;
import com.example.hoang.thenews.model.Articles;
import com.example.hoang.thenews.model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnCLickSearch;
    GridView mGridView;
    ArrayList<News> mShowsList;
    ArticleAdapter adapter;
    Toolbar toolbarSearch;
    Handler mHandler;
    String fq = "news_desk:(\"Education\" \"Sports\" \"Arts\")";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearch = (EditText) findViewById(R.id.edtSearchnha);
        btnCLickSearch = (Button) findViewById(R.id.btnSearch);
        mGridView = (GridView) findViewById(R.id.gvItem);
        mShowsList = new ArrayList<News>();

        toolbarSearch = (Toolbar) findViewById(R.id.ToolbarSearch);
        setSupportActionBar(toolbarSearch);
        adapter = new ArticleAdapter(getApplicationContext(), R.layout.view_layout, mShowsList);
        searchButton();
        webViewActicle();
        loadmore();git config --get remote.origin.url

    }

    private void loadmore() {
        mGridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                fetchQuery(edtSearch.getText().toString().trim(), page);
                return false;
            }
        });
    }

    private void webViewActicle() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = mShowsList.get(position).getWebUrl();
                Intent i = new Intent(getApplicationContext(), WebviewActivity.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });
    }

    private void searchButton() {
        btnCLickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearch.getText().toString().length() > 0) {
                    mShowsList.clear();
                    fetchQuery(edtSearch.getText().toString().trim(), 1);
                    Toast.makeText(SearchActivity.this, "Search for: " + edtSearch.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchActivity.this, "please enter key to search", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItemSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItemSearch.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                break;
            case R.id.itOption:
                Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchQuery(String qSearch, int curentPage) {
        ArticleApi.Factory.getInstance().getArticle(qSearch, 1, getString(R.string.api_key)).enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                for (int i = 0; i < 10; i++) {
                    try {
                        String snippet = response.body().getResponse().getDocs().get(i).getSnippet();
                        String webUrl = response.body().getResponse().getDocs().get(i).getWebUrl();
                        int MultimediaSize = response.body().getResponse().getDocs().get(i).getMultimedia().size();
                        if (MultimediaSize != 0) {
                            String url = response.body().getResponse().getDocs().get(i).getMultimedia().get(0).getUrl();
                            Log.d("abcdef", String.valueOf(response.isSuccessful()));
                            mShowsList.add(new News(snippet, "http://www.nytimes.com/" + url, webUrl));
                        } else {
                            mShowsList.add(new News(snippet, "http://www.freeiconspng.com/uploads/no-image-icon-15.png", webUrl));
                        }
                        adapter.notifyDataSetChanged();
                        mGridView.setAdapter(adapter);
                    } catch (Exception e) {
                        Log.e("BUG", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("abc", t.getMessage());

            }
        });

    }

}