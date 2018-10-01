package com.stream53.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper DATABASE;
    EditText EMAIL, PASSWORD, CONFIRM_PASS;
    Button REGISTER, LOGIN_REG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DATABASE = new DatabaseHelper(this);

        EMAIL=(EditText)findViewById(R.id.edit_text_emailReg);
        PASSWORD =(EditText)findViewById(R.id.edit_text_regPass);
        CONFIRM_PASS =(EditText)findViewById(R.id.edit_text_confirm);
        REGISTER=(Button)findViewById(R.id.button_register);
        LOGIN_REG=(Button)findViewById(R.id.button_regLogin);

        //LOGIN BUTTON, ON REGISTER ACTIVITY LISTENER
        LOGIN_REG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        //REGISTER BUTTON, ON REGISTER ACTIVITY LISTENER
        REGISTER.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String s1 = EMAIL.getText().toString();
                String s2 = PASSWORD.getText().toString();
                String s3 = CONFIRM_PASS.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s2.equals(s3)){
                        Boolean chkemail = DATABASE.validateEmail(s1);
                        if(chkemail==true){
                            Boolean insert = DATABASE.addAccount(s1,s2);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}
