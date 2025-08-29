public class Factura_Producto{
    private int idFactura;
    private int idProducto;
    private int cantidad;

    public Factura_Producto(int idFactura , int idProducto, int cantidad) {
            this.idFactura = idFactura;
            this.idProducto = idProducto;
            this.cantidad = cantidad;
    }

    public int getIdFactura () {
            return this.idFactura;
    }
    public void   setIdFactura(int idFactura) {
            this.idFactura = idFactura;
    }
    public int getIdProducto () {
            return this.idProducto;
    }
    public void  setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getCantidad () {
            return this.cantidad;
    }
    public void  setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}