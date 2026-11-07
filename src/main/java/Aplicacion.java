import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Sabor & Tradicion", "900123456", "Armenia, Quindio");
        RapidoExpress rapidoExpress = new RapidoExpress(5000, 1500, 12);
        EntregaSegura entregaSegura = new EntregaSegura(7000, 1000, 3000, 20);

        int opcion;
        do {
            System.out.println("=====Menu Sabor & Tradicion=====");

            System.out.println("---Menu del restaurante---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Agregar Cliente");

            System.out.println("---Consultas de productos---");
            System.out.println("3. Productos disponibles");
            System.out.println("4. Productos que no cumplen calidad");

            System.out.println("---Pedidos---");
            System.out.println("5. Registrar Pedido");
            System.out.println("6. Confirmar Pedido");
            System.out.println("7. Entregar Pedido");
            System.out.println("8. Cancelar Pedido");
            System.out.println("9. Pedidos de un cliente");

            System.out.println("---Reportes---");
            System.out.println("10. Ingresos del restaurante");
            System.out.println("11. Ingresos por domicilios");
            System.out.println("12. Producto mas vendido");
            System.out.println("13. Categoria con mayores ingresos");

            System.out.println("0. Salir");

            System.out.println("Seleccione la opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("---Agregar Producto---");

                    System.out.println("Tipo: 1.Plato fuerte 2.Bebida 3.Postre");
                    int tipoProducto = sc.nextInt();
                    sc.nextLine();

                    if (tipoProducto < 1 || tipoProducto > 3) {
                        System.out.println("Tipo invalido");
                        break;
                    }

                    System.out.println("Codigo: ");
                    String codigo = sc.nextLine();

                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.println("Descripcion: ");
                    String descripcion = sc.nextLine();

                    System.out.println("Precio base: ");
                    double precioBase = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Tiempo de preparacion (minutos): ");
                    int tiempoPreparacion = sc.nextInt();
                    sc.nextLine();

                    Producto producto = null;

                    if (tipoProducto == 1) {
                        System.out.println("Peso (gramos): ");
                        double pesoPlato = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Tipo de preparacion: 1.Asado 2.Frito 3.Horneado 4.A la plancha");
                        int opcionPreparacion = sc.nextInt();
                        sc.nextLine();

                        TipoPreparacion tipoPreparacion = null;
                        if (opcionPreparacion == 1) {
                            tipoPreparacion = TipoPreparacion.ASADO;
                        } else if (opcionPreparacion == 2) {
                            tipoPreparacion = TipoPreparacion.FRITO;
                        } else if (opcionPreparacion == 3) {
                            tipoPreparacion = TipoPreparacion.HORNEADO;
                        } else if (opcionPreparacion == 4) {
                            tipoPreparacion = TipoPreparacion.A_LA_PLANCHA;
                        }
                        if (tipoPreparacion == null) {
                            System.out.println("Tipo de preparacion invalido");
                            break;
                        }

                        System.out.println("Coccion aprobada? 1.Si 2.No");
                        int opcionCoccion = sc.nextInt();
                        sc.nextLine();

                        boolean coccionAprobada = false;
                        if (opcionCoccion == 1) {
                            coccionAprobada = true;
                        }

                        producto = new PlatoFuerte(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                                pesoPlato, tipoPreparacion, coccionAprobada);
                    } else if (tipoProducto == 2) {
                        System.out.println("Volumen (ml): ");
                        int volumenMl = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Temperatura de servicio (C): ");
                        double temperaturaServicio = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Es fria? 1.Si 2.No (caliente)");
                        int opcionFria = sc.nextInt();
                        sc.nextLine();

                        boolean esFria = false;
                        if (opcionFria == 1) {
                            esFria = true;
                        }

                        System.out.println("Requiere preparacion especial? 1.Si 2.No");
                        int opcionEspecial = sc.nextInt();
                        sc.nextLine();

                        boolean requierePreparacionEspecial = false;
                        if (opcionEspecial == 1) {
                            requierePreparacionEspecial = true;
                        }

                        producto = new Bebida(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                                volumenMl, temperaturaServicio, esFria, requierePreparacionEspecial);
                    } else {
                        System.out.println("Peso (gramos): ");
                        double pesoPostre = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Temperatura de almacenamiento (C): ");
                        double temperaturaAlmacenamiento = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Requiere refrigeracion? 1.Si 2.No");
                        int opcionRefrigeracion = sc.nextInt();
                        sc.nextLine();

                        boolean requiereRefrigeracion = false;
                        if (opcionRefrigeracion == 1) {
                            requiereRefrigeracion = true;
                        }

                        producto = new Postre(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                                pesoPostre, temperaturaAlmacenamiento, requiereRefrigeracion);
                    }

                    if (restaurante.registrarProducto(producto)) {
                        System.out.println("El producto se agrego exitosamente");
                    } else {
                        System.out.println("El producto ya existe");
                    }

                    break;
                }
                case 2: {
                    System.out.println("---Agregar Cliente---");

                    System.out.println("Documento: ");
                    String documento = sc.nextLine();

                    System.out.println("Nombre completo: ");
                    String nombreCompleto = sc.nextLine();

                    System.out.println("Telefono: ");
                    String telefono = sc.nextLine();

                    System.out.println("Correo: ");
                    String correo = sc.nextLine();

                    System.out.println("Direccion de residencia: ");
                    String direccionResidencia = sc.nextLine();

                    Cliente cliente = new Cliente(documento, nombreCompleto, telefono, correo, direccionResidencia);
                    if (restaurante.registrarCliente(cliente)) {
                        System.out.println("El cliente se agrego exitosamente");
                    } else {
                        System.out.println("El cliente ya existe");
                    }

                    break;
                }
                case 3: {
                    System.out.println("---Productos disponibles---");

                    List<Producto> disponibles = restaurante.consultarProductosDisponibles();

                    if (disponibles.isEmpty()) {
                        System.out.println("No hay productos disponibles");
                    } else {
                        for (Producto producto : disponibles) {
                            System.out.println(producto);
                            System.out.println("-----------------------------");
                        }
                    }

                    break;
                }
                case 4: {
                    System.out.println("---Productos que no cumplen calidad---");

                    List<Producto> noCumplen = restaurante.consultarProductosNoCumplenCalidad();

                    if (noCumplen.isEmpty()) {
                        System.out.println("Todos los productos cumplen las condiciones de calidad");
                    } else {
                        for (Producto producto : noCumplen) {
                            System.out.println(producto);
                            System.out.println("-----------------------------");
                        }
                    }

                    break;
                }
                case 5: {
                    System.out.println("---Registrar Pedido---");

                    System.out.println("Documento del cliente:");
                    String documentoCliente = sc.nextLine();

                    Cliente clientePedido = restaurante.buscarCliente(documentoCliente);

                    if (clientePedido == null) {
                        System.out.println("El cliente no existe");
                        break;
                    }

                    System.out.println("Codigo del pedido:");
                    String codigoPedido = sc.nextLine();

                    if (restaurante.buscarPedido(codigoPedido) != null) {
                        System.out.println("El codigo del pedido ya existe");
                        break;
                    }

                    System.out.println("Modalidad de entrega: 1.Consumo en restaurante 2.Domicilio");
                    int opcionModalidad = sc.nextInt();
                    sc.nextLine();

                    ModalidadEntrega modalidad = null;
                    if (opcionModalidad == 1) {
                        modalidad = ModalidadEntrega.CONSUMO_RESTAURANTE;
                    } else if (opcionModalidad == 2) {
                        modalidad = ModalidadEntrega.DOMICILIO;
                    }
                    if (modalidad == null) {
                        System.out.println("Modalidad invalida");
                        break;
                    }

                    Pedido pedido = new Pedido(codigoPedido, LocalDate.now(), clientePedido, modalidad);

                    int agregarOtro;
                    do {
                        System.out.println("Codigo del producto:");
                        String codigoProducto = sc.nextLine();

                        Producto productoPedido = restaurante.buscarProducto(codigoProducto);

                        if (productoPedido == null) {
                            System.out.println("El producto no existe en el menu");
                        } else {
                            System.out.println("Cantidad:");
                            int cantidadProducto = sc.nextInt();
                            sc.nextLine();

                            if (cantidadProducto <= 0) {
                                System.out.println("Cantidad invalida");
                            } else {
                                pedido.agregarDetalle(productoPedido, cantidadProducto);
                                System.out.println("Producto agregado al pedido");
                            }
                        }

                        System.out.println("Agregar otro producto? 1.Si 2.No");
                        agregarOtro = sc.nextInt();
                        sc.nextLine();
                    } while (agregarOtro == 1);

                    if (pedido.getDetalles().isEmpty()) {
                        System.out.println("No se agregaron productos, el pedido se cancelo");
                        break;
                    }

                    if (modalidad == ModalidadEntrega.DOMICILIO) {
                        System.out.println("Empresa de domicilio: 1.RapidoExpress 2.EntregaSegura");
                        int opcionEmpresa = sc.nextInt();
                        sc.nextLine();

                        EmpresaDomicilio empresa = null;
                        if (opcionEmpresa == 1) {
                            empresa = rapidoExpress;
                        } else if (opcionEmpresa == 2) {
                            empresa = entregaSegura;
                        }
                        if (empresa == null) {
                            System.out.println("Empresa invalida, el pedido se cancelo");
                            break;
                        }

                        System.out.println("Distancia del recorrido (km):");
                        double distancia = sc.nextDouble();
                        sc.nextLine();

                        if (pedido.asignarDomicilio(empresa, distancia)) {
                            System.out.println("La empresa acepto el servicio. Costo del domicilio: " + pedido.getCostoDomicilio());
                        } else {
                            System.out.println("La empresa no acepto el servicio por la distancia, el pedido se cancelo. Intenta con otra empresa");
                            break;
                        }
                    }

                    System.out.println("---Resumen del pedido---");
                    for (DetallePedido detalle : pedido.getDetalles()) {
                        System.out.println(detalle);
                        System.out.println("-----------------------------");
                    }
                    System.out.println("Total del pedido: " + pedido.calcularTotal());

                    if (restaurante.registrarPedido(documentoCliente, pedido)) {
                        System.out.println("El pedido se registro exitosamente, esta pendiente de confirmar");
                    } else {
                        System.out.println("No se pudo registrar el pedido");
                    }

                    break;
                }
                case 6: {
                    System.out.println("---Confirmar Pedido---");

                    System.out.println("Codigo del pedido:");
                    String codigoPedido = sc.nextLine();

                    if (restaurante.buscarPedido(codigoPedido) == null) {
                        System.out.println("El pedido no existe");
                        break;
                    }

                    if (restaurante.confirmarPedido(codigoPedido)) {
                        System.out.println("El pedido se confirmo exitosamente");
                    } else {
                        System.out.println("No se pudo confirmar. Verifica que el pedido este pendiente, que los productos cumplan la calidad y que el domicilio este aceptado");
                    }

                    break;
                }
                case 7: {
                    System.out.println("---Entregar Pedido---");

                    System.out.println("Codigo del pedido:");
                    String codigoPedido = sc.nextLine();

                    if (restaurante.buscarPedido(codigoPedido) == null) {
                        System.out.println("El pedido no existe");
                        break;
                    }

                    if (restaurante.entregarPedido(codigoPedido)) {
                        System.out.println("El pedido se marco como entregado");
                    } else {
                        System.out.println("No se pudo entregar. Solo se entregan pedidos confirmados");
                    }

                    break;
                }
                case 8: {
                    System.out.println("---Cancelar Pedido---");

                    System.out.println("Codigo del pedido:");
                    String codigoPedido = sc.nextLine();

                    if (restaurante.buscarPedido(codigoPedido) == null) {
                        System.out.println("El pedido no existe");
                        break;
                    }

                    if (restaurante.cancelarPedido(codigoPedido)) {
                        System.out.println("El pedido se cancelo");
                    } else {
                        System.out.println("No se pudo cancelar. El pedido ya fue entregado o cancelado");
                    }

                    break;
                }
                case 9: {
                    System.out.println("---Pedidos de un cliente---");

                    System.out.println("Documento del cliente:");
                    String documentoCliente = sc.nextLine();

                    Cliente clienteConsulta = restaurante.buscarCliente(documentoCliente);

                    if (clienteConsulta == null) {
                        System.out.println("El cliente no existe");
                        break;
                    }

                    List<Pedido> pedidosCliente = restaurante.consultarPedidosCliente(documentoCliente);

                    if (pedidosCliente.isEmpty()) {
                        System.out.println("El cliente no tiene pedidos");
                    } else {
                        for (Pedido pedido : pedidosCliente) {
                            System.out.println(pedido);
                            for (DetallePedido detalle : pedido.getDetalles()) {
                                System.out.println("  - " + detalle.getProducto().getNombre()
                                        + " x" + detalle.getCantidad()
                                        + " = " + detalle.calcularSubtotal());
                            }
                            System.out.println("-----------------------------");
                        }
                    }

                    break;
                }
                case 10: {
                    System.out.println("---Ingresos del restaurante---");

                    System.out.println("Ingresos (sin domicilios): " + restaurante.calcularIngresosRestaurante());

                    break;
                }
                case 11: {
                    System.out.println("---Ingresos por domicilios---");

                    System.out.println("Valor cobrado por domicilios: " + restaurante.calcularIngresosDomicilios());

                    break;
                }
                case 12: {
                    System.out.println("---Producto mas vendido---");

                    Producto masVendido = restaurante.obtenerProductoMasVendido();

                    if (masVendido == null) {
                        System.out.println("Aun no hay ventas registradas");
                    } else {
                        System.out.println(masVendido);
                    }

                    break;
                }
                case 13: {
                    System.out.println("---Categoria con mayores ingresos---");

                    String categoria = restaurante.obtenerCategoriaMayoresIngresos();

                    if (categoria == null) {
                        System.out.println("Aun no hay ventas registradas");
                    } else {
                        System.out.println("Categoria: " + categoria);
                    }

                    break;
                }
                case 0:
                    System.out.println("Saliendo...");

                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 0);
    }
}

