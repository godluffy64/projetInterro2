package fc.Application.MVC.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.sql.rowset.serial.SerialArray;

import org.h2.tools.SimpleResultSet;


public class Model
{
	public Movie[] m_Movies;
	
    public Client[] m_Clients;
    private static Model s_ModelClient = null;

    public Order[] m_Orders;
    private static Model s_ModelOrder = null;

    public Client m_ClientEditer;
    private static Model s_ModelClientEditer = null;


	public static Model getModel()
	{
		// Fonction par défaut vide
		
		return new Model();
	}

    public static void CreateDatabase(){

        String connUrl = "jdbc:h2:./Database/h2database";
        String username = "sa";
        String password = "";
        

        try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

			Statement stat = conn.createStatement();
            
            stat.execute("CREATE ALIAS NORTHWIND FOR \"fc.Application.MVC.Model.Model.getCSV\"");
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

	public static Model getClient(){

        // A décommenter afin de recréer la base de donnée.

        //Model.CreateDatabase();



		String connUrl = "jdbc:h2:./Database/h2database";
        String username = "sa";
        String password = "";
        

        try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

            PreparedStatement prep = conn.prepareStatement("SELECT `LAST NAME`, `FIRST NAME` FROM NORTHWIND('Customers')");
                
            ResultSet rs = prep.executeQuery();


            Model m = new Model();
            ArrayList<String> listeLastName = new ArrayList<>();
            ArrayList<String> listeFirstName = new ArrayList<>();
            while (rs.next()){
                String LastName = rs.getString(1);
				String FirstName = rs.getString(2);
                
                listeLastName.add(LastName);
                listeFirstName.add(FirstName);

            }       

            Client[] listeClient = new Client[listeLastName.size()];
            for(int i = 0; i<listeLastName.size();i++){
                listeClient[i] = new Client(listeLastName.get(i),listeFirstName.get(i));
            }
            m.m_Clients = listeClient;

            s_ModelClient = m;
            
            prep.close();
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
		return s_ModelClient;
	}		

	public static ResultSet getCSV(Connection conn, String NomDeTable) throws SQLException{
    
        ResultSet rs = new SimpleResultSet();

        String Path = "./Database/" + NomDeTable + ".csv";

        Scanner scanner;
        try {
            scanner = new Scanner(new File(Path));

            // Chaque case d'une ligne est séparé par ;
            // Chaque ligne est séparé par un retour à la ligne soit \n

            scanner.useDelimiter(";");


            String[] ligne = scanner.nextLine().split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // Supprime le premier élément du fichier CSV, utilisé pour indiquer le début des données en CSV
            ligne[0] = ligne[0].substring(1);


            for(int i = 0; i<ligne.length;i++){

                ((SimpleResultSet) rs).addColumn(ligne[i].toUpperCase(),Types.VARCHAR,10,0);
            }

            
            
            while(scanner.hasNext()){

                ligne = scanner.nextLine().split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                ((SimpleResultSet) rs).addRow(ligne);
                
            }


            
            
            scanner.close();
            return rs; 

        }catch(Exception E){
            System.out.println(E);
            return null;
        } 
    }

    public static Model getOrders(String m_LastName, String m_FirstName){

        // A décommenter afin de recréer la base de donnée.

        //Model.CreateDatabase();

		String connUrl = "jdbc:h2:./Database/h2database";
        String username = "sa";
        String password = "";
        

        try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

            PreparedStatement prep = conn.prepareStatement("SELECT CAST(o.`Order ID` AS INTEGER) , o.`Order Date` , c.`Last Name`, c.`First Name`, CAST(REPLACE(od.`Unit Price`,',','.') AS FLOAT), CAST(od.`Quantity` AS DOUBLE) FROM NORTHWIND('Customers') c INNER JOIN NORTHWIND('Orders') o ON o.`Customer ID` = c.`ID` INNER JOIN NORTHWIND('OrderDetails') od ON o.`Order ID`=od.`Order ID` WHERE c.`Last Name` = '" + m_LastName +"' AND c.`First Name` = '"+ m_FirstName + "'");
                
            ResultSet rs = prep.executeQuery();


            
            Model m = new Model();
            SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY");

            ArrayList<Integer> listNum = new ArrayList<>();
            ArrayList<Date> listDate = new ArrayList<>();
            ArrayList<String> listLastName = new ArrayList<>();
            ArrayList<String> listFirstName = new ArrayList<>();
            ArrayList<Double> listTotal = new ArrayList<>();

            while (rs.next()) {

                int Num = rs.getInt(1);
				Date Date = format.parse(rs.getString(2));
                String LastName = rs.getString(3);
				String FirstName = rs.getString(4);
                Double Total = rs.getFloat(5) * rs.getDouble(6);
				
                listNum.add(Num);
                listDate.add(Date);
                listLastName.add(LastName);
                listFirstName.add(FirstName);
                listTotal.add(Total);
                
            }       

            Order[] listOrder = new Order[listLastName.size()];
            for(int i = 0; i<listLastName.size();i++){
                listOrder[i] = new Order(listNum.get(i),listDate.get(i),listLastName.get(i),listFirstName.get(i),listTotal.get(i));
            }
            m.m_Orders = listOrder;

            s_ModelOrder = m;



            
            prep.close();
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
		return s_ModelOrder;
	}		



    public static Model getClientEditer(){

        // A décommenter afin de recréer la base de donnée.

        //Model.CreateDatabase();


            Model m = new Model();

            m.m_ClientEditer = new Client("null", "null");

            s_ModelClientEditer = m;

		return s_ModelClientEditer;
	}		


    // public static Model getDetailsCommandes(String NomPrenom){

	// 	String connUrl = "jdbc:h2:./fc/Application/MVC/Model/base_de_donnees/h2database";
    //     String username = "sa";
    //     String password = "";
        

    //     try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

    //         String[] NomComplet = NomPrenom.split(" ");

    //         String LastName = NomComplet[1];
    //         String FirstName = NomComplet[2];

    //         PreparedStatement prep = conn.prepareStatement("SELECT `ORDER ID`, `ORDER DATE`,`Unit Price`,`Quantity` FROM NORTHWIND('Customers') WHERE "+ LastName+"");
                
    //         ResultSet rs = prep.executeQuery();

    //         while (rs.next()) {
    //             String LastName = rs.getString(1);
	// 			String FirstName = rs.getString(2);
    //         }       
            
    //         prep.close();
    //     }
    //     catch (Exception e){
    //         e.printStackTrace(System.err);
    //     }
	// 	return s_Model;
	// }		

}
