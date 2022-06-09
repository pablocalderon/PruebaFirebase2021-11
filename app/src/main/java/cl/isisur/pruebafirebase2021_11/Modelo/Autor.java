package cl.isisur.pruebafirebase2021_11.Modelo;

public class Autor {
    private String idAutor;
    private String nombre;
    private String estado;
    private String correo;



    public Autor() {
        this.idAutor ="";
        this.nombre = "";
        this.estado = "";
        this.correo="";
    }

    public Autor(String idAutor, String nombre, String estado,String correo) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.estado = estado;
        this.correo=correo;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor='" + idAutor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
