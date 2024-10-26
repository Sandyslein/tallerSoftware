package Entidades;

public class Ciudad {

    private int id;
    private String ciudad;

    public Ciudad() {
        // Constructor vacío necesario para reflexión en JPA
    }

    public Ciudad(int id, String ciudad) {
        this.id = id;
        this.ciudad = ciudad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }


}
