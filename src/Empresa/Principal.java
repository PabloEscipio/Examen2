package Empresa;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static char tipoPuestoTrabajo() {
        Scanner sc = new Scanner(System.in);
        char rInt;
        boolean exit = false;
        do {
            System.out.println("""
                    -- ¿Que puesto de trabajo deseas? --
                    1 - Administrativo
                    2 - Consultor""");
            rInt = sc.nextLine().charAt(0);
            switch (rInt) {
                case '1':
                case '2':
                    exit = true;
                    break;
                default:
                    System.out.println("Error: Indice no valido");
            }
        } while (!exit);
        return rInt;
    }

    public static void crearPuestoTrabajoIndice(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        Scanner sc = new Scanner(System.in);
        char ptIndice = tipoPuestoTrabajo();
        System.out.println("Introduce DNI");
        Scanner in = new Scanner(System.in);
        String dni = in.nextLine();
        System.out.println("Introduce nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduce descripción");
        String resume = sc.nextLine();
        System.out.println("Introduce sueldo bruto");
        double sueldoBruto = sc.nextDouble();
        sc.nextLine();

        switch (ptIndice) {
            case '1':
                System.out.println("Introduce antigüedad");
                int antiguedad = sc.nextInt();
                crearAdministrativo(listPuestoTrabajo, dni, nombre, resume, sueldoBruto, antiguedad);
                break;
            case '2':
                System.out.println("Introduce categoría profesional");
                String catPro = sc.nextLine();
                crearConsultor(listPuestoTrabajo, dni, nombre, resume, sueldoBruto, catPro);
                break;
        } // switch end
    } // crearPuestoTrabajoIndice end

    public static void crearAdministrativo(ArrayList<PuestoTrabajo> listPuestoTrabajo, String pDNI, String pNombre, String pResume, double pSueldoBruto, int pAntiguedad) {
        listPuestoTrabajo.add(new Administrativo(pDNI, pNombre, pResume, pSueldoBruto, pAntiguedad));
    } // crearAdministrativo end

    public static void crearConsultor(ArrayList<PuestoTrabajo> listPuestoTrabajo, String pDNI, String pNombre, String pResume, double pSueldoBruto, String pCatPro) {
        listPuestoTrabajo.add(new Consultor(pDNI, pNombre, pResume, pSueldoBruto, pCatPro));
    } // crearConsultor end

    public static void crearFicheroMostrarAll(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        FileWriter fwPuestosTrabajo;
        try {
            fwPuestosTrabajo = new FileWriter("puestosTrabajo.txt");
            fwPuestosTrabajo.write(mostrarAll(listPuestoTrabajo));
            fwPuestosTrabajo.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    } // crearFicheroMostrarAll end


    public static String mostrarAll(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        String rString = "";
        for (PuestoTrabajo valor : listPuestoTrabajo) {
            rString += valor.toString() + "\n";
        }
        return rString;
    }

    public static void actualizarSalarioBrutoIPC(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        File frActualizar;
        Scanner srActualizar;
        float ipc = 0;
        try {
            frActualizar = new File("actualizar.txt");
            srActualizar = new Scanner(frActualizar);
            ipc = srActualizar.nextFloat();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        for (PuestoTrabajo valor : listPuestoTrabajo) {
            valor.subidaSalarioIPC(ipc);
        }
    }

    public static void crearFicheroAdministrativos(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        FileWriter fwAdministrativos;
        String listAdministrativos = "";

        for (PuestoTrabajo valor : listPuestoTrabajo) {
            if (valor.getClass().getSimpleName().equalsIgnoreCase("Administrativo")) {
                listAdministrativos += valor.toString() + "\n";
            }
        }
        try {
            fwAdministrativos = new FileWriter("administrativos.txt");
            fwAdministrativos.write(listAdministrativos);
            fwAdministrativos.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void crearFicheroConsultores(ArrayList<PuestoTrabajo> listPuestoTrabajo) {
        FileWriter fwAdministrativos;
        String listAdministrativos = "";
        File frSueldo;
        Scanner fsSueldo;
        double sueldo;

        try {
            frSueldo = new File("sueldo.txt");
            fsSueldo = new Scanner(frSueldo);
            sueldo = fsSueldo.nextDouble();

            for (PuestoTrabajo valor : listPuestoTrabajo) {
                if (valor.getClass().getSimpleName().equalsIgnoreCase("Consultor") && valor.sueldoBruto > sueldo) {
                    listAdministrativos += valor.toString() + "\n";
                }
            }

            fwAdministrativos = new FileWriter("consultores.txt");
            fwAdministrativos.write(listAdministrativos);
            fwAdministrativos.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Utils
        Scanner sc = new Scanner(System.in);

        // Variables
        char uIndice;

        // Arrays
        ArrayList<PuestoTrabajo> listPuestoTrabajo = new ArrayList<>();

        listPuestoTrabajo.add(new Administrativo("01", "Paco", "Auxiliar", 1200, 1));
        listPuestoTrabajo.add(new Administrativo("02", "Sonia", "Jefe de sección", 2800, 8));

        listPuestoTrabajo.add(new Consultor("03", "Juan", "Jefe de sección", 2600, "A"));
        listPuestoTrabajo.add(new Consultor("04", "Maria", "Auxiliar", 1100, "C"));

        // Programa
        do {
            System.out.println("***** Indice *****");
            uIndice = sc.nextLine().charAt(0);
            switch (uIndice) {
                case '0':
                    System.out.println("Cerrando el programa...");
                    break;
                case '1':
                    crearPuestoTrabajoIndice(listPuestoTrabajo);
                    break;
                case '2':
                    crearFicheroMostrarAll(listPuestoTrabajo);
                    break;
                case '3':
                    actualizarSalarioBrutoIPC(listPuestoTrabajo);
                    break;
                case '4':
                    crearFicheroAdministrativos(listPuestoTrabajo);
                    break;
                case '5':
                    crearFicheroConsultores(listPuestoTrabajo);
                    break;
                default:
                    System.out.println("Indice introducido no valido");
            } // switch end
        } while (uIndice != '0');

        sc.close();
    } // main end
} // class end
