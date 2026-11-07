public class Postre extends Producto {

    private double pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                  double pesoGramos, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public String toString() {
        return "Categoria: Postre\n" + super.toString()
                + "\nPeso: " + pesoGramos + " g"
                + "\nTemperatura de almacenamiento: " + temperaturaAlmacenamiento + " C"
                + "\nRequiere refrigeracion: " + requiereRefrigeracion;
    }

    // precio base + 15% y 5% adicional del precio base si requiere refrigeracion
    @Override
    public double calcularPrecioVenta() {
        double precio = getPrecioBase() + (getPrecioBase() * 0.15);

        if (requiereRefrigeracion) {
            precio = precio + (getPrecioBase() * 0.05);
        }

        return precio;
    }

    // peso entre 80 y 500 g, refrigerado entre 0 y 6 C, sin refrigerar entre 15 y 30 C
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean cumple = false;
        boolean pesoValido = pesoGramos >= 80 && pesoGramos <= 500;
        boolean temperaturaValida = false;

        if (requiereRefrigeracion) {
            temperaturaValida = temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6;
        } else {
            temperaturaValida = temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30;
        }

        if (pesoValido && temperaturaValida) {
            cumple = true;
        }

        return cumple;
    }
}
