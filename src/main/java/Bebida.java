public class Bebida extends Producto {

    private int volumenMl;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean requierePreparacionEspecial;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                  int volumenMl, double temperaturaServicio, boolean esFria, boolean requierePreparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.volumenMl = volumenMl;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.requierePreparacionEspecial = requierePreparacionEspecial;
    }

    public int getVolumenMl() {
        return volumenMl;
    }

    public void setVolumenMl(int volumenMl) {
        this.volumenMl = volumenMl;
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
    public String toString() {
        return "Categoria: Bebida\n" + super.toString()
                + "\nVolumen: " + volumenMl + " ml"
                + "\nTemperatura de servicio: " + temperaturaServicio + " C"
                + "\nEs fria: " + esFria
                + "\nRequiere preparacion especial: " + requierePreparacionEspecial;
    }

    // precio base + 10% y recargo fijo de 3000 si requiere preparacion especial
    @Override
    public double calcularPrecioVenta() {
        double precio = getPrecioBase() + (getPrecioBase() * 0.10);

        if (requierePreparacionEspecial) {
            precio = precio + 3000;
        }

        return precio;
    }

    // volumen entre 150 y 1500 ml, fria entre 0 y 10 C, caliente entre 55 y 85 C
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean cumple = false;
        boolean volumenValido = volumenMl >= 150 && volumenMl <= 1500;
        boolean temperaturaValida = false;

        if (esFria) {
            temperaturaValida = temperaturaServicio >= 0 && temperaturaServicio <= 10;
        } else {
            temperaturaValida = temperaturaServicio >= 55 && temperaturaServicio <= 85;
        }

        if (volumenValido && temperaturaValida) {
            cumple = true;
        }

        return cumple;
    }
}
