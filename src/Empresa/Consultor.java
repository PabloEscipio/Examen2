package Empresa;

public class Consultor extends PuestoTrabajo {
    // Atributos
    private String catPro;

    // Constructores
    public Consultor(String pDni, String pNombre, String pResume, double pSueldoBruto, String pCatPro) {
        super(pDni, pNombre, pResume, pSueldoBruto);
        setCatPro(pCatPro);
    }

    // Getters & Setters
    public String getCatPro() {
        return catPro;
    }

    public void setCatPro(String catPro) {
        switch (catPro.toLowerCase()) {
            case "a":
            case "b":
            case "c":
                this.catPro = catPro.toUpperCase();
                break;
            default:
                System.out.println("Categoría profesional no valida se le asignara \"N\" por defecto");
                this.catPro = "N";
        }
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "\nCategoría profesional: " + getCatPro();
    }

    // Metodos
    public float impuestos(String pCatPro) {
        return switch (pCatPro.toLowerCase()) {
            case "a" -> 10;
            case "b" -> 15;
            case "c" -> 20;
            default -> 100;
        };
    }

    public Double sueldoNeto() {
        return getSueldoBruto() * (1 - impuestos(getCatPro()) / 100);
    }
}
