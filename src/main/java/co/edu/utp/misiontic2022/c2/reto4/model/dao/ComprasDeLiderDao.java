package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class ComprasDeLiderDao {
    public List<ComprasDeLiderVo> Listar() throws SQLException{
        ArrayList<ComprasDeLiderVo> respuesta = new ArrayList<ComprasDeLiderVo>();
        Connection con= JDBCUtilities.getConnection();
        Statement stm= null;
        ResultSet rs= null;
        String consulta= "SELECT L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO AS LIDER, "
                            + "SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) AS VALOR "
                            + "FROM LIDER L "
                            + "JOIN PROYECTO P ON (P.ID_LIDER = L.ID_LIDER) "
                            + "JOIN COMPRA C ON (P.ID_PROYECTO = C.ID_PROYECTO) "
                            + "JOIN MATERIALCONSTRUCCION MC ON (C.ID_MATERIALCONSTRUCCION = MC.ID_MATERIALCONSTRUCCION) "
                            + "GROUP BY L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO "
                            + "ORDER BY VALOR DESC "
                            + "LIMIT 10 ";
        try {
                stm = con.createStatement();
                rs= stm.executeQuery(consulta);
                while (rs.next()) {
                        ComprasDeLiderVo object = new ComprasDeLiderVo();
                        object.setLider(rs.getString("lider"));
                        object.setValor(rs.getDouble("valor"));
                        respuesta.add(object);
                    }
            }
        finally{
                if (rs != null){
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null){
                    con.close();
                }      
            }
        return respuesta;
        

    }
}
