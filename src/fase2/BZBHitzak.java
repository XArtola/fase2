package fase2;

public class BZBHitzak implements HitzenInterfazea {

	BZBAdabegiaHitzak erroa;

	public boolean hutsaDa() {

		return (this.erroa == null);

	}

	public void errotu(BZBAdabegiaHitzak erroa) {
		this.erroa = erroa;
	}

	@Override
	public void hitzaGehitu(Hitza hitza) {
		// TODO Auto-generated method stub

	}

	@Override
	public Hitza hitzaBilatu(String hitza) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Ezabtu metodoa implementatu ahal izateko gehitu beharrekoa metodoa

	public String ezabatuMin() {
		if (this.hutsaDa())
			
			////////////////////////////////////////////////////////////////////////////
			//////////        KONTUZ HONEKIN INTEGER-EN KASUAN MIN_VALUE; ORAIN NULL.
			/////////         ERROREA EMANA DEZAKE
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

}
