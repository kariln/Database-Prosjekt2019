package modeller;

public class �velse {
	
	private int �velse_id;
	private String navn;
	
	public �velse(int id, String navn) {
		this.�velse_id = id;
		this.navn = navn;
	}
	

	public int get�velse_id() {
		return this.�velse_id;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	public void setNavn(String navn) {
		this.navn = navn;
	}
}
