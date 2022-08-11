package co.edu.utp.misiontic2022.c2.reto4.controller;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.*;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.*;

public class ReportesController {
    private ProyectoBancoDao proyectoBancoDao;
    private DeudasPorProyectoDao deudasPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;
    
    public ReportesController() {
        proyectoBancoDao = new ProyectoBancoDao();
        deudasPorProyectoDao = new DeudasPorProyectoDao();
        comprasDeLiderDao = new ComprasDeLiderDao();
    }

    public List<ProyectoBancoVo> Listatotalproyectobancos(String banco) throws SQLException{
        return proyectoBancoDao.Listar(banco);
    }

    public List<DeudasPorProyectoVo> Listatotaldeudasproyectos(Double limite) throws SQLException{ 
        return deudasPorProyectoDao.Listar(limite);
    }

    public List<ComprasDeLiderVo> Listatotalcompraslider() throws SQLException{
        return comprasDeLiderDao.Listar();
    } 
    
}
