package si.fri.prpo.servlet;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UporabnikDaoImpl implements BaseDao {

    private Connection con;
    private Logger log = Logger.getLogger(UporabnikDaoImpl.class.getSimpleName());

    static UporabnikDaoImpl getInstance() {
        return new UporabnikDaoImpl();
    }

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (Exception e){return null;}

    }

    public void pripravi() {
        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            String sql = "DELETE FROM uporabnik";
            ps = con.prepareStatement(sql);
            ps.execute();

            sql = "INSERT INTO uporabnik (id, ime, priimek, uporabniskoime) VALUES " +
                    "(1, 'Franci', 'Klavž', 'fk0036')," +
                    "(2, 'Janez', 'Detelja', 'det432')," +
                    "(3, 'Miha', 'Delavec', 'krneki')," +
                    "(4, 'Matej', 'Miš', 'mismas')";
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public Entiteta vrni(int id) {

        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Uporabnik ne obstaja");
            }

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return null;
    }

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabniskoime");
        Uporabnik u = new Uporabnik(ime, priimek, uporabniskoIme);
        u.setId(id);
        return u;
    }

    @Override
    public void vstavi(Entiteta ent) {
        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            Uporabnik uporabnik = (Uporabnik) ent;
            String sql = "INSERT INTO uporabnik (ime, priimek, uporabniskoime) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, uporabnik.getIme());
            ps.setString(2, uporabnik.getPriimek());
            ps.setString(3, uporabnik.getUporabniskoIme());
            ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void odstrani(int id) {
        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            String sql = "DELETE FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void posodobi(Entiteta ent) {
        PreparedStatement ps = null;

        try {

            if (con == null) {
                con = getConnection();
            }

            Uporabnik uporabnik = (Uporabnik) ent;
            String sql = "UPDATE uporabnik SET ime = ?, priimek = ?, uporabniskoime = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, uporabnik.getIme());
            ps.setString(2, uporabnik.getPriimek());
            ps.setString(3, uporabnik.getUporabniskoIme());
            ps.setInt(4, uporabnik.getId());
            ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public List<Entiteta> vrniVse() {

        PreparedStatement ps = null;
        List<Entiteta> uporabniki = new ArrayList<Entiteta>();

        try {

            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik ORDER BY id ASC";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                uporabniki.add(getUporabnikFromRS(rs));

            return uporabniki;

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return null;
    }
}
