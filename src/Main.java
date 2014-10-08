import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	private static String IP_depart = "77.193.55.2";
	private static String IP_Fin = "77.193.60.250";
	private static int PORT = 80;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parcours_IP parcours = new Parcours_IP();
		
		System.out.println("Lancement du scan (résultat dans 10sec max)...");
		ArrayList<TravailConnexion> ips = parcours.Scan(IP_depart, IP_Fin, PORT);
		
		
		System.out.println("\nIPs connectées:\n");
		
		for (TravailConnexion ip : ips) {
			if(ip.getEstConnecte())
			System.out.println("IP : "+ip.getIP()+" = "+(ip.getEstConnecte() ? "Connecté": "Déconnecté"));
		}
		
		System.out.println("\nTerminé");
	}

}
