package com.example.stringresource28042021;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.example.stringresource28042021.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    MutableLiveData<LoginForm> mMutableLiveData;
    LoginForm loginForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        loginForm = new LoginForm("","");
        mMutableLiveData = new MutableLiveData<>();
        mBinding.setMutable(mMutableLiveData);
        mBinding.setLifecycleOwner(this);

        mBinding.textInputEditTextMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginForm.email = String.valueOf(charSequence);
                mMutableLiveData.setValue(loginForm);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginForm.password = String.valueOf(charSequence);
                mMutableLiveData.setValue(loginForm);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

    }
}