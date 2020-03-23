package zadatakBiblioteka;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Racun implements Serializable {
	private int brojRacuna;
	private String imeMusterije;
	private int brojPosudenih;
	private static ArrayList<Racun> racun = new ArrayList<Racun>();
	public static void ucitaj () {


		try {
			
			File fajl = new File ("racuni.txt");
			
			FileInputStream in = new FileInputStream(fajl);
			@SuppressWarnings("resource")
			ObjectInputStream oin = new ObjectInputStream(in);

			while (true) 
				racun.add((Racun)oin.readObject());


		} catch (EOFException ex) {}
		catch (Exception e) {}
	}
public Racun() {
		
	}
	
	public Racun(int brojRacuna, String imeMusterije, int brojPosudenih) throws IOException {
		
		if (provjeraDodavanjaRacuna(brojRacuna)) {
			
			this.brojRacuna = brojRacuna;
			this.imeMusterije = imeMusterije;
			this.brojPosudenih = brojPosudenih;
			
			racun.add(this);
		}
	}
boolean provjeraDodavanjaRacuna (int brojRacuna) {
		
		if (brojRacuna < 0) {
			System.out.println("Unos broja racuna ne moze biti negativan broj. Racun se ne moze kreirati!");
			return false;
		}
		
		for (int i = 0; i < racun.size(); i++)
			if (racun.get(i).brojRacuna == brojRacuna) {
				System.out.println("Unijeti broj racuna vec postoji. Racun se ne moze kreirati!");
				return false;
			}
		
		return true;	
	}
public static Racun getRacun(int brojRacuna) {
	
	int i = 0;
	
	for (i = 0; i < racun.size(); i++)
		if (racun.get(i).brojRacuna == brojRacuna)
			return racun.get(i);
	
	return null;
}
public static String ispisRacuna(int brojRacuna){
	
	for (int i = 0; i < racun.size(); i++)
		if (brojRacuna == racun.get(i).brojRacuna) 
			return "Broj racuna: " + racun.get(i).brojRacuna + "Ime musterije: " + racun.get(i).imeMusterije + "Broj posudjenih knjiga: " + racun.get(i).brojPosudenih;
		
	return "Unijeti broj racuna ne postoji.";
	
}
public int getBrojPosudenihKnjiga() {
	return brojPosudenih;
}

public void setBrojPosudenih(int brojPosudenih) {
	this.brojPosudenih = brojPosudenih;
}

public String getImeMusterije() {
	return imeMusterije;
}
public static void save() throws IOException {
	FileOutputStream on = new FileOutputStream("racuni.txt");
	ObjectOutputStream oin = new ObjectOutputStream(on);

	for (int i = 0; i < racun.size(); i++) 
		oin.writeObject(racun.get(i));
	
	oin.close();
}


}
