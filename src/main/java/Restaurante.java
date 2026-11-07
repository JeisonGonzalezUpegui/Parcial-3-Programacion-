import java.util.ArrayList;
import java.util.List;

public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        productos = new ArrayList<>();
        clientes = new ArrayList<>();
        pedidos = new ArrayList<>();
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

    // buscar producto por codigo
    public Producto buscarProducto(String codigo) {
        Producto productoEncontrado = null;

        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                productoEncontrado = producto;
                break;
            }
        }

        return productoEncontrado;
    }

    // buscar cliente por documento
    public Cliente buscarCliente(String numeroDocumento) {
        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNumeroDocumento().equals(numeroDocumento)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        return clienteEncontrado;
    }

    // buscar pedido por codigo
    public Pedido buscarPedido(String codigo) {
        Pedido pedidoEncontrado = null;

        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo().equals(codigo)) {
                pedidoEncontrado = pedido;
                break;
            }
        }

        return pedidoEncontrado;
    }

    // registrar producto sin repetir el codigo
    public boolean registrarProducto(Producto producto) {
        boolean agregado = false;
        Producto existente = buscarProducto(producto.getCodigo());

        if (existente == null) {
            productos.add(producto);
            agregado = true;
        }

        return agregado;
    }

    // registrar cliente sin repetir el documento
    public boolean registrarCliente(Cliente cliente) {
        boolean agregado = false;
        Cliente existente = buscarCliente(cliente.getNumeroDocumento());

        if (existente == null) {
            clientes.add(cliente);
            agregado = true;
        }

        return agregado;
    }

    // productos del menu que cumplen las condiciones de calidad
    public List<Producto> consultarProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.cumpleCondicionesCalidad()) {
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    // productos del menu que no cumplen las condiciones de calidad
    public List<Producto> consultarProductosNoCumplenCalidad() {
        List<Producto> noCumplen = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.cumpleCondicionesCalidad() == false) {
                noCumplen.add(producto);
            }
        }
        return noCumplen;
    }

    // registrar pedido pendiente de un cliente registrado, sin repetir el codigo
    public boolean registrarPedido(String numeroDocumento, Pedido pedido) {
        boolean registrado = false;
        Cliente cliente = buscarCliente(numeroDocumento);
        Pedido existente = buscarPedido(pedido.getCodigo());

        if (cliente != null && existente == null) {
            pedido.setCliente(cliente);
            pedidos.add(pedido);
            registrado = true;
        }

        return registrado;
    }

    // confirmar el pedido si cumple todas las verificaciones
    public boolean confirmarPedido(String codigoPedido) {
        boolean confirmado = false;
        Pedido pedido = buscarPedido(codigoPedido);

        if (pedido != null) {
            boolean valido = true;

            // el cliente debe estar registrado
            if (clientes.contains(pedido.getCliente()) == false) {
                valido = false;
            }

            // el pedido debe tener productos
            if (pedido.getDetalles().isEmpty()) {
                valido = false;
            }

            // productos del menu, cantidades mayores que cero y calidad
            for (DetallePedido detalle : pedido.getDetalles()) {
                if (productos.contains(detalle.getProducto()) == false) {
                    valido = false;
                } else if (detalle.getProducto().cumpleCondicionesCalidad() == false) {
                    valido = false;
                }

                if (detalle.getCantidad() <= 0) {
                    valido = false;
                }
            }

            // si es a domicilio, una empresa debe haber aceptado el servicio
            if (pedido.getModalidadEntrega() == ModalidadEntrega.DOMICILIO && pedido.isDomicilioAceptado() == false) {
                valido = false;
            }

            if (valido && pedido.confirmar()) {
                pedido.getCliente().agregarPedido(pedido);
                confirmado = true;
            }
        }

        return confirmado;
    }

    // marcar como entregado un pedido confirmado
    public boolean entregarPedido(String codigoPedido) {
        boolean entregado = false;
        Pedido pedido = buscarPedido(codigoPedido);

        if (pedido != null) {
            entregado = pedido.entregar();
        }

        return entregado;
    }

    // cancelar un pedido
    public boolean cancelarPedido(String codigoPedido) {
        boolean cancelado = false;
        Pedido pedido = buscarPedido(codigoPedido);

        if (pedido != null) {
            cancelado = pedido.cancelar();
        }

        return cancelado;
    }

    // pedidos realizados por un cliente, en cualquier estado
    public List<Pedido> consultarPedidosCliente(String numeroDocumento) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getNumeroDocumento().equals(numeroDocumento)) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }

    // ingresos propios del restaurante: pedidos confirmados o entregados, sin domicilios
    public double calcularIngresosRestaurante() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                total = total + pedido.calcularValorProductos();
            }
        }
        return total;
    }

    // valores cobrados por domicilios en pedidos confirmados o entregados
    public double calcularIngresosDomicilios() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                total = total + pedido.getCostoDomicilio();
            }
        }
        return total;
    }

    // producto con mas unidades vendidas
    public Producto obtenerProductoMasVendido() {
        Producto masVendido = null;
        int maxUnidades = 0;

        for (Producto producto : productos) {
            int unidades = 0;

            for (Pedido pedido : pedidos) {
                if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido detalle : pedido.getDetalles()) {
                        if (detalle.getProducto() == producto) {
                            unidades = unidades + detalle.getCantidad();
                        }
                    }
                }
            }

            if (unidades > maxUnidades) {
                maxUnidades = unidades;
                masVendido = producto;
            }
        }

        return masVendido;
    }

    // categoria que genera mayores ingresos
    public String obtenerCategoriaMayoresIngresos() {
        double totalPlatos = 0;
        double totalBebidas = 0;
        double totalPostres = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalle : pedido.getDetalles()) {
                    if (detalle.getProducto() instanceof PlatoFuerte) {
                        totalPlatos = totalPlatos + detalle.calcularSubtotal();
                    } else if (detalle.getProducto() instanceof Bebida) {
                        totalBebidas = totalBebidas + detalle.calcularSubtotal();
                    } else if (detalle.getProducto() instanceof Postre) {
                        totalPostres = totalPostres + detalle.calcularSubtotal();
                    }
                }
            }
        }

        String categoria = null;
        double maxIngresos = 0;

        if (totalPlatos > maxIngresos) {
            maxIngresos = totalPlatos;
            categoria = "Plato fuerte";
        }
        if (totalBebidas > maxIngresos) {
            maxIngresos = totalBebidas;
            categoria = "Bebida";
        }
        if (totalPostres > maxIngresos) {
            maxIngresos = totalPostres;
            categoria = "Postre";
        }

        return categoria;
    }
}
