package fase2;

public class BZBAdabegiaHitzak {

	private Hitza info;

	private BZBAdabegiaHitzak ezkerra;
	private BZBAdabegiaHitzak eskuina;

	public BZBAdabegiaHitzak(String hitza) {
		info = new Hitza(hitza);
		ezkerra = null;
		eskuina = null;
	}

	/**
	 * Adabegiak ezkerreko umea baduen ala ez itzultzen du.
	 * 
	 * @return true, ezkerreko umea baldin badu; false, bestela.
	 */
	public boolean baduEzkerra() {
		return this.ezkerra != null;
	}

	/**
	 * Adabegiak eskuineko umea baduen ala ez itzultzen du.
	 * 
	 * @return true, eskuineko umea baldin badu; false, bestela.
	 */
	public boolean baduEskuina() {
		return this.eskuina != null;
	}

	/**
	 * Adabegia hostoa den ala ez itzultzen du.
	 * 
	 * @return true, hostoa bada; false, bestela.
	 */
	public boolean hostoaDa() {
		return !this.baduEzkerra() && !this.baduEskuina();
	}
	
	
	public Hitza hitzaBilatu(String hitza) {
		if (this.info.getDatua().equals(hitza)) {
			return this.info;
		}
		if (this.info.getDatua().compareTo(hitza) < 0 && this.baduEzkerra()) {

			return this.ezkerra.hitzaBilatu(hitza);
		}
		if (this.info.getDatua().compareTo(hitza) > 0 && this.baduEskuina()) {

			return this.eskuina.hitzaBilatu(hitza);
		}
		return null;
	}
	

	public BZBAdabegiaHitzak ezabatu(Hitza hitza) {

		int konparaketa = this.info.getDatua().compareTo(hitza.getDatua());

		if (konparaketa == 0) {// (a): this da ezabatu beharreko adabegia
			if (!this.baduEzkerra())
				return this.eskuina; // (a1) kasua
			else if (!this.baduEskuina())
				return this.ezkerra; // (a2) kasua
			else {// (a3) kasua: bi azpizuhaitzak ditu
					// eskuineko balio minimoarekin ordezkatu
				EzabatuMinEmaitza min = this.eskuina.ezabatuMin();
				this.eskuina = min.getAdabegia();
				this.info.setDatua(min.getBalioa());
				return this;
			}
		} else if (konparaketa > 0) {// (b): ezabatu beharreko elementua ezkerreko
			// azpizuhaitzean egongo da, baldin badago
			if (this.baduEzkerra())
				this.ezkerra = this.ezkerra.ezabatu(hitza);
			return this;
		} else {// this.info < elem: (c) ezabatu beharreko elementua eskuineko
				// azpizuhaitzean egongo da, baldin badago
			if (this.baduEskuina())
				this.eskuina = this.eskuina.ezabatu(hitza);
			return this;
		}

	}

	public EzabatuMinEmaitza ezabatuMin() {
		EzabatuMinEmaitza emaitza = new EzabatuMinEmaitza();
		if (!this.baduEzkerra()) {// b) txikiena unekoa da
			emaitza.setBalioa(this.info.getDatua());
			emaitza.setAdabegia(this.eskuina);
		} else { // a) txikiena ezkerreko azpizuhaitzean dago
			EzabatuMinEmaitza emaitzaEzkerra = this.ezkerra.ezabatuMin();
			this.ezkerra = emaitzaEzkerra.getAdabegia();
			emaitza.setBalioa(emaitzaEzkerra.getBalioa());
			emaitza.setAdabegia(this);
		}
		return emaitza;
	}



}
