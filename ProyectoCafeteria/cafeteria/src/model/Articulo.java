package model;


// CLASE QUE ES UNA REPRESENTACION ABSTRACTA DE ARTICULO PARA MANIPULAR SUS DATOS


public class Articulo {

    private int idArticulo;
    private String nombre;
    private int precio;

    public Articulo(int idArticulo, String nombre, int precio) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * @return the idArticulo
     */
    public int getIdArticulo() {
        return idArticulo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

}
