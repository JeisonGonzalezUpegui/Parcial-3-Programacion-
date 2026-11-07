import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatoFuerteTest {

    @Test
    void calcularPrecioVentaAplicaIncrementoDel20() {
        PlatoFuerte plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                400, TipoPreparacion.ASADO, true);

        assertEquals(12000, plato.calcularPrecioVenta(), 0.001);
    }

    @Test
    void cumpleCondicionesConValoresValidos() {
        PlatoFuerte plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                400, TipoPreparacion.ASADO, true);

        assertTrue(plato.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoElPesoEstaFueraDelRango() {
        PlatoFuerte plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                150, TipoPreparacion.ASADO, true);

        assertFalse(plato.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoElTiempoDePreparacionEstaFueraDelRango() {
        PlatoFuerte plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 5,
                400, TipoPreparacion.FRITO, true);

        assertFalse(plato.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoLaCoccionNoEstaAprobada() {
        PlatoFuerte plato = new PlatoFuerte("P1", "Bandeja paisa", "Plato tipico", 10000, 30,
                400, TipoPreparacion.HORNEADO, false);

        assertFalse(plato.cumpleCondicionesCalidad());
    }
}
