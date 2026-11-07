public class EntregaSegura implements EmpresaDomicilio {

    private double tarifaBase;
    private double tarifaPorKm;
    private double recargoDistancia;
    private double distanciaMaximaKm;

    public EntregaSegura(double tarifaBase, double tarifaPorKm, double recargoDistancia, double distanciaMaximaKm) {
        this.tarifaBase = tarifaBase;
        this.tarifaPorKm = tarifaPorKm;
        this.recargoDistancia = recargoDistancia;
        this.distanciaMaximaKm = distanciaMaximaKm;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaPorKm() {
        return tarifaPorKm;
    }

    public void setTarifaPorKm(double tarifaPorKm) {
        this.tarifaPorKm = tarifaPorKm;
    }

    public double getRecargoDistancia() {
        return recargoDistancia;
    }

    public void setRecargoDistancia(double recargoDistancia) {
        this.recargoDistancia = recargoDistancia;
    }

    public double getDistanciaMaximaKm() {
        return distanciaMaximaKm;
    }

    public void setDistanciaMaximaKm(double distanciaMaximaKm) {
        this.distanciaMaximaKm = distanciaMaximaKm;
    }

    // acepta recorridos mayores que cero y hasta la distancia maxima
    @Override
    public boolean solicitarServicio(double distanciaKm) {
        boolean aceptado = false;

        if (distanciaKm > 0 && distanciaKm <= distanciaMaximaKm) {
            aceptado = true;
        }

        return aceptado;
    }

    // tarifa base + valor por kilometro, y recargo si la distancia supera 5 km
    @Override
    public double calcularCosto(double distanciaKm) {
        double costo = tarifaBase + (tarifaPorKm * distanciaKm);

        if (distanciaKm > 5) {
            costo = costo + recargoDistancia;
        }

        return costo;
    }
}
