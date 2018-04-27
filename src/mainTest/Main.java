package mainTest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import dtoIfc.AccountDTOIfc;
import dtoIfc.ClientDTOIfc;
import actionsIfc.ActionIfc;
import actionsIfc.AdminActionIfc;
import actionsIfc.ClientActionIfc;
import ifc.MBankIfc;

public class Main {

	public static void main(String[] args) {
		
		Map<String , String> map = new HashMap<String, String>();
		map.put("system", "admin");
		map.put("Yosi Second", "4567");
		
		try {
			MBankIfc mbankStub = (MBankIfc) Naming.lookup("rmi://localhost:8080/mbank");
			System.out.println( mbankStub.helloWorld() );
			for( String key : map.keySet() ){
					ActionIfc actionStub = mbankStub.login( key, map.get(key) );
					
					if(actionStub == null){
						System.out.println("Password is wrong");
					}else if(actionStub instanceof AdminActionIfc) {
						System.out.println("Admin Login success");
						System.out.println("test Admin view all Clients : ");
						AdminActionIfc adminActionStub = (AdminActionIfc)actionStub;
						ClientDTOIfc[] clientsArr = adminActionStub.viewAllClientsDetails();
						for(ClientDTOIfc client : clientsArr){
		
							System.out.println( client.toString() );
						}
						
						
					}else if(actionStub instanceof ClientActionIfc){
						System.out.println("Client Login success");
						System.out.println("test Client view Clients : ");
						ClientActionIfc clientActionStub = (ClientActionIfc)actionStub;
						ClientDTOIfc client = clientActionStub.viewClientDetails(key, map.get(key));
						System.out.println(client);
						System.out.println("test Client view Accounts : ");
						AccountDTOIfc[] accountsArr = clientActionStub.viewAccountDetails( client.getClient_name(), client.getPassword() );
						
						for(AccountDTOIfc account : accountsArr){
								System.out.println(account.toString());
						}
						
					}
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
