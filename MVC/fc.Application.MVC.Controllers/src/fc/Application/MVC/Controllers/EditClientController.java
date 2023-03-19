package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Model;
import fc.Application.MVC.ViewModels.ClientViewModel;

public class EditClientController extends Controller
{
    @Override
	public ActionResult run(Object ... args)
	{
        String LastName = (String)args[0];
		String FirstName = (String)args[1];
		this.setModel(Model.getClientEditer());

		// Doit Afficher les CLients

		// AFFICHE LA LISTE DES MOVIES AVEC MOVIEVIEWMODEL
		
		Client Client = m_Model.m_ClientEditer;
		
		ClientViewModel clientViewModel = new ClientViewModel(Client.m_FirstName, Client.m_LastName); // (movie.m_Id, movie.m_Title, movie.m_Year);		 


		return View(clientViewModel);
	}   
}
