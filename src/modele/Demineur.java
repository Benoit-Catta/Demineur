package modele;

import java.util.concurrent.ThreadLocalRandom;

public class Demineur {
	private int longueur;
	private int largeur;
	private int bombe;
	private int drapeau;
	private Case[][] grille;
	
	public Demineur(int nb_bombes) {
		this.bombe = nb_bombes;
		this.longueur = 0;
		this.largeur = 0;
		this.drapeau = this.bombe;
		
		while((this.longueur * this.largeur) < nb_bombes*4) {
			this.longueur++;
			this.largeur++;
		}
		
		this.grille = new Case[this.largeur][this.longueur];
		
		for(int i = 0;i<this.largeur;i++) {
			for(int c = 0;c<this.longueur;c++) {
				this.grille[i][c] = new Case(0,false);
			}
		}
		
		
		int bombesRestantes2 = this.bombe;
		boolean caseLibre = false;
		while(bombesRestantes2 != 0) {
			caseLibre = false;
			while(caseLibre != true) {
				int randomx = ThreadLocalRandom.current().nextInt(0, this.largeur);
				int randomy = ThreadLocalRandom.current().nextInt(0, this.largeur);
				if(grille[randomx][randomy].isBombe() != true) {
					grille[(int)randomx][(int)randomy].setBombe();
					bombesRestantes2--;
					caseLibre = true;
				}
			}
		}
		
		for(int i = 0;i<this.largeur;i++) {
			for(int c = 0;c<this.longueur;c++) {
				if(!this.grille[i][c].isBombe()) {
					this.grille[i][c].setNbBombes(checkAlentour(i, c));
				}
			}
		}
			
		}
	
	public int checkAlentour(int x,int y) {
		Demineur d = this;
		int res = 0;
		if(x != 0 && x != d.largeur-1 && y != this.largeur-1 && y != 0) {
			if(d.grille[x-1][y].isBombe()) {res++;}
			if(d.grille[x-1][y-1].isBombe()) {res++;}
			if(d.grille[x-1][y+1].isBombe()) {res++;}
			if(d.grille[x][y+1].isBombe()) {res++;}
			if(d.grille[x][y-1].isBombe()) {res++;}
			if(d.grille[x+1][y+1].isBombe()) {res++;}
			if(d.grille[x+1][y-1].isBombe()) {res++;}
			if(d.grille[x+1][y].isBombe()) {res++;}
			return res;
		}
		if(x == 0 && y == 0) {
			if(d.grille[x+1][y].isBombe()) {res++;}
			if(d.grille[x][y+1].isBombe()) {res++;}
			if(d.grille[x+1][y+1].isBombe()) {res++;}
			return res;
		}
		if(x == this.largeur-1 && y == this.largeur-1) {
			if(d.grille[x-1][y].isBombe()) {res++;}
			if(d.grille[x][y-1].isBombe()) {res++;}
			if(d.grille[x-1][y-1].isBombe()) {res++;}
			return res;
		}
		if(x == 0 && y == this.largeur-1) {
			if(d.grille[x+1][y].isBombe()) {res++;}
			if(d.grille[x+1][y-1].isBombe()) {res++;}
			if(d.grille[x][y-1].isBombe()) {res++;}
			return res;
		}
		if(y == 0 && x == this.largeur-1) {
			if(d.grille[x-1][y].isBombe()) {res++;}
			if(d.grille[x-1][y+1].isBombe()) {res++;}
			if(d.grille[x][y+1].isBombe()) {res++;}
			return res;
		}
		
		if(x != 0 && x != d.largeur-1 && y == 0) {
			if(d.grille[x-1][y].isBombe()) {res++;}
			if(d.grille[x+1][y].isBombe()) {res++;}
			if(d.grille[x][y+1].isBombe()) {res++;}
			if(d.grille[x-1][y+1].isBombe()) {res++;}
			if(d.grille[x+1][y+1].isBombe()) {res++;}
			return res;
		}
		if(x != 0 && x != d.largeur-1 && y == d.largeur-1) {
			if(d.grille[x-1][y].isBombe()) {res++;}
			if(d.grille[x+1][y].isBombe()) {res++;}
			if(d.grille[x][y-1].isBombe()) {res++;}
			if(d.grille[x-1][y-1].isBombe()) {res++;}
			if(d.grille[x+1][y-1].isBombe()) {res++;}
			return res;
		}
		if(y != 0 && y != d.largeur-1 && x == 0) {
			if(d.grille[x][y-1].isBombe()) {res++;}
			if(d.grille[x][y+1].isBombe()) {res++;}
			if(d.grille[x+1][y+1].isBombe()) {res++;}
			if(d.grille[x+1][y-1].isBombe()) {res++;}
			if(d.grille[x+1][y].isBombe()) {res++;}
			return res;
		}
		if(y != 0 && y != d.largeur-1 && x == d.largeur-1) {
			if(d.grille[x][y+1].isBombe()) {res++;}
			if(d.grille[x][y-1].isBombe()) {res++;}
			if(d.grille[x-1][y-1].isBombe()) {res++;}
			if(d.grille[x-1][y+1].isBombe()) {res++;}
			if(d.grille[x-1][y].isBombe()) {res++;}
			return res;
		}
		return -1;
	}
	
	public boolean gagner() {
		if(this.drapeau != 0) {
			return false;
		}
		Demineur d = this;
		int compteur = 0;
		for(int i = 0;i<d.largeur;i++) {
			for(int c = 0;c<d.longueur;c++) {
				if (d.grille[i][c].isBombe() && d.grille[i][c].estDrapeau()) {
					compteur++;
				}
			}
		}
		return compteur == d.bombe;
	}
	
	public boolean clic(int x, int y, boolean drapeau) {
		Demineur d = this;
		if(drapeau == true) {
			if(d.grille[x][y].estDrapeau()) {
				this.drapeau++;
				d.grille[x][y].setDrapeau();
			} else {
				if(this.drapeau == 0) {
					return true;
				}
				d.grille[x][y].setDrapeau();
				this.drapeau--;
			}
			return true;
		} else {
			if (d.grille[x][y].isBombe()) {
				d.grille[x][y].clic();
				return false;
			}
			d.grille[x][y].clic();
			afficherCaseAdjacentes(x,y);
			return true;
		}
	}
	
	public void afficherToutesLesBombes() {
		Demineur d = this;
		for(int i = 0;i<d.largeur;i++) {
			for(int c = 0;c<d.longueur;c++) {
				if (d.grille[i][c].isBombe()) {
					d.grille[i][c].clic();
				}
			}
		}
	}
	
	public void afficherCaseAdjacentes(int x, int y) {
		Demineur d = this;
		if(d.grille[x][y].getNb_bombes_adjacentes() != 0) {
			return;
		}
		if(x - 1 >= 0) {
			if(!d.grille[x-1][y].isBombe() && !d.grille[x-1][y].isClicee()) {
				d.grille[x-1][y].clic();
				if(d.grille[x-1][y].getNb_bombes_adjacentes() == 0) {
					d.afficherCaseAdjacentes(x-1, y);
				}
			}
			if(y - 1 >= 0) {
				if(!d.grille[x-1][y-1].isBombe() && !d.grille[x-1][y-1].isClicee()) {
					d.grille[x-1][y-1].clic();
					if(d.grille[x-1][y-1].getNb_bombes_adjacentes() == 0) {
						d.afficherCaseAdjacentes(x-1, y-1);
					}
				}
			}
			if(y + 1 != d.largeur) {
				if(!d.grille[x-1][y+1].isBombe() && !d.grille[x-1][y+1].isClicee()) {
					d.grille[x-1][y+1].clic();
					if(d.grille[x-1][y+1].getNb_bombes_adjacentes() == 0) {
						d.afficherCaseAdjacentes(x-1, y+1);
					}
				}
			}
		}
		
		if(x + 1 != d.largeur) {
			if(!d.grille[x+1][y].isBombe() && !d.grille[x+1][y].isClicee()) {
				d.grille[x+1][y].clic();
				if(d.grille[x+1][y].getNb_bombes_adjacentes() == 0) {
					d.afficherCaseAdjacentes(x+1, y);
				}
			}
			if(!(y - 1 < 0)) {
				if(!d.grille[x+1][y-1].isBombe() && !d.grille[x+1][y-1].isClicee()) {
					d.grille[x+1][y-1].clic();
					if(d.grille[x+1][y-1].getNb_bombes_adjacentes() == 0) {
						d.afficherCaseAdjacentes(x+1, y-1);
					}
				}
			}
			if(!(y + 1 == d.largeur)) {
				if(!d.grille[x+1][y+1].isBombe() && !d.grille[x+1][y+1].isClicee()) {
					d.grille[x+1][y+1].clic();
					if(d.grille[x+1][y+1].getNb_bombes_adjacentes() == 0) {
						d.afficherCaseAdjacentes(x+1, y+1);
					}
				}
			}
		}
		
		if (y - 1 >= 0) {
			if(!d.grille[x][y-1].isBombe() && !d.grille[x][y-1].isClicee()) {
				d.grille[x][y-1].clic();
				if(d.grille[x][y-1].getNb_bombes_adjacentes() == 0) {
					d.afficherCaseAdjacentes(x, y-1);
				}
			}
		}
		
		if (y + 1 != d.largeur) {
			if(!d.grille[x][y+1].isBombe() && !d.grille[x][y+1].isClicee()) {
				d.grille[x][y+1].clic();
				if(d.grille[x][y+1].getNb_bombes_adjacentes() == 0) {
					d.afficherCaseAdjacentes(x, y+1);
				}
			}
		}
	}
	
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public Case getCase(int x, int y) {
		return this.grille[x][y];
	}
	
	public int getBombe() {
		return this.bombe;
	}
	
	public int getDrapeau() {
		return this.drapeau;
	}
 }
