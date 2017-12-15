package servets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regras_de_negocio.FuncionalidadesStrategy;




@WebServlet("/sistema")
public class ServletController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		String nomeDaclasse = "regras_de_negocio."+acao;
		try {
			
			Class classe = Class.forName(nomeDaclasse);
			Object obg = classe.newInstance();
			FuncionalidadesStrategy funcionalidadesStrategy = (FuncionalidadesStrategy)obg;
			funcionalidadesStrategy.executaFuncionalida(request,  response);
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}






