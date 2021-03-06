package my.whatsappdemo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private android.support.v7.widget.Toolbar mToolbar;
    private ViewPager viewPager;
    private TabLayout myTablayout;

    private FirebaseUser currentUser;

    private TabAccessorAdapter mytabaccessoradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar=findViewById(R.id.main_page_toolbar);

        setSupportActionBar(mToolbar);
       getSupportActionBar().setTitle("WhatsApp");
       viewPager=findViewById(R.id.main_tabs_pager);
       mytabaccessoradapter= new TabAccessorAdapter(getSupportFragmentManager());
       viewPager.setAdapter(mytabaccessoradapter);

        myTablayout=findViewById(R.id.mainTabs);
        myTablayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser == null){
            SendUserToLoginActivity();
        }

    }

    private void SendUserToLoginActivity() {
        startActivity(new Intent(MainActivity.this,ActLogin.class));

    }

}
