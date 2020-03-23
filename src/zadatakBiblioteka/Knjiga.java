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
public class Knjiga implements Serializable  {

	private int brojKnjige;
	private String imeKnjige;
	private boolean statusKnjige;
	private static ArrayList<Knjiga> knjiga=new ArrayList<Knjiga>();
	public static void ucitaj2 () {

		try {
			
			File fajl = new File ("knjige.txt");
			
			FileInputStream in = new FileInputStream(fajl);
			@SuppressWarnings("resource") 
			ObjectInputStream oin = new ObjectInputStream(in);

			while (true) 
				knjiga.add((Knjiga)oin.readObject());
			
		}  catch (EOFException ex) {}
		catch (Exception e) {}
	}
	public Knjiga()
	{
		
	}
	public Knjiga (int brojKnjige,String imeKnjige,boolean statusKnjige)throws IOException
	{
		if(provjeraDodavanja(brojKnjige))
		{
			this.brojKnjige=brojKnjige;
			this.brojKnjige = brojKnjige;
			this.imeKnjige = imeKnjige;
			this.statusKnjige = statusKnjige;

			knjiga.add(this);
		}
	}
	boolean provjeraDodavanja(int brojKnjige)
	{
		if (brojKnjige < 0) {
			System.out.println("Unos broja knjige ne moze biti negativan broj. Knjiga se ne moze kreirati!");
			return false;
		}

		for (int i = 0; i < knjiga.size(); i++)
			if (knjiga.get(i).brojKnjige == brojKnjige) {
				System.out.println("Unijeti broj knjige vec postoji. Knjiga se ne moze kreirati!");
				return false;
			}

		return true;
	}
	public static void podizanjeKnjige(int brojRacuna, int brojKnjige)throws IOException {

		if (provjeraPodizanja(brojRacuna, brojKnjige)) {

			Knjiga.getKnjiga(brojKnjige).statusKnjige = true;
			Racun.getRacun(brojRacuna).setBrojPosudenih(Racun.getRacun(brojRacuna).getBrojPosudenihKnjiga() + 1);
			System.out.println("Knjiga je podignuta!");
		}
	}
private static Knjiga getKnjiga(int brojKnjige) {

	int i = 0;

	for (i = 0; i < knjiga.size(); i++)
		if (knjiga.get(i).brojKnjige == brojKnjige)
			return knjiga.get(i);

	return null;
}
private static boolean provjeraPodizanja(int brojRacuna, int brojKnjige) {

	Racun trenutniRacun = Racun.getRacun(brojRacuna);

	if (trenutniRacun == null) {
		System.out.println("Unijeti racun nije pronadjen. Knjiga nije uspjesno podignuta!");
		return false;
	}

	Knjiga trenutnaKnjiga = Knjiga.getKnjiga(brojKnjige);

	if (trenutnaKnjiga == null) {
		System.out.println("Trazena knjiga nije pronadjena!");
		return false;
	}

	if (trenutniRacun.getBrojPosudenihKnjiga() == 3) {
		System.out.println("Unijeti racun vec ima tri podignute knjige. Knjiga nije uspjesno podignuta!");
		return false;
	}

	if (trenutnaKnjiga.statusKnjige) {
		System.out.println("Trazena knjiga je vec podignuta!");
		return false;
	}

	return true;
}
public static void save() throws IOException {
	FileOutputStream on = new FileOutputStream("knjige.txt");
	ObjectOutputStream oin = new ObjectOutputStream(on);

	for (int i = 0; i < knjiga.size(); i++) 
		oin.writeObject(knjiga.get(i));
	
	oin.close();
}



}
