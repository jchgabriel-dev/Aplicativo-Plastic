package com.example.ecoplastic;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ecoplastic.ViewMetrics.GraficoResultados;

public class FragmentResultado extends Fragment implements FragmentCallbacks {
    private MainCallbacks mainCallbacks;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentResultado() {
    }

    public static FragmentResultado newInstance(String param1, String param2) {
        FragmentResultado fragment = new FragmentResultado();
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
        View view = inflater.inflate(R.layout.fragment_resultado, container, false);

        Bundle bundle = this.getArguments();
        int data = bundle.getInt("TOTAL");

        GraficoResultados grafico = view.findViewById(R.id.imageGrafico);
        grafico.setValue(data);

        Button btn_continuar = view.findViewById(R.id.btn_Continuar);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainCallbacks.onMsgFromFragtoMain("RESULTADO","salida");
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

    @Override
    public void onMsgFromMainToFragment(String strValue) {
    }
}