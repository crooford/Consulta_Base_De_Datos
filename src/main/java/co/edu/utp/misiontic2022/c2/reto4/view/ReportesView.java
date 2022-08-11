package co.edu.utp.misiontic2022.c2.reto4.view;

import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

public class ReportesView {

    private ReportesController controller;
    public ReportesView(){
        controller = new ReportesController();
    }
    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
        respuesta += caracter;
        }
        return respuesta;
    }
    public void proyectosFinanciadosPorBanco(String banco) {
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO "
        + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
        System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s",
        "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
        System.out.println(repitaCaracter('-', 105));
        try{
            List<ProyectoBancoVo> project = controller.Listatotalproyectobancos(banco);
            
            for (ProyectoBancoVo vo:project) {
                System.out.println(vo);
            }
        }catch(Exception ex){
            System.err.println("ERROR: "+ex);
        }
        }
    }
    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
        + repitaCaracter('=', 1));
        if (limiteInferior != null) {
        System.out.println(String.format("%3s %13s", "ID","VALOR"));
        System.out.println(repitaCaracter('-', 29));
        try {
            List<DeudasPorProyectoVo> project2 = controller.Listatotaldeudasproyectos(limiteInferior);
            
            for (DeudasPorProyectoVo vo : project2) {
                System.out.println(vo);
            }
        }catch(Exception ex){
            System.err.println("ERROR: "+ex);
        }
        }
    }
    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
         + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %13s", "LIDER","VALOR"));
        System.out.println(repitaCaracter('-', 41));
        try {
            
            List<ComprasDeLiderVo> project3 = controller.Listatotalcompraslider();
            
            for (ComprasDeLiderVo vo : project3) {
                System.out.println(vo);
            }
        }catch(Exception ex){
            System.err.println("ERROR: "+ex);
        }
    }

}
