package laz.kcomp.notcom.listviewtesterapplicationohyeahverywell;

/**
 * Created by usuario on 04/10/2016.
 */

public class Opcion {
    // Cada opcion tiene un titulo y un subtitulo
    private String titulo;
    private int icono;
    private boolean checked;

    public Opcion(String titulo, int icono, boolean checked) {
        this.titulo = titulo;
        this.icono = icono;
        this.checked = checked;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitulo(){
        return titulo;
    }



}
