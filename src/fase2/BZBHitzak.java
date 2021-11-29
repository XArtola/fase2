package fase2;

import java.util.Iterator;
import java.util.LinkedList;

public class BZBHitzak implements HitzenInterfazea {

	BZBAdabegiaHitzak erroa;

	public boolean hutsaDa() {

		return (this.erroa == null);

	}

	public void errotu(BZBAdabegiaHitzak erroa) {
		this.erroa = erroa;
	}

	/**
	 * Hitz bat gehitzen dio bilaketa-zuhaitz bitarrari
	 * 
	 * @param hitza: gehitzen den hitza
	 */
	@Override
	public void hitzaGehitu(Hitza hitza) {
		if (this.hutsaDa()) {
			this.erroa = new BZBAdabegiaHitzak(hitza.getDatua());
		} else {
			this.erroa.hitzaGehitu(hitza);
		}

	}

	@Override
	public Hitza hitzaBilatu(String hitza) {

		if (!this.hutsaDa())
			return this.erroa.hitzaBilatu(hitza);
		return null;
	}

	// Ezabatu metodoa implementatu ahal izateko gehitu beharrekoa metodoa

	public String ezabatuMin() {
		if (this.hutsaDa())

			////////////////////////////////////////////////////////////////////////////
			////////// KONTUZ HONEKIN INTEGER-EN KASUAN MIN_VALUE; ORAIN NULL.
			///////// ERROREA EMANA DEZAKE
			////////////////////////////////////////////////////////////////////////////
			return null;
		else {
			EzabatuMinEmaitza emaitza = erroa.ezabatuMin();
			this.erroa = emaitza.getAdabegia();
			return emaitza.getBalioa();
		}
	}

	/**
	 * Bilaketa-zuhaitz bitarretik hitza ezabatzen du. Bilaketa-zuhaitz bitarra
	 * izaten jarraitzen du.
	 * 
	 * @param hitza: ezabatuko den hitza Aurrebaldintza: hitza, agertzen bada, behin
	 *        bakarrik agertuko da
	 */

	private void ezabatu(Hitza hitza) {
		if (!this.hutsaDa()) {
			this.erroa = this.erroa.ezabatu(hitza);
		}
	}

	/**
	 * Inongo web-orriren hitz gakoak ez diren zuhaitzeko hitzen lista bat itzultzen
	 * du
	 * 
	 * @return Ezabatu behar diren hitzen lista
	 */
	private LinkedList<Hitza> lortuEzabatzekoHitzak() {
		
		if(this.hutsaDa())
			
			return new LinkedList<>();
		else
			
			return this.erroa.lortuEzabatzekoHitzak();
		
	}

	/**
	 * Aurreko metodoak erabiliz, ezabatu beharreko hitzen lista lortzen du, eta
	 * hitz horiek ezabatu egiten ditu.
	 */
	public void bahetuHitzGakoak() {

		LinkedList<Hitza> ezabatzekoLista = this.lortuEzabatzekoHitzak();

		for (Hitza hitza : ezabatzekoLista) {

			// System.out.println(hitza.getDatua());

			this.ezabatu(hitza);
		}

	}

}
