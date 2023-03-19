package fc.Application.MVC.Controllers;


import fc.Application.MVC.ViewModels.OrderViewModel;
public class DetailsCommandeController extends Controller{

    @Override
    public ActionResult run(Object... args) {
        int ID = (int)args[0]; 

        // this.setModel(Model.getOrdersDetails(ID));


        OrderViewModel orderViewModel = new OrderViewModel(ID, null, null, null, null);
        return View(orderViewModel);
    }
    
}
