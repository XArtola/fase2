package fase2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hiztegia {

	private static Hiztegia hiztegiaInstance = new Hiztegia();

	private HitzenInterfazea hitzak;

	// Eraikitzailea

	private Hiztegia() {
		// HAU ALDATU BILAKETA ZUHAITZ BITARRERA
		this.hitzak = (HitzenInterfazea) new BZBHitzak();

	}

	// Get/Seterrak

	public static Hiztegia getHiztegiaInstance() {

		return hiztegiaInstance;
	}

	public BZBHitzak getHitzak() {
		return (BZBHitzak) hitzak;
	}

	public void setHitzak(BZBHitzak hitzak) {
		this.hitzak = hitzak;
	}
	
	/**
	* Hiztegia hasieratzen du parametro gisa pasatzen zaion hiztegiarekin
	* @param hiztegia
	*/
	public void setHiztegia(HitzenInterfazea hiztegia) {
		
		/////////////////////////////////////////////////////////////////
		// Zer getatzen da zuhaitza beharrean hitzen lista pasatzen bazaio??
		/////////////////////////////////////////////////////////////////

		this.hitzak = hiztegia;
			
	}
	

	/**
	 * Hiztegia kargatzen du emandako fitxategitik
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena
	 * @throws FileNotFoundException
	 */
	private void hitzakKargatu(String fitxIzena) throws FileNotFoundException {

		// Fitxategi izenak hutsunerik ez duela ziurtatzeko
		fitxIzena.trim();

		Scanner sc = new Scanner(new File(fitxIzena));
		String lerroa;
		String[] osagaiak;

		while (sc.hasNextLine()) {
			lerroa = sc.nextLine();
			lerroa.trim();
			osagaiak = lerroa.split("\\s+");
			Hitza h = new Hitza(osagaiak[0]);
			this.hitzak.hitzaGehitu(h);

		}

		sc.close();

	}

	/**
	 * Hiztegiko hitz bakoitzari erreferentziatzen dituen web-orriak esleitzen
	 * zaizkio Aurre: Internet eta hiztegia kargatuta daude jadanik
	 */
	private void hitzenWebakKonputatu() {

		for (Web web : Internet.getInternetInstance().getWebak().getWebenLista()) {

			String domeinua = web.getDomeinua();

			for (int i = 4; i <= 10; i++) {

				for (int j = 0; j <= domeinua.length() - i; j++) {

					// Hitzen bilaketa bitarra compareTo erabiliz
					String bilatzeko = domeinua.substring(j, j + i);

					Hitza bilaketa = this.hitzaBilatu(bilatzeko);

					if (bilaketa != null) {
						// Konprobatu bi aldiz berdina ez jartzeko
						if (!bilaketa.getWebOrrienLista().getWebenLista().contains(web)) {

							bilaketa.getWebOrrienLista().getWebenLista().add(web);
							// System.out.println("a "+bilaketa.getDatua()+"\t"+web.getDomeinua());
						}

					}

				}

			}

		}

	}

	/**
	 * Hiztegia kargatzen du emandako fitxategitik, eta hitz bakoitzaren webak
	 * konputatzen ditu (hitz bakoitzari erreferentziatzen dituen web-orriak
	 * esleitzen zaizkio)
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena AURRE: Internet eta
	 *        hiztegia kargatuta daude dagoeneko
	 * @throws FileNotFoundException
	 */
	public void hasieratu(String fitxIzena) throws FileNotFoundException {

		hitzakKargatu(fitxIzena);
		hitzenWebakKonputatu();

	}

	/**
	 * Emandako stringa bilatzen du hiztegian eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (hiztegian badago), null bestela
	 */
	public Hitza hitzaBilatu(String s) {

		return this.hitzak.hitzaBilatu(s);

	}

}
