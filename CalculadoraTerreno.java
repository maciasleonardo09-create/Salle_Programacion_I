import java.util.ArrayList;
import java.util.List;

public class CalculadoraTerreno {
    private final List<Vertice> vertices = new ArrayList<>();
    private double precioMetroCuadrado;
    private double precioMetroLinealReja;

    private double area;
    private double perimetro;
    private double costoTotalPropiedad;
    private double costoTotalEnrejado;
    private double costoGlobalProyecto;
    private boolean calculado;

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void agregarVertice(double x, double y) {
        vertices.add(new Vertice(x, y));
        calculado = false;
    }

    public void limpiarVertices() {
        vertices.clear();
        calculado = false;
    }

    public void setCostos(double precioMetroCuadrado, double precioMetroLinealReja) {
        if (precioMetroCuadrado <= 0 || precioMetroLinealReja <= 0) {
            throw new IllegalArgumentException("Los precios unitarios deben ser mayores que cero.");
        }
        this.precioMetroCuadrado = precioMetroCuadrado;
        this.precioMetroLinealReja = precioMetroLinealReja;
        calculado = false;
    }

    public double getPrecioMetroCuadrado() {
        return precioMetroCuadrado;
    }

    public double getPrecioMetroLinealReja() {
        return precioMetroLinealReja;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public void calcular() {
        if (vertices.size() < 3) {
            throw new IllegalStateException("Se requieren al menos 3 vértices para formar un polígono válido.");
        }
        if (precioMetroCuadrado <= 0 || precioMetroLinealReja <= 0) {
            throw new IllegalStateException("Primero debes ingresar precios unitarios válidos (mayores a 0).");
        }

        double sumaArea = 0.0;
        double sumaPerimetro = 0.0;
        int n = vertices.size();

        for (int i = 0; i < n; i++) {
            Vertice actual = vertices.get(i);
            Vertice siguiente = vertices.get((i + 1) % n);

            // Fórmula del área de Gauss (Shoelace)
            sumaArea += (actual.getX() * siguiente.getY()) - (siguiente.getX() * actual.getY());

            // Distancia euclidiana entre vértices consecutivos
            double dx = siguiente.getX() - actual.getX();
            double dy = siguiente.getY() - actual.getY();
            sumaPerimetro += Math.hypot(dx, dy);
        }

        this.area = Math.abs(sumaArea) / 2.0;
        this.perimetro = sumaPerimetro;
        this.costoTotalPropiedad = this.area * this.precioMetroCuadrado;
        this.costoTotalEnrejado = this.perimetro * this.precioMetroLinealReja;
        this.costoGlobalProyecto = this.costoTotalPropiedad + this.costoTotalEnrejado;
        this.calculado = true;
    }

    public double getArea() {
        return area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public double getCostoTotalPropiedad() {
        return costoTotalPropiedad;
    }

    public double getCostoTotalEnrejado() {
        return costoTotalEnrejado;
    }

    public double getCostoGlobalProyecto() {
        return costoGlobalProyecto;
    }
}