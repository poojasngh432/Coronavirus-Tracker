package com.myapp.coronavirusapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.myapp.coronavirusapp.api.Api;
import com.myapp.coronavirusapp.model.States;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView indiaStats, helpline, faq, news;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20, tv21, tv22, tv23, tv24, tv25, tv26, tv27;
    private ProgressDialog progressDialog;
    private States statesData;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        indiaStats = (ImageView) v.findViewById(R.id.india_stats);
        helpline = (ImageView) v.findViewById(R.id.helpline);
        faq = (ImageView) v.findViewById(R.id.faq);
        news = (ImageView) v.findViewById(R.id.news);

        indiaStats.setOnClickListener(this::onClick);
        helpline.setOnClickListener(this::onClick);
        faq.setOnClickListener(this::onClick);
        news.setOnClickListener(this::onClick);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.india_stats:
                callApi();
                break;

            case R.id.helpline:
                showHelplineDialog();
                break;

            case R.id.faq:
                showFAQDialog();
                break;
            case R.id.news:
                moveToNewsActivity();
                break;
        }
    }

    private void moveToNewsActivity() {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        startActivity(intent);
    }

    private void showCasesInIndiaStatsDialog() {

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());

        View view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.india_stats_dialog, null);

        tv1 = (TextView) view.findViewById(R.id.delhi_number);
        tv2 = (TextView) view.findViewById(R.id.andaman_number);
        tv3 = (TextView) view.findViewById(R.id.andhra_number);
        tv4 = (TextView) view.findViewById(R.id.bihar_number);
        tv5 = (TextView) view.findViewById(R.id.chandigarh_number);
        tv6 = (TextView) view.findViewById(R.id.chattisgarh_number);
        tv7 = (TextView) view.findViewById(R.id.goa_number);
        tv8 = (TextView) view.findViewById(R.id.gujarat_number);
        tv9 = (TextView) view.findViewById(R.id.haryana_number);
        tv10 = (TextView) view.findViewById(R.id.himachal_number);
        tv11 = (TextView) view.findViewById(R.id.jammu_number);
        tv12 = (TextView) view.findViewById(R.id.karnataka_number);
        tv13 = (TextView) view.findViewById(R.id.kerala_number);
        tv14 = (TextView) view.findViewById(R.id.ladakh_number);
        tv15 = (TextView) view.findViewById(R.id.mp_number);
        tv16 = (TextView) view.findViewById(R.id.maharashtra_number);
        tv17 = (TextView) view.findViewById(R.id.manipur_number);
        tv18 = (TextView) view.findViewById(R.id.mizoram_number);
        tv19 = (TextView) view.findViewById(R.id.odisha_number);
        tv20 = (TextView) view.findViewById(R.id.puducherry_number);
        tv21 = (TextView) view.findViewById(R.id.punjab_number);
        tv22 = (TextView) view.findViewById(R.id.rajasthan_number);
        tv23 = (TextView) view.findViewById(R.id.tamilnadu_number);
        tv24 = (TextView) view.findViewById(R.id.telangana_number);
        tv25 = (TextView) view.findViewById(R.id.uttarakhand_number);
        tv26 = (TextView) view.findViewById(R.id.up_number);
        tv27 = (TextView) view.findViewById(R.id.wb_number);

        tv1.setText(String.valueOf(statesData.getDelhi()));
        tv2.setText(String.valueOf(statesData.getAndamanAndNicobarIslands()));
        tv3.setText(String.valueOf(statesData.getAndhraPradesh()));
        tv4.setText(String.valueOf(statesData.getBihar()));
        tv5.setText(String.valueOf(statesData.getChandigarh()));
        tv6.setText(String.valueOf(statesData.getChhattisgarh()));
        tv7.setText(String.valueOf(statesData.getGoa()));
        tv8.setText(String.valueOf(statesData.getGujarat()));
        tv9.setText(String.valueOf(statesData.getHaryana()));
        tv10.setText(String.valueOf(statesData.getHimachalPradesh()));
        tv11.setText(String.valueOf(statesData.getJammuAndKashmir()));
        tv12.setText(String.valueOf(statesData.getKarnataka()));
        tv13.setText(String.valueOf(statesData.getKerala()));
        tv14.setText(String.valueOf(statesData.getLadakh()));
        tv15.setText(String.valueOf(statesData.getMadhyaPradesh()));
        tv16.setText(String.valueOf(statesData.getMaharashtra()));
        tv17.setText(String.valueOf(statesData.getManipur()));
        tv18.setText(String.valueOf(statesData.getMizoram()));
        tv19.setText(String.valueOf(statesData.getOdisha()));
        tv20.setText(String.valueOf(statesData.getPuducherry()));
        tv21.setText(String.valueOf(statesData.getPunjab()));
        tv22.setText(String.valueOf(statesData.getRajasthan()));
        tv23.setText(String.valueOf(statesData.getTamilNadu()));
        tv24.setText(String.valueOf(statesData.getTelengana()));
        tv25.setText(String.valueOf(statesData.getUttarakhand()));
        tv26.setText(String.valueOf(statesData.getUttarPradesh()));
        tv27.setText(String.valueOf(statesData.getWestBengal()));

        alertBuilder.setView(view);
        alertBuilder.setPositiveButton(getActivity().getString(R.string.alert_dialog_ok_label), null);
        final AlertDialog dialog = alertBuilder.create();
        dialog.show();

    }

    private void showHelplineDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());

        View view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.helpline_dialog, null);

        alertBuilder.setView(view);
        alertBuilder.setPositiveButton(getActivity().getString(R.string.alert_dialog_ok_label), null);
        final AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }


    private void showFAQDialog() {
        final AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(getActivity());

        View view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.faq_dialog_box,null);

        alertBuilder1.setView(view);
        alertBuilder1.setPositiveButton(getActivity().getString(R.string.alert_dialog_ok_label), null);
        final AlertDialog dialog = alertBuilder1.create();
        dialog.show();
    }


    private void callApi() {

            showLoadingDialog();
            //Creating a retrofit object

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.STATES_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            //creating the api interface
            Api api = retrofit.create(Api.class);
            Call<States> call = api.getStatesDataList();

            call.enqueue(new Callback<States>() {
                @Override
                public void onResponse(Call<States> call, Response<States> response) {
                    dismissLoadingDialog();
                    statesData = response.body();

                    if(statesData != null)
                        showCasesInIndiaStatsDialog();
                }

                @Override
                public void onFailure(Call<States> call, Throwable t) {
                    dismissLoadingDialog();
                }
            });
    }

    private void showLoadingDialog() {
        progressDialog = ProgressDialog.show(getActivity(), null, this.getString(R.string.loading), false, false);
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
