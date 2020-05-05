package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.PaymentDetails;;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PaymentDetails paymentdetails= new PaymentDetails();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = paymentdetails.insertPaymentDetails(
				request.getParameter("app_Id"),
				request.getParameter("cardType"),
				request.getParameter("nameOnCard"),
				request.getParameter("cardno"),
				request.getParameter("phone"),
				request.getParameter("expdate"),
				request.getParameter("amount"),
				request.getParameter("status"));
				response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = paymentdetails.updatePaymentDetails(paras.get("hidPaymentIDSave").toString(), paras.get("app_Id").toString(),
				paras.get("cardType").toString(), paras.get("nameOnCard").toString(), paras.get("cardno").toString(), paras.get("phone").toString(), 
				paras.get("expdate").toString(), paras.get("amount").toString(), paras.get("status").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = paymentdetails.deletePaymentDetails(paras.get("id").toString());
		response.getWriter().write(output);
	}

	
}
