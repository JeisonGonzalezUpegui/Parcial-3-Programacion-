public class PlatoFuerte extends Producto {

    private double pesoGramos;
    private TipoPreparacion tipoPreparacion;
    private boolean coccionAprobada;

    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                       double pesoGramos, TipoPreparacion tipoPreparacion, boolean coccionAprobada) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.tipoPreparacion = tipoPreparacion;
        this.coccionAprobada = coccionAprobada;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public TipoPreparacion getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(TipoPreparacion tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public boolean isCoccionAprobada() {
        return coccionAprobada;
    }

    public void setCoccionAprobada(boolean coccionAprobada) {
        this.coccionAprobada = coccionAprobada;
    }

    @Override
    public String toString() {
        return "Categoria: Plato fuerte\n" + super.toString()
                + "\nPeso: " + pesoGramos + " g"
                + "\nTipo de preparacion: " + tipoPreparacion
                + "\nCoccion aprobada: " + coccionAprobada;
    }

    // precio base + 20%
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() + (getPrecioBase() * 0.20);
    }

    // peso entre 200 y 900 g, preparacion entre 10 y 60 min y coccion aprobada
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean cumple = false;
        boolean pesoValido = pesoGramos >= 200 && pesoGramos <= 900;
        boolean tiempoValido = getTiempoPreparacion() >= 10 && getTiempoPreparacion() <= 60;

        if (pesoValido && tiempoValido && coccionAprobada) {
            cumple = true;
        }

        return cumple;
    }
}
