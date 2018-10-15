package laz.kcomp.notcom.listicadelacompra21;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogoModificaciones extends DialogFragment {
	public interface EditNameDialogListenerM {
		void onDialogPositiveClick(DialogFragment dialog, String name);

		void onDialogNegativeClick(DialogFragment dialog);
	}

	public DialogoModificaciones() {
		// Empty constructor required for DialogFragment
	}

	DialogoModificaciones.EditNameDialogListenerM mListener;

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (DialogoModificaciones.EditNameDialogListenerM) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
												 + " must implement ShareDialogListener");
		}
	}

	LayoutInflater l;
	View v;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {


		l = getActivity().getLayoutInflater();
		v = (l.inflate(R.layout.layout_dialog_altas, null));
		TextView t1 = (TextView) v.findViewById(R.id.text1);
		t1.setText("Modifica Artículo");
		final TextView texto = (TextView) v.findViewById(R.id.introducirTexto);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(v)
				.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {
						Toast toast1 = Toast.makeText(getActivity(), "Operación cancelada.", Toast.LENGTH_SHORT);
						toast1.show();
						dialog.cancel();
					}
				})
				.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String nombre = texto.getText().toString();
						mListener.onDialogPositiveClick(DialogoModificaciones.this, nombre);
					}
				});

		return builder.create();
	}
}
