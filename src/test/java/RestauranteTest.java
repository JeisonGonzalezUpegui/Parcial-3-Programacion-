import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestauranteTest {

    private Restaurante restaurante;
    private Cliente cliente;
    private PlatoFuerte plato;
    private Bebida bebida;

    @BeforeEach
    void preparar() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456", "Armenia, Quindio");
        cliente = new Cliente("100", "Ana Perez", "3001234567", "ana@correo.com", "Calle 1");
        plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                400, TipoPreparacion.ASADO, true);
        bebida = new Bebida("B1", "Limonada", "Limonada natural", 5000, 5,
                500, 5, true, false);
    }

    @Test
    void noRegistraUnProductoConCodigoDuplicado() {
        PlatoFuerte repetido = new PlatoFuerte("P1", "Otro plato", "Otra descripcion", 9000, 20,
                300, TipoPreparacion.FRITO, true);

        assertTrue(restaurante.registrarProducto(plato));
        assertFalse(restaurante.registrarProducto(repetido));
    }

    @Test
    void noRegistraUnClienteConDocumentoRepetido() {
        Cliente repetido = new Cliente("100", "Luis Gomez", "3007654321", "luis@correo.com", "Calle 2");

        assertTrue(restaurante.registrarCliente(cliente));
        assertFalse(restaurante.registrarCliente(repetido));
    }

    @Test
    void confirmaUnPedidoValidoYLoGuardaEnElHistorialDelCliente() {
        restaurante.registrarProducto(plato);
        restaurante.registrarCliente(cliente);

        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 2);

        assertTrue(restaurante.registrarPedido("100", pedido));
        assertTrue(restaurante.confirmarPedido("PED1"));
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
        assertEquals(1, cliente.consultarHistorialPedidos().size());
        assertFalse(restaurante.confirmarPedido("PED1"));
    }

    @Test
    void noConfirmaUnPedidoConUnProductoQueNoCumpleCalidad() {
        PlatoFuerte pesoBajo = new PlatoFuerte("P2", "Plato pequeno", "Poco peso", 10000, 30,
                100, TipoPreparacion.ASADO, true);
        restaurante.registrarProducto(pesoBajo);
        restaurante.registrarCliente(cliente);

        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(pesoBajo, 1);
        restaurante.registrarPedido("100", pedido);

        assertFalse(restaurante.confirmarPedido("PED1"));
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    void losIngresosNoCuentanPedidosCanceladosNiDomicilios() {
        restaurante.registrarProducto(plato);
        restaurante.registrarProducto(bebida);
        restaurante.registrarCliente(cliente);

        Pedido pedidoDomicilio = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.DOMICILIO);
        pedidoDomicilio.agregarDetalle(plato, 1);
        pedidoDomicilio.asignarDomicilio(new RapidoExpress(5000, 1500, 12), 4);
        restaurante.registrarPedido("100", pedidoDomicilio);
        restaurante.confirmarPedido("PED1");

        Pedido pedidoCancelado = new Pedido("PED2", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedidoCancelado.agregarDetalle(bebida, 2);
        restaurante.registrarPedido("100", pedidoCancelado);
        restaurante.confirmarPedido("PED2");
        restaurante.cancelarPedido("PED2");

        assertEquals(12000, restaurante.calcularIngresosRestaurante(), 0.001);
        assertEquals(11000, restaurante.calcularIngresosDomicilios(), 0.001);
    }
}
