package com.laz.notcom.faunamarina;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.samuel.faunamarina.R;

public class MainActivity extends AppCompatActivity implements FragmentListado.FMarinaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentListado frgListado =
                (FragmentListado) getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setFMarinaListener(this);
    }

    @Override
    public void onFMarinaSeleccionado(FMarina fm) {

        if(findViewById(R.id.FrgDetalle) != null) {
            ((FragmentDetalles)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(String.valueOf(fm.getRef()));
        }
        else {
            Intent i = new Intent(this, MainActivity2.class);
            i.putExtra(MainActivity2.EXTRA_TEXTO,fm.getRef());
            startActivity(i);
        }
    }
}
