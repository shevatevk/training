package hu.neuron.java.project.web;

import hu.neuron.java.project.business.BusinessO;
import hu.neuron.java.project.business.DataBVO;
import hu.neuron.java.project.business.impl.BusinessOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")

public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DataServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String data3 = request.getParameter("data3");

		BusinessO businessO = new BusinessOImpl();

		if (op != null && op.equals("add")) {
			DataBVO dataVO = new DataBVO(null, data1, data2, data3);
			businessO.save(dataVO);
		} else if (op != null && op.equals("get")) {
			DataTableResponseWebVo dataTableResponseWebVo = new DataTableResponseWebVo();
			Set<DataBVO> data = businessO.findAll();
			dataTableResponseWebVo.setData(new ArrayList<>(data));
			Gson gson = new Gson();
			gson.toJson(dataTableResponseWebVo, response.getWriter());
		}

	}

}
