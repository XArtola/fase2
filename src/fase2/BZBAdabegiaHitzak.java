package fase2;

import java.util.LinkedList;

public class BZBAdabegiaHitzak {

	private Hitza info;

	private BZBAdabegiaHitzak ezkerra;
	private BZBAdabegiaHitzak eskuina;

	// Eraikitzailea

	public BZBAdabegiaHitzak(String hitza) {
		info = new Hitza(hitza);
		ezkerra = null;
		eskuina = null;
	}

	// Getterrak eta Setterrak

	public Hitza getInfo() {
		return info;
	}

	public void setInfo(Hitza info) {
		this.info = info;
	}

	public BZBAdabegiaHitzak getEzkerra() {
		return ezkerra;
	}

	public void setEzkerra(BZBAdabegiaHitzak ezkerra) {
		this.ezkerra = ezkerra;
	}

	public BZBAdabegiaHitzak getEskuina() {
		return eskuina;
	}

	public void setEskuina(BZBAdabegiaHitzak eskuina) {
		this.eskuina = eskuina;
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
		if (this.info.getDatua().compareTo(hitza) < 0 && this.baduEskuina()) {

			return this.eskuina.hitzaBilatu(hitza);
		}
		if (this.info.getDatua().compareTo(hitza) > 0 && this.baduEzkerra()) {

			return this.ezkerra.hitzaBilatu(hitza);
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

	public void hitzaGehitu(Hitza hitza) {

		if (this.info.getDatua().compareTo(hitza.getDatua()) > 0) {
			if (this.baduEzkerra()) {
				this.ezkerra.hitzaGehitu(hitza);
			} else {
				this.ezkerra = new BZBAdabegiaHitzak(hitza.getDatua());
			}
		} else { 
			if (this.baduEskuina()) {
				this.eskuina.hitzaGehitu(hitza);
			} else {
				this.eskuina = new BZBAdabegiaHitzak(hitza.getDatua());
			}
		}

	}

	public LinkedList<Hitza> lortuEzabatzekoHitzak() {

		LinkedList<Hitza> lista;
		LinkedList<Hitza> listaEzker;
		LinkedList<Hitza> listaEskuin;

		if (this.hostoaDa()) {

			lista = new LinkedList<>();

			if (this.info.getWebOrrienLista().getWebenLista().isEmpty())

				lista.add(this.info);

			return lista;

		}

		else {

			if (this.baduEzkerra())

				listaEzker = this.ezkerra.lortuEzabatzekoHitzak();

			else
				listaEzker = new LinkedList<>();

			if (this.baduEskuina())
				
				listaEskuin = this.eskuina.lortuEzabatzekoHitzak();

			else
				listaEskuin = new LinkedList<>();

			listaEzker.addAll(listaEskuin);
			
			if (this.info.getWebOrrienLista().getWebenLista().isEmpty())

				listaEzker.addLast(this.info);

			return listaEzker;

		}

	}

}
