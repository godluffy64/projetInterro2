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

    public class clientViewModel {
        public String m_FirstName;
	public String m_LastName;
	
	// EXEMPLE DE VIEW MODEL A FAIRE


	    public clientViewModel(String lastname, String FirstName)
	    {
		    m_FirstName = FirstName;
		    m_LastName = lastname;
	    }
        }

}


