package co.edu.utp.misiontic2022.c2.reto4.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;




public class ProyectoBancoDao {
    public List<ProyectoBancoVo> Listar(String banco) throws SQLException {
        ArrayList<ProyectoBancoVo> respuesta = new ArrayList<ProyectoBancoVo>();
        Connection con = JDBCUtilities.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta="SELECT P.ID_PROYECTO AS ID, P.CONSTRUCTORA, P.CIUDAD, P.CLASIFICACION,T.ESTRATO, " 
                            + "L.NOMBRE||' '||L.PRIMER_APELLIDO||' '||L.SEGUNDO_APELLIDO AS LIDER "
                            + "FROM PROYECTO P "
                            + "JOIN TIPO T ON (P.ID_TIPO = T.ID_TIPO) "
                            + "JOIN LIDER L ON (P.ID_LIDER = L.ID_LIDER) "
                            + "WHERE P.BANCO_VINCULADO = ? "
                            + "ORDER BY FECHA_INICIO DESC, CIUDAD, CONSTRUCTORA "; 
        try {
                ps = con.prepareStatement(consulta);
                ps.setString(1, banco);
                rs= ps.executeQuery();
                while (rs.next()) {
                    ProyectoBancoVo object = new ProyectoBancoVo();
                    object.setId(rs.getInt("id"));
                    object.setConstructora(rs.getString("constructora"));
                    object.setCiudad(rs.getString("ciudad"));
                    object.setClasificacion(rs.getString("clasificacion"));
                    object.setEstrato(rs.getInt("estrato"));
                    object.setLider(rs.getString("lider"));
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
