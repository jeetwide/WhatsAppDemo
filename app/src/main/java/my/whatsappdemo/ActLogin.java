package my.whatsappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class ActLogin extends AppCompatActivity {

    private FirebaseUser currentUser;
    private Button login_button, phone_login_button;
    private EditText user_email, user_pass;
    private TextView need_new_account, forget_pass_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        InitializeFields();

        need_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegisterActivity();
            }
        });

    }

    private void InitializeFields() {
        login_button = findViewById(R.id.login_button);
        phone_login_button = findViewById(R.id.phone_button);
        user_email = findViewById(R.id.login_email);
        user_pass = findViewById(R.id.login_password);
        need_new_account = findViewById(R.id.need_new_account);
        forget_pass_link = findViewById(R.id.forget_password_link);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser != null) {
            SendUserToMainActivity();
        }

    }

    private void SendUserToMainActivity() {
        startActivity(new Intent(ActLogin.this, MainActivity.class));
    }

    private void SendUserToRegisterActivity() {
        startActivity(new Intent(ActLogin.this, ActRegister.class));
    }
}
