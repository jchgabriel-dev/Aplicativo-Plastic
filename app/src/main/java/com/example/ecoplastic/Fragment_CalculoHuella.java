package com.example.ecoplastic;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment_CalculoHuella extends Fragment implements FragmentCallbacks {
    private MainCallbacks mainCallbacks;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public Fragment_CalculoHuella() {}

    public static Fragment_CalculoHuella newInstance(String param1, String param2) {
        Fragment_CalculoHuella fragment = new Fragment_CalculoHuella();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculo_huella, container, false);
        EditText editTextNumber1 = view.findViewById(R.id.editTextNumber1);
        EditText editTextNumber2 = view.findViewById(R.id.editTextNumber2);
        EditText editTextNumber3 = view.findViewById(R.id.editTextNumber3);
        EditText editTextNumber4 = view.findViewById(R.id.editTextNumber4);
        EditText[] edits = {editTextNumber1,editTextNumber2,editTextNumber3,editTextNumber4};

        Button btn_calcular = view.findViewById(R.id.btn_calcular);
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = calcular(edits);
                mainCallbacks.onMsgFromFragtoMain("CALCULO_HUELLA",String.valueOf(val));
            }
        });

        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCallbacks){
            mainCallbacks = (MainCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }
    public int calcular(EditText[] edits){
        int suma = 0;
        String cad;
        for(int val = 0; val < 4; val++){
            cad = edits[val].getText().toString();
            if (!TextUtils.isEmpty(cad))
                suma += Integer.parseInt(cad);
        }
        if(suma<10)
            return 1;
        else if (suma<15)
            return 2;
        else if(suma<20)
            return 3;
        else return 4;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
    }
}