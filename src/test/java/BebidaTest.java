import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BebidaTest {

    @Test
    void calcularPrecioVentaAplicaIncrementoDel10() {
        Bebida bebida = new Bebida("B1", "Limonada", "Limonada natural", 5000, 5,
                500, 5, true, false);

        assertEquals(5500, bebida.calcularPrecioVenta(), 0.001);
    }

    @Test
    void calcularPrecioVentaSumaRecargoDePreparacionEspecial() {
        Bebida bebida = new Bebida("B1", "Limonada de coco", "Preparacion especial", 5000, 8,
                500, 5, true, true);

        assertEquals(8500, bebida.calcularPrecioVenta(), 0.001);
    }

    @Test
    void cumpleCondicionesLaBebidaFriaYLaCaliente() {
        Bebida fria = new Bebida("B1", "Limonada", "Limonada natural", 5000, 5,
                500, 5, true, false);
        Bebida caliente = new Bebida("B2", "Chocolate", "Chocolate caliente", 4000, 7,
                300, 70, false, false);

        assertTrue(fria.cumpleCondicionesCalidad());
        assertTrue(caliente.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoElVolumenEstaFueraDelRango() {
        Bebida bebida = new Bebida("B1", "Limonada", "Limonada natural", 5000, 5,
                100, 5, true, false);

        assertFalse(bebida.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoLaTemperaturaNoCorrespondeAlTipo() {
        Bebida caliente = new Bebida("B1", "Chocolate", "Chocolate caliente", 4000, 7,
                300, 30, false, false);
        Bebida fria = new Bebida("B2", "Limonada", "Limonada natural", 5000, 5,
                500, 20, true, false);

        assertFalse(caliente.cumpleCondicionesCalidad());
        assertFalse(fria.cumpleCondicionesCalidad());
    }
}
