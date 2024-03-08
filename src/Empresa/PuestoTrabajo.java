package Empresa;

public abstract class PuestoTrabajo {
    // Atributos
    protected String dni;
    protected String nombre;
    protected String resume;
    protected double sueldoBruto;

    // Constructores
    public PuestoTrabajo(String pDni, String pNombre, String pResume, double pSueldoBruto) {
        this.dni = pDni;
        this.nombre = pNombre;
        this.resume = pResume;
        setSueldoBruto(pSueldoBruto);
    }

    // Getters & Setters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResume() {
        return resume;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        if (sueldoBruto >= 0) {
            this.sueldoBruto = sueldoBruto;
        } else {
            System.out.println("Sueldo no valido se le asignara por defecto -1");
            this.sueldoBruto = -1;
        }
    }

    // toString
    public String toString() {
        return "---Empleado---" + "\nDNI: " + getDni() + "\nNombre: " + getNombre() + "\nDescripción" + getResume() + "\nSueldo bruto: " + getSueldoBruto();
    }

    // Metodos
    public void subidaSalarioIPC(float pIPC) {
        setSueldoBruto(getSueldoBruto() * (1 + pIPC / 100));
    }

} // class end
