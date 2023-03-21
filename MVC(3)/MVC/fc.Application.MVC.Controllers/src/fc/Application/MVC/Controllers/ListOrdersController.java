package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Model;
import fc.Application.MVC.Model.Order;
import fc.Application.MVC.ViewModels.OrderViewModel;

public class ListOrdersController extends Controller
{

    @Override
	public ActionResult run(Object ... args)
	{
		String LastName = (String)args[0];
		String FirstName = (String)args[1];


		this.setModel(Model.getOrders(LastName,FirstName));

        Order[] Orders = m_Model.m_Orders;

        OrderViewModel[] orderViewModel = new OrderViewModel[Orders.length];

		
		for (int i=0; i < Orders.length; i++)
		{
			Order order = Orders[i];
			orderViewModel[i] = new OrderViewModel(order.m_Num, order.m_Date,LastName, FirstName,order.m_Total);
		}
		
		return View(orderViewModel);

	}

	
}
