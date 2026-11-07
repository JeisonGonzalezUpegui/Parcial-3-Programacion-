import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostreTest {

    @Test
    void calcularPrecioVentaAplicaIncrementoDel15() {
        Postre postre = new Postre("D1", "Brownie", "Brownie con helado", 8000, 10,
                200, 20, false);

        assertEquals(9200, postre.calcularPrecioVenta(), 0.001);
    }

    @Test
    void calcularPrecioVentaSumaElCincoPorCientoSiRequiereRefrigeracion() {
        Postre postre = new Postre("D1", "Tres leches", "Torta fria", 8000, 10,
                200, 3, true);

        assertEquals(9600, postre.calcularPrecioVenta(), 0.001);
    }

    @Test
    void cumpleCondicionesElPostreRefrigeradoYElQueNoLoEs() {
        Postre refrigerado = new Postre("D1", "Tres leches", "Torta fria", 8000, 10,
                200, 3, true);
        Postre sinRefrigerar = new Postre("D2", "Brownie", "Brownie con helado", 8000, 10,
                200, 20, false);

        assertTrue(refrigerado.cumpleCondicionesCalidad());
        assertTrue(sinRefrigerar.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoElPesoEstaFueraDelRango() {
        Postre postre = new Postre("D1", "Brownie", "Brownie con helado", 8000, 10,
                600, 20, false);

        assertFalse(postre.cumpleCondicionesCalidad());
    }

    @Test
    void noCumpleCuandoLaTemperaturaNoCorrespondeALaRefrigeracion() {
        Postre refrigerado = new Postre("D1", "Tres leches", "Torta fria", 8000, 10,
                200, 10, true);
        Postre sinRefrigerar = new Postre("D2", "Brownie", "Brownie con helado", 8000, 10,
                200, 5, false);

        assertFalse(refrigerado.cumpleCondicionesCalidad());
        assertFalse(sinRefrigerar.cumpleCondicionesCalidad());
    }
}
