package com.example.recipesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Page2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue; // for volley


    private DrawerLayout myDrawer;
    private ActionBarDrawerToggle myToggle;
    /* Navigation Drawer */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent i = new Intent(Page2.this,Page2.class);
                break;
            case R.id.categories:
                Intent in = new Intent(Page2.this,Categories.class);
                startActivity(in);
                break;

            case R.id.shopping:
                Intent intent = new Intent(Page2.this, Page3.class);
                startActivity(intent);
                break;
        }
        myDrawer.closeDrawer(GravityCompat.START);
        return true;
    }






    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        setTitle("Home");

      mRecyclerView = findViewById(R.id.recycler_view);
       mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();







        /*Navigation Drawer */
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


        myDrawer = findViewById(R.id.myDrawer);
        myToggle = new ActionBarDrawerToggle(this,myDrawer,R.string.open,R.string.close);
        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






    }

    private void parseJSON() {
        String url = "https://raw.githubusercontent.com/raywenderlich/recipes/06131f61510f6343332666de6ac111087454f991/Recipes.json?fbclid=IwAR195mrLxHt5Lmn1IAygb0Q56zxyFsMxXGP0mhotqxhl2Cu-XM9Y_pUOWnY";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("ingredients");
                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject ingredients = jsonArray.getJSONObject(i);
                        String name = ingredients.getString("name");
                        String imageUrl = ingredients.getString("imageURL");
                        String type = ingredients.getString("type");

                        mExampleList.add(new ExampleItem(name,imageUrl,type));
                    }

                    mExampleAdapter = new ExampleAdapter(Page2.this,mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }


}
