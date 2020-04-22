import java.util.Scanner;

public class Methodes {

	public static String saisirPseudo() {
		
		// Méthode de saisie et de vérification longueur pseudo
		Scanner saisie = new Scanner(System.in);
		String pseudoJoueur = null;
		pseudoJoueur = saisie.nextLine();
		while(pseudoJoueur.length() < 3 || pseudoJoueur.length() > 10) {
			if(pseudoJoueur.length() < 3){
				System.out.print("Pseudo trop court entrez un nouveau pseudo : ");
				pseudoJoueur = saisie.nextLine();
			}else {
				System.out.print("Pseudo trop long entrez un nouveau pseudo : ");
				pseudoJoueur = saisie.nextLine();
			}
		}

		return pseudoJoueur;

	}


	public static int choisirColonne(char[][] plateau) {
		
		// Méthode de placement de jeton qui vérifie le numéro et l'état de la colonne
		Scanner saisie = new Scanner(System.in);
		int colonneChoisie;
		System.out.print("Choisissez une colonne entre 1 et 7 : ");
		colonneChoisie = Integer.parseInt(saisie.nextLine());
		System.out.println();

		boolean etat = false;
		while(!etat) {
			if(colonneChoisie < 1 || colonneChoisie > 7) {
				System.out.print("Colonne choisie incorrect réessayez : ");
				colonneChoisie = Integer.parseInt(saisie.nextLine());
				System.out.println();
			}else if(plateau[colonneChoisie-1][0] != '.') {
				System.out.print("Colonne pleine, veuillez choisir une autre colonne : ");
				colonneChoisie = Integer.parseInt(saisie.nextLine());
				System.out.println();
			}else {// Ici la colonne n'est pas pleine et son numéro correct
				etat = true;
			}
		}

		return colonneChoisie;
	}

	public static boolean détection(char[][] plateau, int colonneInd, int rang, boolean gagner, int tour, char symbole) {

		// Méthode de détection, si 4 jetons sont alignés

		// Détection diagonale de bas en haut en partant de la gauche vers la droite puis de haut en bas de la droite vers la gauche
		int x = colonneInd-1, y = rang-1, somme=1;
		while(y >= 0 && x >= 0 && plateau[x][y] == symbole){
			y--;
			x--;
			somme++;
		}
		x = colonneInd+1; y = rang+1;
		while(y <=5 && x < 7 && plateau[x][y] == symbole){
			y++;
			x++;
			somme++;
		}
		if(somme >= 4) 
			gagner = true;

		// Détection diagonale de bas en haut en partant de la droite vers la gauche puis de haut en bas de la gauche vers la droite
		x = colonneInd+1; y = rang-1; somme=1;
		while(y >= 0 && x <= 6 && plateau[x][y] == symbole){
			y--;
			x++;
			somme++;
		}
		x = colonneInd-1; y = rang+1;
		while(y <= 5 && x >= 0 && plateau[x][y] == symbole){
			y++; 
			x--;
			somme++;
		}
		if(somme >= 4) 
			gagner = true;

		// Détection verticale
		x = colonneInd; y = rang+1; somme=1;
		while(y <= 5 && plateau[x][y] == symbole){
			y++;
			somme++;
		}
		if(somme >= 4) 
			gagner= true;

		// Détection horizontale de gauche à droite
		x = colonneInd-1; y = rang; somme=1;
		while(x >= 0 && plateau[x][y] == symbole){
			x--; 
			somme++;
		}
		x = colonneInd+1; y = rang;
		while(x <= 6 && plateau[x][y] == symbole){
			x++;
			somme++;
		}
		if(somme >= 4) 
			gagner = true;

		return gagner;

	}

	public static void affichage(char[][] plateau) {

		// Méthode d'affichage
		System.out.print("\n"+"  1  2  3  4  5  6  7");
		System.out.println();
		for(int contour = 0 ; contour<(7*3)+2 ; contour++)
			System.out.print("-");
		System.out.println();
		for(int y = 0 ; y<6 ; y++) {
			System.out.print("|");
			for(int x = 0 ; x<7 ; x++)
				System.out.print(" " +plateau[x][y] + " ");
			System.out.print("|" + "\n");
		}
		for(int contour = 0 ; contour<(7*3)+2 ; contour++)
			System.out.print("-");
		System.out.println("\n");
	}

	public static char[][] miseàZéro(char[][] plateau, int colonne, int ligne) {
		
		// Méthode de mise à zéro du plateau 
		for(int x = 0 ; x<colonne ; x++)
			for(int y = 0 ; y<ligne ; y++)
				plateau[x][y] = '.';
		return plateau;

	}
}
