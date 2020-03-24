package com.example.coronavirustrackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.coronavirustrackerapp.adapter.CountryAdapter;
import com.example.coronavirustrackerapp.api.Api;
import com.example.coronavirustrackerapp.model.Countries;
import com.example.coronavirustrackerapp.model.Global;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private Global globalData;
    private List<Countries> countriesData;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Hi","onCreate Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callApi();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        Log.d("Hi","2");
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance());
    }

    private void callApi() {
        if(isNetworkAvailable()){
            showLoadingDialog();
            //Creating a retrofit object
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            //creating the api interface
            Api api = retrofit.create(Api.class);
            Call<Global> call = api.getGlobalData();
            Call<List<Countries>> call2 = api.getCountriesData();

            call.enqueue(new Callback<Global>() {
                @Override
                public void onResponse(Call<Global> call, Response<Global> response) {
                    dismissLoadingDialog();
                    Log.d("Hi", "success" + response.body().toString());
                    globalData = response.body();
                    if(globalData != null)
                        setGlobalData();
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Global> call, Throwable t) {
                    dismissLoadingDialog();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            call2.enqueue(new Callback<List<Countries>>() {
                @Override
                public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                    dismissLoadingDialog();
                    countriesData = response.body();
                    if(countriesData != null) {
                        setCountriesData();
                    }
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<Countries>> call, Throwable t) {
                    dismissLoadingDialog();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else {
            Toast.makeText(this, this.getString(R.string.network_not_available), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public Global setGlobalData() {
        Log.d("Hi","setGlobalData");
        return globalData;
    }
    public List<Countries> setCountriesData(){
        return countriesData;
    }

    public void openFragment(Fragment fragment) {
        Log.d("Hi","8");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(HomeFragment.newInstance());
                            return true;
                        case R.id.navigation_globe:
                            openFragment(GlobeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_countries:
                            openFragment(CountriesFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_about:
                            openFragment(AboutFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    private void showLoadingDialog() {
        progressDialog = ProgressDialog.show(this, null, this.getString(R.string.loading), false, false);
    }

    private void dismissLoadingDialog() {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            progressDialog = null;
        }
    }
}
