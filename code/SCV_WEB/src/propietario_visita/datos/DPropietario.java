package propietario_visita.datos;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;

import propietario_visita.encapsulamiento.EPropietario;
import util.ServiceProvider;

public class DPropietario {
	
	private final Logger log = Logger.getLogger(DPropietario.class);

	public synchronized boolean registrar(EPropietario data) {
        Connection con = null;
        PreparedStatement st = null;
        boolean response = false;
        try {
            con = ServiceProvider.openConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "INSERT INTO propietario VALUES (?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(sql);
            if (st != null) {
                st.setInt(1, data.getCi());
                st.setString(2, data.getNombre());
                st.setString(3, data.getApellido());
                if (data.getFoto() == null) {
                	st.setNull(4, Types.NULL);
                } else {
                	Blob foto = new SerialBlob(data.getFoto());
                    st.setBlob(4, foto);
                }
                st.setString(5, String.valueOf(data.getSexo()));
                st.setBoolean(6, data.getEstado());

                st.execute();
                con.commit();
                response = true;
                log.info("Propietario guardado correctamente: " + data.toString());
            }
        } catch (SQLException e) {
            log.error("Error al guardar propietario " + data.toString() + ": ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    log.error("Error al deshacer la transacion: ", ex);
                }
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el PreparedStatement de la conexion de base de datos: ", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar la conexion de base de datos: ", e);
                }
            }
        }
        return response;
	}
	
	public synchronized boolean modificar(EPropietario data) {
        Connection con = null;
        PreparedStatement st = null;
        boolean response = false;
        try {
            con = ServiceProvider.openConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "UPDATE propietario SET nombre = ?, apellido = ?, foto = ?, sexo = ?, estado = ? WHERE ci = ?";
            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, data.getNombre());
                st.setString(2, data.getApellido());
                if (data.getFoto() == null) {
                	st.setNull(3, Types.NULL);
                } else {
                	Blob foto = new SerialBlob(data.getFoto());
                    st.setBlob(3, foto);
                }
                st.setString(4, String.valueOf(data.getSexo()));
                st.setBoolean(5, data.getEstado());
                st.setInt(6, data.getCi());

                st.execute();
                con.commit();
                response = true;
                log.info("Propietario modificado correctamente: " + data.toString());
            }
        } catch (SQLException e) {
            log.error("Error al modificar propietario " + data.toString() + ": ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    log.error("Error al deshacer la transacion: ", ex);
                }
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el PreparedStatement de la conexion de base de datos: ", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar la conexion de base de datos: ", e);
                }
            }
        }
        return response;
	}
	
	public synchronized boolean anular(EPropietario data) {
        Connection con = null;
        PreparedStatement st = null;
        boolean response = false;
        try {
            con = ServiceProvider.openConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "UPDATE propietario SET estado = false WHERE ci = ?";
            st = con.prepareStatement(sql);
            if (st != null) {
                st.setInt(1, data.getCi());

                st.execute();
                con.commit();
                response = true;
                log.info("Propietario anulado correctamente: " + data.toString());
            }
        } catch (SQLException e) {
            log.error("Error al anular propietario " + data.toString() + ": ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    log.error("Error al deshacer la transacion: ", ex);
                }
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el PreparedStatement de la conexion de base de datos: ", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar la conexion de base de datos: ", e);
                }
            }
        }
        return response;
	}
	
	public synchronized EPropietario obtenerPorKey(int ci) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        EPropietario response = null;
        try {
            con = ServiceProvider.openConnection();
            String sql = "SELECT * FROM propietario where ci = ? AND estado = TRUE";
            st = con.prepareStatement(sql);
            if (st != null) {
                st.setInt(1, ci);
                rs = st.executeQuery();
                if (rs.next()) {
                    response = new EPropietario();
                    response.setCi(rs.getInt("ci"));
                    response.setNombre(rs.getString("nombre"));
                    response.setApellido(rs.getString("apellido"));
                    Blob foto = rs.getBlob("foto");
                    if (foto == null) {
                    	response.setFoto(null);
                    } else {
                    	response.setFoto(foto.getBytes(0, (int) foto.length()));
                    }
                    response.setSexo(rs.getString("sexo").charAt(0));
                    response.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            log.error("Error al obtener datos del propietario con CI [" + ci + "]: ", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el ResultSet de la conexion de base de datos: ", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el PreparedStatement de la conexion de base de datos: ", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar la conexion de base de datos: ", e);
                }
            }
        }
        return response;
	}
	
	public synchronized List<EPropietario> obtenerTodos() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EPropietario> response = null;
        try {
            con = ServiceProvider.openConnection();
            String sql = "SELECT * FROM propietario WHERE estado = TRUE";
            st = con.prepareStatement(sql);
            if (st != null) {
                rs = st.executeQuery();
                response = new ArrayList<EPropietario>();
                while (rs.next()) {
                	EPropietario r = new EPropietario();
                    r.setCi(rs.getInt("ci"));
                    r.setNombre(rs.getString("nombre"));
                    r.setApellido(rs.getString("apellido"));
                    Blob foto = rs.getBlob("foto");
                    if (foto == null) {
                    	r.setFoto(null);
                    } else {
                    	r.setFoto(foto.getBytes(0, (int) foto.length()));
                    }
                    r.setSexo(rs.getString("sexo").charAt(0));
                    r.setEstado(rs.getBoolean("estado"));
                    
                    response.add(r);
                }
            }
        } catch (SQLException e) {
            log.error("Error al obtener datos de los propietarios: ", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el ResultSet de la conexion de base de datos: ", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el PreparedStatement de la conexion de base de datos: ", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar la conexion de base de datos: ", e);
                }
            }
        }
        return response;
	}
	
}
