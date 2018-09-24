package laz.kcomp.notcom.pulsame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button _boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _boton = (Button) findViewById(R.id.button);

        _boton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        botonPulsado();
                    }
                }
        );
    }

    private void botonPulsado() {
        Context context = getApplicationContext();
        CharSequence text = "Introduzca un número para convertir";
        int duration = Toast.LENGTH_SHORT;
        EditText _distancia = (EditText) findViewById(R.id.distancia);
        RadioButton _rb1 = (RadioButton) findViewById(R.id.radioButton);
        TextView _tv = (TextView) findViewById(R.id.textView);
        String conv;
        if (_distancia.getText().length() == 0) {
            Toast  toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (_rb1.isChecked()) {
                conv = Double.parseDouble(_distancia.getText().toString()) * 1.60934 + "";
                _tv.setText(conv);
            } else {
                conv = Double.parseDouble(_distancia.getText().toString()) / 1.60934 + "";
                _tv.setText(conv);
            }
        }
    }
}
