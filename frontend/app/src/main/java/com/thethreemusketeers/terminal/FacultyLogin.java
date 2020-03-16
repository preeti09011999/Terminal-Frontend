package com.thethreemusketeers.terminal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.thethreemusketeers.terminal.JSONResponseObject.MessageAndStatusResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FacultyLogin extends AppCompatActivity {

    TextView forgotPasswordLink , signUpLink;
    EditText facultyId , facultyPassword;
    Button loginBtn;
    TextView attentionRequiredTowardsFacultyId , attentionRequiredTowardsFacultyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        forgotPasswordLink = findViewById(R.id.forgot_password);
        signUpLink = findViewById(R.id.sign_up);
        facultyId = findViewById(R.id.facultyId);
        facultyPassword = findViewById(R.id.facultyPassword);
        loginBtn = findViewById(R.id.login_btn);
        attentionRequiredTowardsFacultyId = findViewById(R.id.attention_required_on_facultyId);
        attentionRequiredTowardsFacultyPassword = findViewById(R.id.attention_required_on_facultyPassword);


        SpannableString forgottenPassword = new SpannableString("Forgotten your details? Get help with signing in.");
        SpannableString signUp = new SpannableString("Don't have an account? Sign Up");
        ClickableSpan forgottenPasswordActivityLauncher = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //launch new activity
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds){
                ds.setUnderlineText(false);
                ds.setColor( getResources().getColor(R.color.colorMatteGreen));
                ds.setFakeBoldText(true);
            }
        };

        ClickableSpan signUpActivityLauncher = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //launch new activity
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds){
                ds.setColor(getResources().getColor(R.color.colorMatteGreen));
                ds.setUnderlineText(true);
            }
        };

        forgottenPassword.setSpan(forgottenPasswordActivityLauncher, 24, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUp.setSpan(signUpActivityLauncher, 23, 30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgotPasswordLink.setText(forgottenPassword);
        signUpLink.setText(signUp);

        final String FacReqURL = "https://terminal-bpit.herokuapp.com/faculty/login";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        //CHECKING FOR TEXTCHANGE ON USERNAME EDIT TEXT FIELD
        facultyId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")){
                    attentionRequiredTowardsFacultyId.setAlpha(0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        facultyPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")){
                    attentionRequiredTowardsFacultyPassword.setAlpha(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECKING WHETHER ALL THE FIELDS ARE FILLED WITH USERDATA OR NOT
                String idValue = facultyId.getText().toString();
                String passwordValue = facultyPassword.getText().toString();
                Boolean proceedingNextFlagId = true;
                Boolean proceedingNextFlagPassword = true;
                if(idValue.equals("")){
                    attentionRequiredTowardsFacultyId.setAlpha(1);
                    proceedingNextFlagId = false;
                }
                else{
                    attentionRequiredTowardsFacultyId.setAlpha(0);
                }
                if(passwordValue.equals("")){
                    attentionRequiredTowardsFacultyPassword.setAlpha(1);
                    proceedingNextFlagPassword = false;
                }
                else
                    attentionRequiredTowardsFacultyPassword.setAlpha(0);

                if(proceedingNextFlagId){
                    if(proceedingNextFlagPassword){
                        startActivity(new Intent(FacultyLogin.this, CreateProfile2.class));
                    }
                    else{
                        startActivity(new Intent(FacultyLogin.this, MainActivity.class));
                    }
                }
            }
        });

    }


}
