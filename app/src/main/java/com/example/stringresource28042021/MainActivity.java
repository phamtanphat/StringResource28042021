package com.example.stringresource28042021;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.stringresource28042021.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("mycache",MODE_PRIVATE);

        if (mSharedPreferences != null && getLocal().length() > 0){
            Locale myLocale = new Locale(getLocal());
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.textInputEditTextMail.getText().toString();
                String password = mBinding.textInputEditTextPassword.getText().toString();


                String labelEmail = getResources().getString(R.string.hint_email);
                String labelPassword = getResources().getString(R.string.hint_password);
                mBinding.textViewInfo.setText(String.format("%s : %s \r\n%s : %s",labelEmail,email,labelPassword,password));
            }
        });

        mBinding.imageLanguageVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("vi");
            }
        });
        mBinding.imageLanguageEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
            }
        });
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        finish();
        startActivity(refresh);
        saveLocal(lang);
    }
    private String getLocal(){
        return mSharedPreferences.getString("local","");
    }
    private void saveLocal(String local){
        if (mSharedPreferences != null){
            mEditor = mSharedPreferences.edit();
            mEditor.putString("local",local);
            mEditor.apply();
        }

    }
}