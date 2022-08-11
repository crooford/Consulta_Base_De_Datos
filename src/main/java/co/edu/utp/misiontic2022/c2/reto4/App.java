package co.edu.utp.misiontic2022.c2.reto4;

import co.edu.utp.misiontic2022.c2.reto4.view.ReportesView;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("REQUERIMIENTO 1");
        var reportesView = new ReportesView();
        var banco = "Conavi";
        reportesView.proyectosFinanciadosPorBanco(banco);

        System.out.println("\nREQUERIMIENTO 2");
        var reportesView1 = new ReportesView();
        var limiteInferior = 50_000d;
        reportesView1.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);

        System.out.println("\nREQUERIMIENTO 3");
        var reportesView2 = new ReportesView();
        reportesView2. lideresQueMasGastan();

    }
}
