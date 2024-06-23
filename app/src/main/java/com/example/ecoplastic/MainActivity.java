package com.example.ecoplastic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainCallbacks{
    private Fragment_CalculoHuella fragment_c;
    private FragmentResultado fragment_r;
    private FragmentBienvenida fragment_b;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        fragment_b = FragmentBienvenida.newInstance("","");
        ft.add(R.id.fragment_calculo, fragment_b);
        ft.commit();
    }

    @Override
    public void onMsgFromFragtoMain(String sender, String strValue) {
        if (sender.equals("BIENVENIDO")) {
            try {
                ft = getSupportFragmentManager().beginTransaction();
                fragment_c = Fragment_CalculoHuella.newInstance("","");
                ft.replace(R.id.fragment_calculo, fragment_c);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

        if (sender.equals("CALCULO_HUELLA")) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("TOTAL",Integer.parseInt(strValue));

                ft = getSupportFragmentManager().beginTransaction();
                fragment_r = FragmentResultado.newInstance("","");
                fragment_r.setArguments(bundle);

                ft.replace(R.id.fragment_calculo, fragment_r);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("RESULTADO")) {
            try {
                ft = getSupportFragmentManager().beginTransaction();
                ft.remove(fragment_r);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}