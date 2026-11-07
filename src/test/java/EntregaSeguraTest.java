import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntregaSeguraTest {

    @Test
    void calcularCostoSinRecargoHastaCincoKilometros() {
        EntregaSegura empresa = new EntregaSegura(7000, 1000, 3000, 20);

        assertEquals(10000, empresa.calcularCosto(3), 0.001);
    }

    @Test
    void calcularCostoConRecargoSiSuperaCincoKilometros() {
        EntregaSegura empresa = new EntregaSegura(7000, 1000, 3000, 20);

        assertEquals(18000, empresa.calcularCosto(8), 0.001);
    }

    @Test
    void aceptaLaDistanciaExactaDelLimite() {
        EntregaSegura empresa = new EntregaSegura(7000, 1000, 3000, 20);

        assertTrue(empresa.solicitarServicio(20));
    }

    @Test
    void rechazaUnaDistanciaMayorAlLimite() {
        EntregaSegura empresa = new EntregaSegura(7000, 1000, 3000, 20);

        assertFalse(empresa.solicitarServicio(25));
    }

    @Test
    void noAplicaRecargoEnExactamenteCincoKilometros() {
        EntregaSegura empresa = new EntregaSegura(7000, 1000, 3000, 20);

        assertEquals(12000, empresa.calcularCosto(5), 0.001);
    }
}
