package co.gostyn.karson.medijgastronomia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.button_medij)
    ImageView buttonMedij;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button1)
    Button button1;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       // App app = (App)getApplication();


    }




    @OnClick({R.id.button2, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

                break;
            case R.id.button1:
                intent = new Intent(this, Menu2Activity.class);
                startActivity(intent);
                break;
        }

    }



}
