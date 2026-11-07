public class Bebida extends Producto{
    private int volumenMI;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean requierePreparacionEspecial;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, int volumenMI, double temperaturaServicio, boolean esFria, boolean requierePreparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.volumenMI = volumenMI;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.requierePreparacionEspecial = requierePreparacionEspecial;
    }

    public int getVolumenMI() {
        return volumenMI;
    }

    public void setVolumenMI(int volumenMI) {
        this.volumenMI = volumenMI;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public boolean isEsFria() {
        return esFria;
    }

    public void setEsFria(boolean esFria) {
        this.esFria = esFria;
    }

    public boolean isRequierePreparacionEspecial() {
        return requierePreparacionEspecial;
    }

    public void setRequierePreparacionEspecial(boolean requierePreparacionEspecial) {
        this.requierePreparacionEspecial = requierePreparacionEspecial;
    }

    @Override
    public double calcularPrecioVenta() {
        return 0;
    }

    @Override
    public boolean cumpleCondicionesCalidad() {
        return false;
    }
}
