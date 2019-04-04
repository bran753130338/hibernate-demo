package cn.itheima.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itheima.domain.LinkMan;
import cn.itheima.service.LinkManService;

/**
 * Servlet implementation class AddLinkManServlet
 */
@WebServlet("/AddLinkManServlet")
public class AddLinkManServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LinkManService lms = new LinkManServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLinkManServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1>获得参数并且封装到LinkMan对象中
		request.setCharacterEncoding("utf-8");
		LinkMan linkMan = new LinkMan();
		try {
			BeanUtils.populate(linkMan, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2>调用service保存LinkMan对象
		lms.save(linkMan);
		//3>重定向到展示LinkMan的列表
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
