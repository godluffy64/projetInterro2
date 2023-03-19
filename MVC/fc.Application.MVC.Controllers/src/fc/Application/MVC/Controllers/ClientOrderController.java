package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Order;
import fc.Application.MVC.ViewModels.ClientOrdersViewModel;
import fc.Application.MVC.ViewModels.ClientViewModel;

public class ClientOrderController extends Controller {

    @Override
    public ActionResult run(Object... args) {
        // TODO Auto-generated method stub
        


        Client[] clients = m_Model.m_Clients;
        Client c = clients[0]; 
        Order[] orders = m_Model.m_Orders; 

        ClientOrdersViewModel clientOrdersViewModel = new ClientOrdersViewModel(clients, c, orders);

        return View(clientOrdersViewModel);
    }
    
}
