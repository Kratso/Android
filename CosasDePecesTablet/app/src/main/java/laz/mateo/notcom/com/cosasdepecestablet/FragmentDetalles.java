package laz.mateo.notcom.com.cosasdepecestablet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class FragmentDetalles extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    public void mostrarDetalle(String imagen) {
        ImageView imagenView = (ImageView) getView().findViewById(R.id.imageView2);
        imagenView.setImageResource(getResources().getIdentifier(imagen,"drawable",this.getActivity().getPackageName()));
    }

}