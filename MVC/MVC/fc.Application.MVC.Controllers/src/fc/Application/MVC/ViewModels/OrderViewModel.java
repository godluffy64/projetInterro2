package fc.Application.MVC.ViewModels;

import java.util.Date;

public class OrderViewModel 
{
    public int m_Num;
    public Date m_Date;
    public String m_LastName;
    public String m_FirstName;
    public Double m_Total;
    
    public OrderViewModel(int Num,Date Date, String LastName, String  FirstName, Double Total )
	{
		m_Num = Num;
		m_Date = Date;
		m_LastName = LastName;
        m_FirstName = FirstName;
        m_Total= Total;
	}

}


