package laz.mateo.notcom.com.cosasdepecestablet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_TEXTO =
            "imagen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagengrande);

        FragmentDetalles detalle =
                (FragmentDetalles)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);

        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
    }
}
