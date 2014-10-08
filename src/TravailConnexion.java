

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

import javax.net.SocketFactory;

public class TravailConnexion extends Thread{

	
	private Inet4Address IP;
	private int PORT;
	private boolean estConnecte;
	private boolean travailTermine;
	
	public TravailConnexion(Inet4Address ip, int port) {
		this.IP = ip;
		this.PORT = port;
		this.travailTermine = false;
	}
	
	public void run() {
		this.tenterConnexion(this.IP);
	}
	
	private void tenterConnexion(Inet4Address ip) {
		boolean connecte;
		Socket soc = new Socket();
		
		try {
			soc.connect(new InetSocketAddress(this.IP, this.PORT), 6000);
			this.estConnecte = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			this.estConnecte = false;
		}
		
		try {
			soc.close();
		} catch (IOException e) {
			
		}
		this.travailTermine = true;
		
	}
	
		
	public boolean getEstTravailTermine() {
		return this.travailTermine;
	}
	
	public boolean getEstConnecte() {
		return this.estConnecte;
	}
	
	public Inet4Address getIP() {
		return this.IP;
	}
	
}
