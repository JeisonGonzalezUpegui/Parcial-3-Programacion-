import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String codigo;
    private LocalDate fecha;
    private ModalidadEntrega modalidadEntrega;
    private EstadoPedido estado;
    private double distanciaKm;
    private boolean domicilioAceptado;
    private double costoDomicilio;
    private Cliente cliente;
    private EmpresaDomicilio empresaDomicilio;
    private ArrayList<DetallePedido> detalles;

    public Pedido(String codigo, LocalDate fecha, Cliente cliente, ModalidadEntrega modalidadEntrega) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.modalidadEntrega = modalidadEntrega;
        this.estado = EstadoPedido.PENDIENTE;
        this.distanciaKm = 0;
        this.domicilioAceptado = false;
        this.costoDomicilio = 0;
        detalles = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ModalidadEntrega getModalidadEntrega() {
        return modalidadEntrega;
    }

    public void setModalidadEntrega(ModalidadEntrega modalidadEntrega) {
        this.modalidadEntrega = modalidadEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public boolean isDomicilioAceptado() {
        return domicilioAceptado;
    }

    public void setDomicilioAceptado(boolean domicilioAceptado) {
        this.domicilioAceptado = domicilioAceptado;
    }

    public double getCostoDomicilio() {
        return costoDomicilio;
    }

    public void setCostoDomicilio(double costoDomicilio) {
        this.costoDomicilio = costoDomicilio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EmpresaDomicilio getEmpresaDomicilio() {
        return empresaDomicilio;
    }

    public void setEmpresaDomicilio(EmpresaDomicilio empresaDomicilio) {
        this.empresaDomicilio = empresaDomicilio;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        return "Pedido: " + codigo
                + "\nFecha: " + fecha
                + "\nCliente: " + cliente.getNombreCompleto()
                + "\nModalidad de entrega: " + modalidadEntrega
                + "\nEstado: " + estado
                + "\nCosto del domicilio: " + costoDomicilio
                + "\nTotal: " + calcularTotal();
    }

    // agregar un producto con su cantidad al pedido
    public void agregarDetalle(Producto producto, int cantidad) {
        DetallePedido detalle = new DetallePedido(producto, cantidad);
        detalles.add(detalle);
    }

    // solicitar el domicilio a la empresa, solo para pedidos a domicilio pendientes
    public boolean asignarDomicilio(EmpresaDomicilio empresa, double distanciaKm) {
        boolean aceptado = false;

        if (modalidadEntrega == ModalidadEntrega.DOMICILIO && estado == EstadoPedido.PENDIENTE
                && empresa != null && empresa.solicitarServicio(distanciaKm)) {
            this.empresaDomicilio = empresa;
            this.distanciaKm = distanciaKm;
            this.domicilioAceptado = true;
            this.costoDomicilio = empresa.calcularCosto(distanciaKm);
            aceptado = true;
        }

        return aceptado;
    }

    // valor de los productos, sin domicilio
    public double calcularValorProductos() {
        double total = 0;
        for (DetallePedido detalle : detalles) {
            total = total + detalle.calcularSubtotal();
        }
        return total;
    }

    // valor de los productos + costo del domicilio
    public double calcularTotal() {
        return calcularValorProductos() + costoDomicilio;
    }

    // solo un pedido pendiente se puede confirmar
    public boolean confirmar() {
        boolean exito = false;

        if (estado == EstadoPedido.PENDIENTE) {
            estado = EstadoPedido.CONFIRMADO;
            exito = true;
        }

        return exito;
    }

    // solo un pedido confirmado se puede entregar
    public boolean entregar() {
        boolean exito = false;

        if (estado == EstadoPedido.CONFIRMADO) {
            estado = EstadoPedido.ENTREGADO;
            exito = true;
        }

        return exito;
    }

    // se puede cancelar un pedido pendiente o confirmado
    public boolean cancelar() {
        boolean exito = false;

        if (estado == EstadoPedido.PENDIENTE || estado == EstadoPedido.CONFIRMADO) {
            estado = EstadoPedido.CANCELADO;
            exito = true;
        }

        return exito;
    }
}
