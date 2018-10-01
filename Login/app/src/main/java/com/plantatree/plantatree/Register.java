package com.plantatree.plantatree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stream53.plantatree.plantatree.R;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    EditText EMAIL, PASS, CONFIRM;
    Button REGISTER;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        EMAIL = (EditText)findViewById(R.id.edit_text_email);
        PASS = (EditText)findViewById(R.id.edit_text_pass);
        CONFIRM = (EditText)findViewById(R.id.edit_text_confirm);
        REGISTER = (Button)findViewById(R.id.button_register);
        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String grab_email = EMAIL.getText().toString();
                String grab_pass = PASS.getText().toString();
                String grab_confirm = CONFIRM.getText().toString();

                if(grab_email.equals("")||grab_pass.equals("")||grab_confirm.equals("")){
                    Toast.makeText(getApplicationContext(), "Whats up?",Toast.LENGTH_SHORT).show();
                }else{
                    if(grab_pass.equals(grab_confirm)){
                        Boolean validateEmail = db.validateEmail(grab_email);
                        if(validateEmail==true){
                            Boolean insert = db.addUser(grab_email, grab_pass);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Chur Bro", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "We've got that email my bro", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });
    }
}
