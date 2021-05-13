import modele.Demineur;
import vue.Fenetre;

public class Application {

	public static void main(String[] args) {
		Demineur demineur = new Demineur(10);
		Fenetre fenetre = new Fenetre(demineur);
	}

}
