package Empresa;

public class Administrativo extends PuestoTrabajo {
    // Atributos
    private int antiguedad;

    // Constructores
    public Administrativo(String pDni, String pNombre, String pResume, double pSueldoBruto, int pAntiguedad) {
        super(pDni, pNombre, pResume, pSueldoBruto);
        setAntiguedad(pAntiguedad);
    }

    // Getters & Setters
    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        if (antiguedad >= 0) {
            this.antiguedad = antiguedad;
        } else {
            System.out.println("Antiguedad no valida, se le asignara por defecto el valor de 0");
            this.antiguedad = 0;
        }
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "\nAntiguedad: " + getAntiguedad();
    }

    // Metodos
    public float impuestos() {
        return 12;
    }

    public double sueldoNeto() {
        return getSueldoBruto() * (1 - impuestos() / 100);
    }
}
