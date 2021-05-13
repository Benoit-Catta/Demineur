package modele;

public class Case {
	private int nb_bombes_adjacentes;
	private boolean bombe;
	private boolean drapeau;
	private boolean revelee;
	
	public Case(int nb_bombes_adjacentes, boolean bombe) {
		this.nb_bombes_adjacentes = nb_bombes_adjacentes;
		this.bombe = bombe;
		this.drapeau = false;
		this.revelee = false;
	}
	
	public void setDrapeau() {
		if(this.drapeau) {
			this.drapeau = false;
		} else {
			this.drapeau = true;
		}
	}
	
	public void delDrapeau() {
		this.drapeau = false;
	}
	
	public int getNb_bombes_adjacentes() {
		return this.nb_bombes_adjacentes;
	}
	
	public boolean isBombe() {
		return this.bombe;
	}
	
	public void setBombe() {
		this.bombe = true;
	}
	
	public void setNbBombes(int bombes) {
		this.nb_bombes_adjacentes = bombes;
	}
	
	public boolean estDrapeau() {
		return this.drapeau;
	}
	
	public void clic() {
		this.revelee = true;
	}
	
	public boolean isClicee() {
		return this.revelee;
	}
	
	public void reinitialiserCase(int nb_bombes_adjacentes, boolean bombe) {
		this.nb_bombes_adjacentes = nb_bombes_adjacentes;
		this.bombe = bombe;
		this.drapeau = false;
		this.revelee = false;
	}
}
