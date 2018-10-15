package laz.kcomp.notcom.listicadelacompra21;

import android.app.DialogFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogoAltas extends DialogFragment {
	public interface EditNameDialogListener {
		void onDialogPositiveClick(DialogFragment dialog, String name);
		void onDialogNegativeClick(DialogFragment dialog);
	}

	public DialogoAltas() {
		// Empty constructor required for DialogFragment
	}

	EditNameDialogListener mListener;

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = ( EditNameDialogListener) activity;
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
		final TextView texto = (TextView) v.findViewById(R.id.introducirTexto);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(v)
				.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {
						Toast toast1 = Toast.makeText(getActivity(),"Operación cancelada.", Toast.LENGTH_SHORT);
						toast1.show();
						dialog.cancel();
					}
				})
				.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String nombre = texto.getText().toString();
						mListener.onDialogPositiveClick(DialogoAltas.this,nombre);
					}
				});

		return builder.create();
	}


}

