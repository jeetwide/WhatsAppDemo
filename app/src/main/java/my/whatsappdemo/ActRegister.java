package my.whatsappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActRegister extends AppCompatActivity {

    private Button create_account_btn;
    private EditText user_email, user_pass;
    private TextView already_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_register);

        InitializeFields();

        already_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLoginActivity();
            }
        });
    }


    private void InitializeFields() {
        create_account_btn = findViewById(R.id.register_button);

        user_pass = findViewById(R.id.register_password);
        user_email = findViewById(R.id.register_email);
        already_have_account = findViewById(R.id.already_have_account);


    }

    private void SendUserToLoginActivity() {
        startActivity(new Intent(ActRegister.this, ActLogin.class));
    }
}
