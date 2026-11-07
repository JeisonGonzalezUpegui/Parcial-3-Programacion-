import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PedidoTest {

    private Cliente cliente;
    private PlatoFuerte plato;
    private Bebida bebida;

    @BeforeEach
    void preparar() {
        cliente = new Cliente("100", "Ana Perez", "3001234567", "ana@correo.com", "Calle 1");
        plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                400, TipoPreparacion.ASADO, true);
        bebida = new Bebida("B1", "Limonada", "Limonada natural", 5000, 5,
                500, 5, true, false);
    }

    @Test
    void calcularValorProductosSumaVariosProductosYCantidades() {
        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 2);
        pedido.agregarDetalle(bebida, 1);

        assertEquals(29500, pedido.calcularValorProductos(), 0.001);
    }

    @Test
    void calcularTotalIncluyeElCostoDelDomicilio() {
        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(plato, 1);

        assertTrue(pedido.asignarDomicilio(new RapidoExpress(5000, 1500, 12), 4));
        assertEquals(11000, pedido.getCostoDomicilio(), 0.001);
        assertEquals(23000, pedido.calcularTotal(), 0.001);
    }

    @Test
    void asignarDomicilioRechazadoNoDejaCostoNiAceptacion() {
        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(plato, 1);

        assertFalse(pedido.asignarDomicilio(new RapidoExpress(5000, 1500, 12), 15));
        assertFalse(pedido.isDomicilioAceptado());
        assertEquals(0, pedido.getCostoDomicilio(), 0.001);
    }

    @Test
    void unPedidoConfirmadoSePuedeEntregar() {
        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 1);

        assertFalse(pedido.entregar());
        assertTrue(pedido.confirmar());
        assertFalse(pedido.confirmar());
        assertTrue(pedido.entregar());
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado());
    }

    @Test
    void unPedidoCanceladoNoSePuedeEntregar() {
        Pedido pedido = new Pedido("PED1", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 1);
        pedido.confirmar();

        assertTrue(pedido.cancelar());
        assertFalse(pedido.entregar());
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
    }
}
