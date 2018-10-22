package laz.kcomp.notcom.listicadelacompra21;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.*;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogoAltas.EditNameDialogListener, DialogoModificaciones.EditNameDialogListenerM {

	ListView list;
	AdapterProductos adaptador;
	ArrayList<Articulo> productos = new ArrayList<>();
	FragmentManager fm = getFragmentManager();
	int indice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = findViewById(R.id.miLista);
		productos.add(new Articulo("Pan", true));
		productos.add(new Articulo("Leche", false));
		productos.add(new Articulo("Periódico", true));
		productos.add(new Articulo("Fruta", false));
		productos.add(new Articulo("Carne", true));
		adaptador = new AdapterProductos(this, productos);
		list.setAdapter(adaptador);
		registerForContextMenu(list); //Aplica el menú contextual a los items del ArrayList.
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Toast toast1;
                /*
                    -Se cambia la variable de tipo boolean asociada al objeto Artículo del adaptador.
                    -Se muestra un mensaje informativo de tipo Toast.
                    -Se vuelve a asignar el adaptador, ya modificado, al ListView.
                 */
				adaptador.getItem(i).setComprado(!adaptador.getItem(i).isComprado());
				String mensaje = !adaptador.getItem(i).isComprado()
										 ? "Has dejado de comprar '" + adaptador.getItem(i).getNombre() + "'."
										 : "Has comprado '" + adaptador.getItem(i).getNombre() + "'.";
				toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
				toast1.show();
				list.setAdapter(adaptador);
			}
		});
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	//Actúa según lo elegido en la ActionBar.
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.ItemOpcionCruz:
				DialogoAltas dialogo = new DialogoAltas();
				dialogo.show(fm, "Dialog Fragment");
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	//Asigna el menú contextual a un objeto.
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		if (v.getId() == R.id.miLista)
		{
			AdapterContextMenuInfo info =
					(AdapterContextMenuInfo) menuInfo;
			// Definimos la cabecera del menú contextual
			menu.setHeaderTitle("Operaciones sobre " + productos.get(info.position).getNombre());
			// Inflamos el menú contextual
			inflater.inflate(R.menu.menu_contextual, menu);
		}
	}

	//Asigna acciones a las diferentes opciones que nos ofrece el menú contextual.
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
			case R.id.borrar:
				productos.remove(info.position);
				adaptador = new AdapterProductos(this, productos);
				list.setAdapter(adaptador);
				return true;
			case R.id.editar:
				indice = info.position;
				DialogoModificaciones dialogo = new DialogoModificaciones();

				String nombreaeditar = productos.get(info.position).getNombre();
				//EditarDFragment editarDFragment= new EditarDFragment(nombreaeditar,info.position);
				dialogo.show(fm, "Dialog Fragment");
				DialogoModificaciones editarDFragment= new DialogoModificaciones();
				Bundle bundle = new Bundle(2);
				bundle.putString("nombreaeditar", nombreaeditar);
				bundle.putInt("posicion", info.position);
				editarDFragment.setArguments(bundle);
				editarDFragment.show(fm, "Edita articulo");

				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String name) {
		if (dialog.getClass().toString().equals("class laz.kcomp.notcom.listicadelacompra21.DialogoModificaciones")) {
			boolean comprado = productos.get(indice).isComprado(); //Guarda valor booleano.
			productos.remove(indice);//Borra el Articulo en "indice".
			productos.add(indice, new Articulo(name, comprado));//Añade el nuevo Articulo.
			adaptador = new AdapterProductos(this, productos);//Reinicia el adaptador.
			list.setAdapter(adaptador);//Asigna el adaptador al ListView.
		} else {
			//Si DialogoAltas ha sido el que ha llamado al método:
			adaptador.add(new Articulo(name, false));//Se añade un nuevo elemento al ListView.
		}
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {

	}

}
