package hu.neuron.java.simple;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet("/WriterServlet")
public class WriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		test test = new test();
		test.setLong1(1L);
		request.getSession().setAttribute("test",  test);
		System.out.println(request.getSession().getAttribute("test"));
		test.setLong1(2L);
		System.out.println(request.getSession().getAttribute("test"));
		response.getWriter().write("Hello!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public class test {
		private Long long1;

		public Long getLong1() {
			return long1;
		}

		@Override
		public String toString() {
			return "test [long1=" + long1 + "]";
		}

		public void setLong1(Long long1) {
			this.long1 = long1;
		}
	}

}
