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
import org.eclipse.swt.widgets.Text;

import fc.Application.MVC.Controllers.EditMovieController;
import fc.Application.MVC.Controllers.ListClientController;
import fc.Application.MVC.Controllers.ListClientOrdersController;
import fc.Application.MVC.Controllers.ListOrdersController;
import fc.Application.MVC.Controllers.UpdateMovieController;
import fc.Application.MVC.ViewModels.ClientViewModel;

public class EditClientView extends Dialog
{

	protected Object result;
	protected Shell shlListe;

	private Text txtLastName;
	private Text txtFirstName;
	
	public RunController m_Infrastructure;
	
	protected ClientViewModel getViewModel()
	{
			return (ClientViewModel)m_Infrastructure.m_ViewModel;
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EditClientView(Shell parent, int style) {
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
		shlListe.setSize(450, 400);
		shlListe.setText("Détails du client");
		
		// LIST VIEW
		  

		txtLastName = new Text(shlListe, SWT.BORDER);
		txtLastName.setBounds(150, 30, 200, 30);
		txtLastName.setText(getViewModel().m_LastName);


		txtFirstName = new Text(shlListe, SWT.BORDER);
		txtFirstName.setBounds(150, 70, 200, 30);
		txtFirstName.setText(getViewModel().m_FirstName);
		


		Label lblNom = new Label(shlListe, SWT.NONE);
		lblNom.setBounds(20,30,60,20);
		lblNom.setText("Nom");

		Label lblPrenom = new Label(shlListe, SWT.NONE);
		lblPrenom.setBounds(20,70,60,20);
		lblPrenom.setText("Prenom");
		



		

		Button btnRevenir = new Button(shlListe, SWT.NONE);
		btnRevenir.setBounds(275, 300, 150, 30);
		btnRevenir.setText("Liste des clients");	
		
		
		btnRevenir.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newLastName = txtLastName.getText();
				String newFirstName = txtFirstName.getText();
				m_Infrastructure.runController(shlListe, ListClientOrdersController.class, getViewModel().m_LastName, getViewModel().m_FirstName, newFirstName, newLastName);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Unimplemented method 'widgetDefaultSelected'");
			}
		});
	}
}