import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RapidoExpressTest {

    @Test
    void calcularCostoSumaTarifaBaseMasKilometros() {
        RapidoExpress empresa = new RapidoExpress(5000, 1500, 12);

        assertEquals(11000, empresa.calcularCosto(4), 0.001);
    }

    @Test
    void aceptaUnaDistanciaDentroDelLimite() {
        RapidoExpress empresa = new RapidoExpress(5000, 1500, 12);

        assertTrue(empresa.solicitarServicio(10));
    }

    @Test
    void rechazaUnaDistanciaMayorAlLimite() {
        RapidoExpress empresa = new RapidoExpress(5000, 1500, 12);

        assertFalse(empresa.solicitarServicio(15));
    }

    @Test
    void aceptaYCobraLaDistanciaExactaDelLimite() {
        RapidoExpress empresa = new RapidoExpress(5000, 1500, 12);

        assertTrue(empresa.solicitarServicio(12));
        assertEquals(23000, empresa.calcularCosto(12), 0.001);
    }

    @Test
    void rechazaUnaDistanciaCeroONegativa() {
        RapidoExpress empresa = new RapidoExpress(5000, 1500, 12);

        assertFalse(empresa.solicitarServicio(0));
        assertFalse(empresa.solicitarServicio(-2));
    }
}
