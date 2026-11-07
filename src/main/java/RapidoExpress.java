public class RapidoExpress implements EmpresaDomicilio {
    private double tarifaBase;
    private double tarifaPorKm;
    private double distanciaMaximaKm;

    public RapidoExpress(double tarifaBase, double tarifaPorKm, double distanciaMaximaKm) {
        this.tarifaBase = tarifaBase;
        this.tarifaPorKm = tarifaPorKm;
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

    public double getDistanciaMaximaKm() {
        return distanciaMaximaKm;
    }

    public void setDistanciaMaximaKm(double distanciaMaximaKm) {
        this.distanciaMaximaKm = distanciaMaximaKm;
    }

    @Override
    public boolean solicitarServicio(double distanciaKm) {
        return false;
    }

    @Override
    public double calcularCosto(double distanciaKm) {
        return 0;
    }
}
