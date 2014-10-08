

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class Parcours_IP {
	
	
	
	
	public ArrayList<TravailConnexion> Scan(String IP_start, String IP_end, int port) {
		
		String texteAAffichier = "";
		ArrayList<TravailConnexion> listeThreadTravail = new ArrayList<TravailConnexion>();
		
		
		IP ipStart, ipEnd;
		try {
			ipStart = new IP(IP_start);
			ipEnd = new IP(IP_end);		
			int end1=255, end2 = 255, end3 = 255, end4 = 255;
			int start1 = ipStart.getChamps1(), start2 = ipStart.getChamps2(), start3 = ipStart.getChamps3(), start4 = ipStart.getChamps4();
			boolean champs1Final = false, champs2Final = false, champs3Final = false;
			
			switch (ipStart.CompareFirstChampNotEqual(ipEnd)) {
			case 1:				
				end1 = ipEnd.getChamps1();
				break;
			case 2:
				end1 = ipEnd.getChamps1();
				end2 = ipEnd.getChamps2();			
				break;
			case 3:
				end1 = ipEnd.getChamps1();
				end2 = ipEnd.getChamps2();
				end3 = ipEnd.getChamps3();
				break;
			case 4:
				end1 = ipEnd.getChamps1();
				end2 = ipEnd.getChamps2();
				end3 = ipEnd.getChamps3();
				end4 = ipEnd.getChamps4();			
				
				break;
			}
			
			for(int i = start1;i <= end1; i++) {
				if(i == ipEnd.getChamps1()) {
					champs1Final = true;
					end2 = ipEnd.getChamps2();
				}
				for(int j = start2;j <= end2; j++) {
					if(champs1Final) {							
						if(j == ipEnd.getChamps2()) {
							champs2Final = true;
							end3 = ipEnd.getChamps3();
						}
					}
					for(int k = start3; k <= end3; k++) {
						if(champs2Final) {								
							if(k == ipEnd.getChamps3()) {
								champs3Final = true;
								end4 = ipEnd.getChamps4();
							}
						}
						for(int l = start4; l <= end4; l++) {
							try {
								Inet4Address ip = (Inet4Address) Inet4Address.getByName(i+"."+j+"."+k+"."+l);
								//System.out.println("IP = "+(ip.toString()));
								
								TravailConnexion taffe = new TravailConnexion(ip, port);
								taffe.start();
								listeThreadTravail.add(taffe);
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("IP = ERREUR");
								texteAAffichier += "IP = ERREUR";
							}
						}
						start4 = 1;
					}
					start3 = 1;
				}
				start2 = 1;
			}
			
			//teste que tous les threads ont terminé leur execution
			boolean allFinished = false;
			while(allFinished == false) {
				boolean TousTermine = true;
				for(int i=0; i<listeThreadTravail.size(); i++) {
					if(!listeThreadTravail.get(i).getEstTravailTermine()) TousTermine = false;
					//System.out.println(listeThreadTravail.get(i).getIP()+" : terminé = "+listeThreadTravail.get(i).getEstTravailTermine());
				}
				if(TousTermine) allFinished = true;
				
			}
			
			//Boucle de remplissage du résultat à partir de la liste
			for (int i =0; i< listeThreadTravail.size(); i++) {
				texteAAffichier += "IP : "+listeThreadTravail.get(i).getIP().toString()+" = "+listeThreadTravail.get(i).getEstConnecte()+"\n";
			}
			
			
		} catch (ExceptionInInitializerError e) {
			System.out.println("Erreur mauvaise IP entrée lors de la saisie");
			texteAAffichier += "Erreur mauvaise IP entrée lors de la saisie\n";
		}
	
		return  listeThreadTravail;
		
		
	}
	
public String ScanToString(String IP_start, String IP_end, int port) {
		
		String texteAAffichier = "";
		
		ArrayList<TravailConnexion> listeThreadTravail = Scan(IP_start, IP_end, port);
		
		//Boucle de remplissage du résultat à partir de la liste
		for (int i =0; i< listeThreadTravail.size(); i++) {
			texteAAffichier += "IP : "+listeThreadTravail.get(i).getIP().toString()+" = "+listeThreadTravail.get(i).getEstConnecte()+"\n";
		}
		
		return texteAAffichier;
		
	}

	
	
	
	
}