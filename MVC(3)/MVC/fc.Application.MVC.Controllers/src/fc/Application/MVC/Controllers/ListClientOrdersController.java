package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Model;
import fc.Application.MVC.Model.Order;
import fc.Application.MVC.ViewModels.ClientOrdersViewModel;
import fc.Application.MVC.ViewModels.ClientViewModel;
import fc.Application.MVC.ViewModels.OrderViewModel;

public class ListClientOrdersController extends Controller {

    @Override
    public ActionResult run(Object... args) {

        this.setModel(Model.getClient());
        
        Client[] Clients = m_Model.m_Clients;

        if(args.length == 2){
            String LastName = (String)args[0];
		    String FirstName = (String)args[1];
            this.setModel(Model.getOrders(LastName,FirstName));
        }
        else if (args.length == 4)
        {
            String LastName = (String)args[0];
		    String FirstName = (String)args[1];
            String newLastName = (String)args[2];
		    String newFirstName = (String)args[3];
            for(int i = 0; i < Clients.length; i++)
            {
                if(Clients[i].m_FirstName.equals(FirstName)  & Clients[i].m_LastName.equals(LastName))
                {
                    Clients[i].m_FirstName = newFirstName;
                    Clients[i].m_LastName = newLastName;
                } 
            } 
            this.setModel(Model.getOrders());
        } 

        else
        {
            this.setModel(Model.getOrders());
        }


        ClientViewModel[] clientViewModel = new ClientViewModel[Clients.length];
        

        for (int i=0; i < Clients.length; i++)
		{
			Client client = Clients[i];
			clientViewModel[i] = new ClientViewModel(client.m_LastName, client.m_FirstName);
		}
        Order[] Orders = m_Model.m_Orders;
        OrderViewModel[] orderViewModel = new OrderViewModel[Orders.length];


        

       


        

        for (int i=0; i < Orders.length; i++)
		{
			Order order = Orders[i];
			orderViewModel[i] = new OrderViewModel(order.m_Num, order.m_Date,order.m_LastName, order.m_FirstName,order.m_Total);
		}

        
        ClientOrdersViewModel clientOrdersViewModel = new ClientOrdersViewModel(clientViewModel,orderViewModel);

        return View(clientOrdersViewModel);
    }


    public ActionResult run() {

        this.setModel(Model.getClient());

        Client[] Clients = m_Model.m_Clients;
        ClientViewModel[] clientViewModel = new ClientViewModel[Clients.length];

        this.setModel(Model.getOrders());

        Order[] Orders = m_Model.m_Orders;
        OrderViewModel[] orderViewModel = new OrderViewModel[Orders.length];


        

        for (int i=0; i < Clients.length; i++)
		{
			Client client = Clients[i];
			clientViewModel[i] = new ClientViewModel(client.m_LastName, client.m_FirstName);
		}


        

        for (int i=0; i < Orders.length; i++)
		{
			Order order = Orders[i];
			orderViewModel[i] = new OrderViewModel(order.m_Num, order.m_Date,order.m_LastName, order.m_FirstName,order.m_Total);
		}

        
        ClientOrdersViewModel clientOrdersViewModel = new ClientOrdersViewModel(clientViewModel,orderViewModel);

        return View(clientOrdersViewModel);
    }
    
}
