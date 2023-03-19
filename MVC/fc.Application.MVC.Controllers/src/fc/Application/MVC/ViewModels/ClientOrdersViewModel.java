package fc.Application.MVC.ViewModels;

import java.util.ArrayList;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Order;

public class ClientOrdersViewModel
{
    public Client[] listClients;
    public Client client;
    public Order[] listOrders;

    public ClientOrdersViewModel(Client[] lClients,Client c, Order[] lOrders)
	{
		listClients = lClients;
		client = c;
        listOrders = lOrders;
	}

}
