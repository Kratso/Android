package laz.kcomp.notcom.listviewtesterapplicationohyeahverywell;

/**
 * Created by usuario on 04/10/2016.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class AdaptadorOpciones extends ArrayAdapter<Opcion> {


        Activity contexto;
        // Contructor del adaptador usando el contexto de la aplicacion actual

        AdaptadorOpciones(Activity contexto, List<Opcion> datos) {

                // Llamamos al constructor de la clase superior
                //se le pasa el xml que genera la fila y el array de objetos
                super(contexto, R.layout.listitem_opcion, datos);
                this.contexto = contexto;
        }
        // Metodo que dibuja la Vista de cada Opcion
        // Se invoca cada vez que haya que mostrar un elemento de la lista.
    @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {


                View item = convertView;
                ViewHolder holder;
                if(item==null) {
                        LayoutInflater inflater = contexto.getLayoutInflater();
                        item = inflater.inflate(R.layout.listitem_opcion, null);

                        holder = new ViewHolder();
                        holder.titulo = item.findViewById(R.id.LblTitulo);

                        item.setTag(holder);
                }
                else
                {
                        holder = (ViewHolder)item.getTag();
                }



                //Mediante getItem cargamos cada uno de los objetos del array
                Opcion mielemento=getItem(position);


                holder.titulo.setText(mielemento.getTitulo());

                // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
                return(item);
        }

        class ViewHolder {
                TextView titulo;
                ImageView icono;
                RadioButton checked;
        }
}
