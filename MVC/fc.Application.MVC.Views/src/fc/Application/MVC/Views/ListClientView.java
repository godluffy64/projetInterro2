package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fc.Application.MVC.Controllers.EditClientController;
import fc.Application.MVC.Controllers.EditMovieController;
import fc.Application.MVC.Controllers.ListClientController;
import fc.Application.MVC.Controllers.ListOrdersController;
import fc.Application.MVC.Controllers.UpdateMovieController;
import fc.Application.MVC.ViewModels.ClientViewModel;


public class ListClientView extends Dialog
{

	protected Object result;
	protected Shell shlListe;
	private Table table;
	
	public RunController m_Infrastructure;
	
	protected ClientViewModel[] getViewModel()
	{
		if (m_Infrastructure != null)
			return (ClientViewModel[])m_Infrastructure.m_ViewModel;
		else
			return new ClientViewModel[0];
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ListClientView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlListe.open();
		shlListe.layout();
		Display display = getParent().getDisplay();
		while (!shlListe.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {

		shlListe = new Shell(getParent(), getStyle());
		shlListe.setSize(750, 600);
		shlListe.setText("NORTHWIND PROJET L3");
		
		// LIST VIEW

		Label lblClient = new Label(shlListe, SWT.NONE);
		lblClient.setBounds(50,30,60,20);
		lblClient.setText("Clients:");

		
		Combo menu = new Combo(shlListe, SWT.BORDER | SWT.READ_ONLY);
		
		menu.setSize(300,150);
		menu.setBounds(50,50,350,30);
		
		
		Button commandes = new Button(shlListe, SWT.BORDER);
		commandes.setText("Afficher détails commandes");
		commandes.setBounds(500,510,200,30);
		commandes.setSize(200,40);
		
		Button editer = new Button(shlListe, SWT.NONE);
		editer.setText("Editer");
		editer.setBounds(625,50,75,30);
		
		table = new Table(shlListe, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(50, 100, 650, 400);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tcNum = new TableColumn(table, SWT.NONE);
		tcNum.setWidth(150);
		tcNum.setText("Num commande");
		
		TableColumn tcDate = new TableColumn(table, SWT.NONE);
		tcDate.setWidth(150);
		tcDate.setText("Date commande");

		TableColumn tcNom = new TableColumn(table, SWT.NONE);
		tcNom.setWidth(150);
		tcNom.setText("Nom Prénom");

		TableColumn tcTotal = new TableColumn(table, SWT.NONE);
		tcTotal.setWidth(150);
		tcTotal.setText("Montant Total");
		

		ClientViewModel[] Clients = getViewModel();
		
		for (ClientViewModel Client : Clients)
		{
		    menu.add(""+Client.m_LastName+"  "+ Client.m_FirstName );
		}


	

		menu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String element = menu.getText();
				String[] NomPrenom = element.split("  ");
				String LastName = NomPrenom[0];
				String FirstName = NomPrenom[1];

				m_Infrastructure.runController(shlListe, ListOrdersController.class, LastName,FirstName);
			}
		});


		editer.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String element = menu.getText();
				String[] NomPrenom = element.split("  ");
				String LastName = NomPrenom[0];
				String FirstName = NomPrenom[1];
				m_Infrastructure.runController(shlListe, EditClientController.class, LastName, FirstName);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Unimplemented method 'widgetDefaultSelected'");
			}
		});
	}
}

