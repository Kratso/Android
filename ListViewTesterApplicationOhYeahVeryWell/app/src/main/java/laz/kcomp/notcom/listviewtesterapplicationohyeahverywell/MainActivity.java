package laz.kcomp.notcom.listviewtesterapplicationohyeahverywell;


import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {



    private ArrayList<Opcion> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignamos el objeto ListView con el id de la etiqueta ListView del
        //layout principal
        ListView listaOpciones = (ListView)findViewById(R.id.ListaOpciones);
        // Creamos los objetos y los guardamos en un array
        datos.add(new Opcion("Atletismo", R.drawable.atletismo, false));
		datos.add(new Opcion("Baloncesto", R.drawable.baloncesto, false));
		datos.add(new Opcion("Fútbol", R.drawable.futbol, false));
		datos.add(new Opcion("Golf", R.drawable.golf, false));
		datos.add(new Opcion("Motociclismo", R.drawable.motociclismo, false));
		datos.add(new Opcion("Natación", R.drawable.natacion, false));
		datos.add(new Opcion("Ping Pong", R.drawable.pingpong, false));


        // Usamos un adaptador para dibujar las opciones de la lista
        AdaptadorOpciones adaptador = new AdaptadorOpciones(this,datos);

        // Establecemos el adaptador del Listview
        listaOpciones.setAdapter(adaptador);

        // Definimos el evento setOnItemClick
        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Si se hace clic sobre una opción mostramos un mensaje
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Has hecho clic en la '" + datos.get(position).getTitulo() + "'", Toast.LENGTH_LONG).show();
            }
        });
    }

	public void clicky(View view) {
		RadioButton rb = findViewById(R.id.radioButton);
		rb.setChecked(!rb.isChecked());
	}
}
