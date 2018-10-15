package laz.kcomp.notcom.listicadelacompra21;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProductos extends ArrayAdapter<Articulo> {

	Activity contexto;

	AdapterProductos(Activity contexto, ArrayList<Articulo> datos) {
		super(contexto, R.layout.layout_productos, datos);
		this.contexto = contexto;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = contexto.getLayoutInflater();
		View item = inflater.inflate(R.layout.layout_productos, null);

		Articulo mielemento = getItem(position);

		TextView b = (TextView) item.findViewById(R.id.textoArticulo);

		b.setText(mielemento.getNombre());

		if (mielemento.isComprado()) {
			b.setPaintFlags(b.getPaintFlags() |
									Paint.STRIKE_THRU_TEXT_FLAG);
			b.setTextColor(Color.parseColor("#00FF00"));
		} else {
			b.setPaintFlags(b.getPaintFlags()
									& ~Paint.STRIKE_THRU_TEXT_FLAG);
			b.setTextColor(Color.parseColor("#FF0000"));
		}
		// Devolvemos la vista que dibuja la opción.
		return (item);
	}
}