package laz.kcomp.notcom.adivinaelnumeronew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static int _n_clicks = 0;
    private static int _rgn;
    private static Random rng;
    private static String _message;
    private static int _victory_state;

    private static final String STATE_NUM_CLICKS = "_n_clicks";
    private static final String STATE_RGN = "_rgn";
    private static final String STATE_SUP_MSG = "_message";
    private static final String STATE_VICTORY_STATE = "_victory_state";

    private static final int STATE_NO_VICTORY = 0;
    private static final int STATE_VICTORY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rng = new Random();
        _rgn = 65;//rng.nextInt(100) + 1;
        TextView tv = findViewById(R.id.textView);
        _message = tv.getText().toString();
    }

    @Override
    public void onRestoreInstanceState(Bundle outInstance) {
        _rgn = outInstance.getInt(STATE_RGN);
        _n_clicks = outInstance.getInt(STATE_NUM_CLICKS);
        _message = outInstance.getString(STATE_SUP_MSG);
        _victory_state = outInstance.getInt(STATE_VICTORY_STATE);
        if (_victory_state == STATE_NO_VICTORY) {
            TextView tv = findViewById(R.id.textView);
            tv.setText(_message);
            String format = getResources().getString(R.string.numeroVeces);
            String cadFinal = String.format(format, _n_clicks);
            tv = findViewById(R.id.textView2);
            tv.setText(cadFinal);
        } else {
            paintVictory();
        }


    }

    private void paintVictory() {
        String format = getResources().getString(R.string.victoria);
        String cadFinal = String.format(format, _n_clicks);
        TextView tv = findViewById(R.id.textView);
        tv.setText(cadFinal);
        tv.setEnabled(false);
        EditText et = findViewById(R.id.editText);
        et.setEnabled(false);
        Button button = findViewById(R.id.button);
        button.setText(R.string.victoriaBoton);
        _victory_state = STATE_VICTORY;
        _message = cadFinal;
    }

    @Override
    public void onSaveInstanceState(Bundle outInstance) {
        super.onSaveInstanceState(outInstance);
        outInstance.putInt(STATE_NUM_CLICKS, _n_clicks);
        outInstance.putInt(STATE_RGN, _rgn);
        outInstance.putString(STATE_SUP_MSG, _message);
        outInstance.getInt(STATE_VICTORY_STATE, _victory_state);

    }

    public void onClickEvent(View view) {
        EditText et = findViewById(R.id.editText);

        if (_victory_state == STATE_NO_VICTORY) {
            if (et.getText().toString().matches("[0-9]+")) {
                int numUsuario = Integer.parseInt(et.getText().toString());
                _n_clicks++;
                String format = getResources().getQuantityString(R.plurals.numeroVeces, 1);
                String cadFinal = String.format(format, _n_clicks);
                TextView tv = findViewById(R.id.textView2);
                tv.setText(cadFinal);
                tv = findViewById(R.id.textView);
                if (numUsuario < _rgn) {
                    format = getResources().getString(R.string.falloMenor);
                    cadFinal = String.format(format, numUsuario);
                    tv.setText(cadFinal);
                    _message = cadFinal;
                    et.setText("");
                } else if (numUsuario > _rgn) {
                    format = getResources().getString(R.string.falloMayor);
                    cadFinal = String.format(format, numUsuario);
                    tv.setText(cadFinal);
                    _message = cadFinal;
                    et.setText("");
                } else {
                    et.setText("");
                    tv = findViewById(R.id.textView2);
                    tv.setText("");
                    paintVictory();
                }
            }
        } else {
            restart();
        }

    }

    private void restart() {
        TextView tv = findViewById(R.id.textView2);
        tv.setText(R.string.intentalo);
        tv = findViewById(R.id.textView);
        tv.setText(R.string.etiquetaSuperior);
        Button button = findViewById(R.id.button);
        button.setText(R.string.boton);
        _rgn = rng.nextInt(100) + 1;
        _n_clicks = 0;
        _victory_state = STATE_NO_VICTORY;
        _message = tv.getText().toString();

    }
}
