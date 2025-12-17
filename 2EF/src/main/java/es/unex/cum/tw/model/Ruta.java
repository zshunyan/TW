package es.unex.cum.tw.model;

public class Ruta {
    private int idRuta;
    private String pathImagen;
    private String fechaIncorporacion;
    private int maximoUsuario;
    private int dificultad;
    private int metros;

    // Getters y setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public String getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(String fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public int getMaximoUsuario() {
        return maximoUsuario;
    }

    public void setMaximoUsuario(int maximoUsuario) {
        this.maximoUsuario = maximoUsuario;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }
}