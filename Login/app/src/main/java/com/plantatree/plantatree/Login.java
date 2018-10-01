package com.plantatree.plantatree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stream53.plantatree.plantatree.R;

public class Login extends AppCompatActivity {

    EditText EMAIL_LOGIN, PASS_LOGIN;
    Button LOGIN;
    DatabaseHelper DATABASE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DATABASE = new DatabaseHelper(this);
        EMAIL_LOGIN = (EditText)findViewById(R.id.edit_text_emailLogin);
        PASS_LOGIN = (EditText)findViewById(R.id.edit_text_loginPass);
        LOGIN = (Button)findViewById(R.id.button_login);

        //LOGIN BUTTON, ON LOGIN ACTIVITY LISTENER
        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EMAIL_LOGIN.getText().toString();
                String password = PASS_LOGIN.getText().toString();
                Boolean Chkemailpass = DATABASE.emailPassword(email, password);
                if(Chkemailpass==true){
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();

                    /*SEND USER TO MAIN ACTIVITY ADD HERE*/
                    
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong email or Passowrd", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
