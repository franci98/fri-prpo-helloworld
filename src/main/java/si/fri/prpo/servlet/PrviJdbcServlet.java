package si.fri.prpo.servlet;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/servlet")
public class PrviJdbcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pisalec = resp.getWriter();
        UporabnikDaoImpl uporabnikDao = UporabnikDaoImpl.getInstance();
        uporabnikDao.pripravi();
        Uporabnik uporabnik = new Uporabnik("Marko", "Novak", "prpo123");

        pisalec.append("Dodajam uporabnika " + uporabnik.toString());
        uporabnikDao.vstavi(uporabnik);
        pisalec.append("\n\n");

        pisalec.append("Seznam obstojecih uporabnikov \n");
        List<Entiteta> uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> pisalec.append(u.toString() + "\n"));
        pisalec.append("\n\n");

        pisalec.append("Brišem uporabnika " + uporabnikDao.vrni(1).toString() + "\n");
        uporabnikDao.odstrani(1);
        pisalec.append("Seznam obstojecih uporabnikov \n");
        uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> pisalec.append(u.toString() + "\n"));
        pisalec.append("\n\n");

        uporabnik = (Uporabnik) uporabnikDao.vrni(4);
        uporabnik.setIme("Janez");
        uporabnikDao.posodobi(uporabnik);
        pisalec.append("Posodobljen seznam obstojecih uporabnikov. \n");
        uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> pisalec.append(u.toString() + "\n"));

    }
}
