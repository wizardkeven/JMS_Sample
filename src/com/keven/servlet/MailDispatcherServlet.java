package com.keven.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keven.ejb.MailSenderBean;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * Servlet implementation class MailDispatcherServlet
 */
@WebServlet("/MailDispatcherServlet")
public class MailDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private MailSenderBean mailSender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailDispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String toEmail = req.getParameter("email");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		
		//properties to be read from external file or database or server properties 
		
		String fromEmail = "wizardkeven@gmail.com";
		String username = "wizardkeven";
		String password = "keven19871111";
		
		String resultMsg = "";
//		(PrintWriter out =  resp.getWriter())
		try {
//			PrintWriter out =  resp.getWriter();
			
			//Call to mail sender bean
			mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);
			//----------------------
			
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<title>Mail Status</title>");
//			out.println("</head>");
//			out.println("<body>");
//			out.println("<h1>Mail status !!!</h1>");
//			out.println("<b> Mail Sent Successfully !!!</b><br>");
//			out.println("Client <a href='emailClient.jsp'>here</a> to go back !!!");
//			out.println("</body>");
			resultMsg = "The mail was sent successfully ";
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg ="There was an error!"+e.getMessage();
		}finally {
			req.setAttribute("Message",resultMsg );
			getServletContext().getRequestDispatcher("/Result.jsp").forward(req, resp);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
