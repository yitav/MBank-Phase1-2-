package utils;

import ifc.MBankIfc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import actionsIfc.ActionIfc;
import actionsIfc.AdminActionIfc;

public class LoginManager {
	
	private static LoginManager instance = null;
	private AdminActionIfc admin = null;
	private LoginManager() {}
	
	public static LoginManager getInstance() {
		if(instance == null)
			instance = new LoginManager();
		
		return instance;
	}
	
	
	public boolean tryLoginAsAdmin(String username, String password) {
		
		MBankIfc mbankStub;
		try {
			mbankStub = (MBankIfc) Naming.lookup("rmi://localhost:8000/mbank");
			ActionIfc actionStub = mbankStub.login( username, password );
			if(actionStub == null){
				//System.out.println("actionStub is null");//Debug purposes
				return false;
			}else if(actionStub instanceof AdminActionIfc ){
				setAdmin((AdminActionIfc) actionStub);
				return true;
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
		
		return false;
	}

	public AdminActionIfc getAdmin() {
		return admin;
	}
	private void setAdmin(AdminActionIfc admin){
		this.admin = admin;
	}
}
