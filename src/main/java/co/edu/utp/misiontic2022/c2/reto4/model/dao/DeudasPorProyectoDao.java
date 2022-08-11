package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;
public class DeudasPorProyectoDao {
    public List<DeudasPorProyectoVo> Listar(Double limite) throws SQLException{
        ArrayList<DeudasPorProyectoVo> respuesta = new ArrayList<DeudasPorProyectoVo>();
        Connection con = JDBCUtilities.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "SELECT P.ID_PROYECTO AS ID, SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) AS VALOR "
                            + "FROM PROYECTO P "
                            + "JOIN COMPRA C ON (P.ID_PROYECTO = C.ID_PROYECTO) "
                            + "JOIN MATERIALCONSTRUCCION MC ON (C.ID_MATERIALCONSTRUCCION = MC.ID_MATERIALCONSTRUCCION) "
                            + "WHERE C.PAGADO = 'No' "
                            + "GROUP BY P.ID_PROYECTO "
                            + "HAVING SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) > ? "
                            + "ORDER BY VALOR DESC ";
        try {
            ps = con.prepareStatement(consulta);
            ps.setDouble(1, limite);
            rs= ps.executeQuery();
            while (rs.next()) {
                    DeudasPorProyectoVo object = new DeudasPorProyectoVo();
                    object.setId(rs.getInt("id"));
                    object.setValor(rs.getDouble("valor"));
                    respuesta.add(object);
                }
            }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null){
                con.close();
            }      
        }
        return respuesta;

    }
}
