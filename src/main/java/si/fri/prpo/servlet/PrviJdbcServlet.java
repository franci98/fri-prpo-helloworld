package si.fri.prpo.servlet;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/servlet")
public class PrviJdbcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pisalec = resp.getWriter();
        pisalec.write("Hello World! \n");

        String str = ConfigurationUtil.getInstance().get("kumuluzee.name").get();
        pisalec.write(str + "\n");
        str = ConfigurationUtil.getInstance().get("kumuluzee.version").get();
        pisalec.write(str + "\n");
        str = ConfigurationUtil.getInstance().get("kumuluzee.env.name").get();
        pisalec.write(str + "\n");

    }
}
