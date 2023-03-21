package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.Model.Model;
import fc.Application.MVC.ViewModels.ClientViewModel;

public class EditClientController extends Controller
{
    @Override
	public ActionResult run(Object ... args)
	{
		this.setModel(Model.getClientEditer());
        String LastName = (String)args[0];
		String FirstName = (String)args[1];
		

		// Doit Afficher les CLients

		// AFFICHE LA LISTE DES MOVIES AVEC MOVIEVIEWMODEL
		
		ClientViewModel clientViewModel = new ClientViewModel(LastName, FirstName); // (movie.m_Id, movie.m_Title, movie.m_Year);		 
		
		return View(clientViewModel);
	}   
}
