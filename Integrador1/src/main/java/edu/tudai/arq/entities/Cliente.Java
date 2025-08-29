public class Cliente{
    private int idCliente;
    private String nombre;
    private String email;

    public Cliente(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    public int getIdCliente() {
            return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }


}