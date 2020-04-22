/*
 * Auteur : Lucas Laplanche 
 * Date : 05/01/2020 
 * Projet : puissance 4 
 */
import java.util.Scanner;

public class Puissance4 {

	public static void main(String[] args) {

		Scanner saisie = new Scanner(System.in);

		// Création du tableau à 2 dimensions qui sera le plateau du jeu
		int colonne = 7 , ligne = 6;
		char plateau[][] = new char [colonne][ligne];

		// Envoie le plateau vers la méthode où il sera comme le nom l'indique mis à zéro 
		Methodes.miseàZéro(plateau,colonne,ligne);

		// Saisie des pseudos et vérification longueurs pseudos grâce à la méthode saisirPseudo
		System.out.print("Saisissez pseudo Joueur 1 : ");
		String j1 = Methodes.saisirPseudo();
		
		System.out.print("Saisissez pseudo Joueur 2 : ");
		String j2 = Methodes.saisirPseudo();
		
		while(j2.equals(j1)) {
			System.out.print("Choisissez un pseudo différent de celui du joueur 1 qui est " + '"' + j1 + '"' + " :");
			j2 = Methodes.saisirPseudo();	
		}

		// Création des variables pour compter le nombre de tour, pour l'indice de la colonne choisie par le joueur,
		// du booléen qui sera l'un des paramètres de sortie de la boucle while et du symbole du jeton selon le tour
		int tour = 1,colonneInd;
		boolean gagner = false;
		char symbole;
		System.out.println("\n"+"**********DEBUT**********" + "\n");

		// Boucle du jeu 
		while(tour<=(colonne*ligne) && !gagner) {

			symbole = (tour%2 == 1 ? 'X' : 'O');
			System.out.println("Tour "+tour+"/"+(colonne*ligne)+ ", état du plateau : ");
			Methodes.affichage(plateau);
			// Celon le nombre de tou ce sera le tour du joeur 1 (ici tour impair) ou du joueur2 (ici tour pair)
			if (tour%2 == 1) {
				System.out.print("Tour de " + j1 + ", ");
			}else {
				System.out.print("Tour de " + j2 + ", ");
			}
			// On retire 1 à colonneChoisie pour correspondre à l'indice de la colonne 1 à 7
			colonneInd = Methodes.choisirColonne(plateau) - 1;
			int rang = ligne-1; // Rang est initialisé à 5 pour vérifier de bas en haut qu'il n'y est pas de jetons pour pouvoir le placer au bon endroit
			while(plateau[colonneInd][rang]!='.') {
				rang--;
			}
			plateau[colonneInd][rang] = symbole; // Selon le tour ce sera soit le symbole du j1 "X" ou J2 "O"
			// Envoie le booléen à la méthode détection pour voir si le joeur à gagner
			gagner = Methodes.détection(plateau,colonneInd,rang,gagner,tour,symbole);
			System.out.println("*************************");
			tour++;

		}

		if(gagner == true && tour%2 == 1) {
			System.out.println("************************" + "\n" +
					"************************" + "\n" +
					"****FIN DE LA PARTIE****" + "\n" +
					"************************" + "\n" +
					"************************" + "\n" +
					"************************");
			Methodes.affichage(plateau); 
			System.out.print("La victoire revient à " + j1 + " !!!");
		}else if (gagner == true && tour%2 == 0) {
			System.out.println("************************"+ "\n"+
					"************************" + "\n" +
					"****FIN DE LA PARTIE****" + "\n" +
					"************************" + "\n" +
					"************************" + "\n" +
					"************************");
			Methodes.affichage(plateau); 
			System.out.print("La victoire revient à " + j2 + " !!!");
		}else { 
			System.out.println("************************" + "\n" +
					"************************" + "\n" +
					"****FIN DE LA PARTIE****" + "\n" +
					"************************" + "\n" +
					"************************" + "\n" +
					"************************");
			Methodes.affichage(plateau); 
			System.out.print("Egalité entre " + j1 + " et " + j2 + ".");
		}
		saisie.close();

	}
}