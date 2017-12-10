package regras_de_negocio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FuncionalidadesStrategy {
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
