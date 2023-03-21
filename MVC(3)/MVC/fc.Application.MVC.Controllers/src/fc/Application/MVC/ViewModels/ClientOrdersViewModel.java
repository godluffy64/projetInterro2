package fc.Application.MVC.ViewModels;

import java.util.ArrayList;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Order;

public class ClientOrdersViewModel
{
    public ClientViewModel[] m_Clients;
    public OrderViewModel[] m_Orders;

    

    public ClientOrdersViewModel(ClientViewModel[] clientViewModel, OrderViewModel[] orderViewModel) {
        m_Clients = clientViewModel;
        m_Orders = orderViewModel;
    }

}
