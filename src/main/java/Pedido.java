import java.time.LocalDate;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private ModalidadEntrega modalidadEntrega;
    private EstadoPedido estadoPedido;
    private double distanciaKM;
    private boolean domocilioAceptado;
    private double costoDomicilio;
    private Cliente cliente;
    private EmpresaDomicilo empresaDomicilio;
    private DetallePedido detallePedido;

    public Pedido(String codigo, LocalDate fecha, ModalidadEntrega modalidadEntrega, EstadoPedido estadoPedido, double distanciaKM, boolean domocilioAceptado, double costoDomicilio, Cliente cliente, EmpresaDomicilo empresaDomicilio, DetallePedido detallePedido) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidadEntrega = modalidadEntrega;
        this.estadoPedido = estadoPedido;
        this.distanciaKM = distanciaKM;
        this.domocilioAceptado = domocilioAceptado;
        this.costoDomicilio = costoDomicilio;
        this.cliente = cliente;
        this.empresaDomicilio = empresaDomicilio;
        this.detallePedido = detallePedido;
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

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public double getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(double distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public boolean isDomocilioAceptado() {
        return domocilioAceptado;
    }

    public void setDomocilioAceptado(boolean domocilioAceptado) {
        this.domocilioAceptado = domocilioAceptado;
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

    public EmpresaDomicilo getEmpresaDomicilio() {
        return empresaDomicilio;
    }

    public void setEmpresaDomicilio(EmpresaDomicilo empresaDomicilio) {
        this.empresaDomicilio = empresaDomicilio;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }
}
