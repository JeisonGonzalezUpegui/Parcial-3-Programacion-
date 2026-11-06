import java.util.ArrayList;

public class Restaurante {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Pedido> ListaPedidos;

    public Restaurante(String nombreComercial, String nit, String direccion, ArrayList<Cliente> listaClientes, ArrayList<Producto> listaProductos, ArrayList<Pedido> listaPedidos) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.listaClientes = listaClientes;
        this.listaProductos = listaProductos;
        ListaPedidos = listaPedidos;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return ListaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        ListaPedidos = listaPedidos;
    }
}
