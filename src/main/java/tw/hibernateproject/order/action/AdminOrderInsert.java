package tw.hibernateproject.order.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernateproject.member.model.Member;
import tw.hibernateproject.order.model.Order;
import tw.hibernateproject.order.model.OrderDao;
import tw.hibernateproject.util.HibernateUtil;


/**
 * Servlet implementation class AdminOrderInsert
 */
@WebServlet("/admin/order/insert")
public class AdminOrderInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Long datetime = System.currentTimeMillis();
			Member member = new Member(1011, "王孝小","女", "AAA", "1234", 0, "QQQ@FF", "1.jpg", new Timestamp(datetime), 0);
            
//            StockTransaction trans1 = new StockTransaction(10393);
//            StockTransaction trans2 = new StockTransaction(12625);
//            StockTransaction trans3 = new StockTransaction(7856);
            
            Order o1 = new Order(new Timestamp(datetime), 0, 0);
            Order o2 = new Order(new Timestamp(datetime), 2, 9);
            Order o3 = new Order(new Timestamp(datetime), 5, 6);
//            
//            trans1.setStock(stock1);
//            trans2.setStock(stock1);
//            trans3.setStock(stock1);
            o1.setMember(member);
            o2.setMember(member);
            o3.setMember(member);
        
            
            Set<Order> Orders = new HashSet<Order>();
            //Set<StockTransaction> stockTrans = new LinkedHashSet<StockTransaction>();
            Orders.add(o1);
            Orders.add(o2);
            Orders.add(o3);
            
            member.setOrders(Orders);
            
            session.save(member);
			
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
