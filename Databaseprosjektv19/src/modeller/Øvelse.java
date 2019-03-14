package modeller;

public class Øvelse {
	
	private int øvelse_id;
	private String navn;
	
	public Øvelse(int id, String navn) {
		this.øvelse_id = id;
		this.navn = navn;
	}
	

	public int getØvelse_id() {
		return this.øvelse_id;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	public void setNavn(String navn) {
		this.navn = navn;
	}
}
