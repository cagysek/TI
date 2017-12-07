package semestralka;

public class Button {
	private String name;
	private int cena;
	public Button(String name,int cena){
		this.name = name;
		this.cena = cena;
	}
	
	public Button(String name) {
		this.name = name;
	}
	
	public int getCena() {
		return cena;
	}
}

