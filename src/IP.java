

public class IP {

	
	
	private int champs1;
	private int champs2;
	private int champs3;
	private int champs4;
	
	public IP(String ip)
	{
		if(!this.ValidIP(ip)) throw new ExceptionInInitializerError("String ip is not correct");
		
		
		this.champs1 = this.convertStringToInt(this.SelectStringChamps(ip, 1));
		this.champs2 = this.convertStringToInt(this.SelectStringChamps(ip, 2));
		this.champs3 = this.convertStringToInt(this.SelectStringChamps(ip, 3));
		this.champs4 = this.convertStringToInt(this.SelectStringChamps(ip, 4));
		
	}
	
	public int getChamps1() {
		return this.champs1;
	}
	
	public int getChamps2() {
		return this.champs2;
	}
	
	public int getChamps3() {
		return this.champs3;
	}
	
	public int getChamps4() {
		return this.champs4;
	}

	
	
	
	private boolean ValidIP(String ip) {
		if(ip.length() > "255.255.255.255".length()) return false;
		int nbChampsDansIP = this.nbChampsDansString(ip);
		for(int i=1; i<= nbChampsDansIP; i++) {
			int nbChampsI = this.convertStringToInt(this.SelectStringChamps(ip, i));
			if(nbChampsI == -1) return false;
			else if (nbChampsI > 255 || nbChampsI < 0) return false;
		}
		return true;
	}
	
	private int nbChampsDansString(String texte) {
		int nb = 1;
		for(int i = 0; i<texte.length(); i++) {
			if(texte.charAt(i) == '.') nb++;
		}
		return nb;
	}
	
	private String SelectStringChamps(String texte, int numChamps) {
		if(!(numChamps <= this.nbChampsDansString(texte))) return "error sur SelectStringChamps";
		
		int CurrentNumChamps = 1, indiceDebut = 0;
		for(int i = 0; CurrentNumChamps <= numChamps && i<texte.length(); i++ ) {
			if(texte.charAt(i) == '.') {
				if(numChamps == CurrentNumChamps) {
					try {
						return texte.substring(indiceDebut, i);
					} catch(Exception e) {
						return "";
					}
				}
				CurrentNumChamps++;
				indiceDebut = i+1;
			}
		}
		
		try {
			return texte.substring(indiceDebut, texte.length());
		} catch(Exception e) {
			return "";
		}
		
	}
	
	private int convertStringToInt(String texte) {
		try {
			return Integer.parseInt(texte);
		} catch (Exception e) {
			return -1;
		}
	}

	//Return the index of the first champs which is not equal
	public int CompareFirstChampNotEqual(IP ip) {		
		if(this.champs1 != ip.getChamps1()) return 1;
		else if (this.champs2 != ip.getChamps2()) return 2;
		else if (this.champs3 != ip.getChamps3()) return 3;
		else return 4;
	}
	
	
}