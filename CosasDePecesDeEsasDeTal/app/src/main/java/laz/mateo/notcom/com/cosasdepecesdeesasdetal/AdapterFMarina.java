package laz.mateo.notcom.com.cosasdepecesdeesasdetal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterFMarina extends ArrayAdapter<FMarina> {

    Activity contexto;

    AdapterFMarina(Activity contexto, ArrayList<FMarina> datos) {
        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto, R.layout.layout_list, datos);
        this.contexto = contexto;
    }


    //Esto es lo que se invoca cada vez que haya que mostrar un elemento de la lista

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_list, null);

        FMarina mielemento = getItem(position);

        ImageView imagen = item.findViewById(R.id.imageView);
        TextView principal = item.findViewById(R.id.textPrincipal);
        TextView latin = item.findViewById(R.id.textLatinajo);
        TextView tam = item.findViewById(R.id.textTam);
        TextView habitat = item.findViewById(R.id.textHabitat);

        imagen.setImageResource(mielemento.getRef());
        principal.setText(mielemento.getNombre());
        latin.setText(mielemento.getLatin());
        tam.setText(mielemento.getTamano() + "cm");
        habitat.setText(mielemento.getHabitat());

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }
}