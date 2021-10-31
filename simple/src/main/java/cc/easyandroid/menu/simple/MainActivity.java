package cc.easyandroid.menu.simple;

import android.os.Bundle;

import cc.easyandroid.listfiltermenu.simple.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
