package com.example.coronavirustrackerapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.coronavirustrackerapp.adapter.NewsAdapter;
import com.example.coronavirustrackerapp.api.Api;
import com.example.coronavirustrackerapp.model.Article;
import com.example.coronavirustrackerapp.model.News;
import com.example.coronavirustrackerapp.model.News;
import com.example.coronavirustrackerapp.model.States;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView indiaStats, helpline, faq, news, img5, img6, img7, img8;
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
//        img5 = (ImageView) view.findViewById(R.id.imageview5);
//        img6 = (ImageView) view.findViewById(R.id.imageview6);
//        img7 = (ImageView) view.findViewById(R.id.imageview7);
//        img8 = (ImageView) view.findViewById(R.id.imageview8);

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
//            case R.id.imageview5:
//
//                break;
//            case R.id.imageview6:
//
//                break;
//            case R.id.imageview7:
//
//                break;
//            case R.id.imageview8:
//
//                break;
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

        tv1.setText(statesData.getDelhi().toString());
        tv2.setText(statesData.getAndamanAndNicobarIslands().toString());
        tv3.setText(statesData.getAndhraPradesh().toString());
        tv4.setText(statesData.getBihar().toString());
        tv5.setText(statesData.getChandigarh().toString());
        tv6.setText(statesData.getChhattisgarh().toString());
        tv7.setText(statesData.getGoa().toString());
        tv8.setText(statesData.getGujarat().toString());
        tv9.setText(statesData.getHaryana().toString());
        tv10.setText(statesData.getHimachalPradesh().toString());
        tv11.setText(statesData.getJammuAndKashmir().toString());
        tv12.setText(statesData.getKarnataka().toString());
        tv13.setText(statesData.getKerala().toString());
        tv14.setText(statesData.getLadakh().toString());
        tv15.setText(statesData.getMadhyaPradesh().toString());
        tv16.setText(statesData.getMaharashtra().toString());
        tv17.setText(statesData.getManipur().toString());
        tv18.setText(statesData.getMizoram().toString());
        tv19.setText(statesData.getOdisha().toString());
        tv20.setText(statesData.getPuducherry().toString());
        tv21.setText(statesData.getPunjab().toString());
        tv22.setText(statesData.getRajasthan().toString());
        tv23.setText(statesData.getTamilNadu().toString());
        tv24.setText(statesData.getTelengana().toString());
        tv25.setText(statesData.getUttarakhand().toString());
        tv26.setText(statesData.getUttarakhand().toString());
        tv27.setText(statesData.getWestBengal().toString());

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
               //     Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG).show();
                    if(statesData != null)
                        showCasesInIndiaStatsDialog();
                }

                @Override
                public void onFailure(Call<States> call, Throwable t) {
                    dismissLoadingDialog();
               //     Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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
