package com.myapp.coronavirusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.myapp.coronavirusapp.api.Api;
import com.myapp.coronavirusapp.model.Countries;
import com.myapp.coronavirusapp.model.Global;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigation;
    private Global globalData;
    private List<Countries> countriesData;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callApi();
        bottomNavigation = findViewById(R.id.bottom_navigation);
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
                    Log.d("Hi", "success" + response.body().toString());
                    globalData = response.body();
                    if(globalData != null)
                        setGlobalData();
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
                }

                @Override
                public void onFailure(Call<List<Countries>> call, Throwable t) {
                    dismissLoadingDialog();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            if(globalData != null && countriesData != null){
                dismissLoadingDialog();
            }
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
