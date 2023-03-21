package fc.Application.MVC.ViewModels;

import java.util.Date;

public class ViewModel 
{
    public ClientViewModel clientViewModel;
    public OrderViewModel orderViewModel;

    public ViewModel(ClientViewModel client,OrderViewModel order){
        clientViewModel = client;
        orderViewModel = order;
    }
    public ClientViewModel getClientViewModel() {
        return clientViewModel;
    }

    public OrderViewModel getOrderViewModel() {
        return orderViewModel;
    }

    
}


