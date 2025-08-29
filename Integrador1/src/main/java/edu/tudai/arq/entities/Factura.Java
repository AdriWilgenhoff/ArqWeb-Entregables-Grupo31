public class Factura{
    private int idFactura;
    private int idCliente;

    public Factura(int idFactura, String idCliente) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public int idFactura(){
        return this.idFactura;
    }
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return this.idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente=idCliente;
    }

}