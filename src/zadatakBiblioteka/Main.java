package zadatakBiblioteka;
import java.util.Scanner;
import java.io.IOException;
public class Main {
	private static Scanner unos=new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		Knjiga.ucitaj2();
		Racun.ucitaj();
	int brojOpcije=0;
	int i=1;
	do
	{
	
		System.out.println("Unosenjem navedenog broja birate opciju:  ");
		System.out.println("1=Kreirnaje novog Racuna. ");
		System.out.println("2=Unosenje nove Knjige. ");
		System.out.println("3=Podizanje Knjige. ");
		System.out.println("4=Sacuvati");
		 brojOpcije=unos.nextInt();
		 switch(brojOpcije) {
		 case 1:
			 kreiranjeRacuna();break;
		 case 2:
				kreiranjeKnjige();break;
		 case 3:
				podizanje(); break;
		 case 4:
				Knjiga.save(); Racun.save(); System.exit(0);
		 }
		
			
		
	}
	while(i==1);
	}
	public static void kreiranjeRacuna() throws IOException {
		
		int brojRacuna = 0;
		int brojPodignutih = 0;
		
		System.out.println("Unesite broj racuna: ");
		brojRacuna = unos.nextInt();
		unos.nextLine();
		System.out.println("Unesite ime musterije: ");
		String imeMusterije = unos.nextLine();
		
		new Racun(brojRacuna, imeMusterije, brojPodignutih);
		
	}
	
public static void kreiranjeKnjige() throws IOException {
		
        System.out.println("Unesite broj knjige: ");
		int brojKnjige = unos.nextInt();
		unos.nextLine();
		System.out.println("Unesite ime knjige: ");
		String imeKnjige = unos.nextLine();
		
		new Knjiga(brojKnjige, imeKnjige, false);
		
	}

public static void podizanje() throws IOException {
	
	System.out.println("Unesite vas broj racuna: ");
	int brojRacuna = unos.nextInt();
	
	System.out.println("Unesite broj knjige: ");
	int brojKnjige = unos.nextInt();
	
	Knjiga.podizanjeKnjige(brojRacuna, brojKnjige);
	
	
}


}
	

