package cc.easyandroid.menu.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cc.easyandroid.listfiltermenu.simple.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainFragment fragment=new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment,"tag").commit();

    }

}
