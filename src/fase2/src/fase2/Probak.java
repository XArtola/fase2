package fase2;

import java.io.File;
import java.util.Scanner;


public class Probak {
	
	public static void main(String[] args) throws Exception {
		
		Hitza a = new Hitza("a");
		Hitza b = new Hitza("b");
		Hitza c = new Hitza("c");
		Hitza aa = new Hitza("aa");
		Hitza t = new Hitza("t");
		Hitza s = new Hitza("s");
		
		Web weba = new Web(0,"aaa.com");
		
		BZBHitzak z1 = new BZBHitzak();
		z1.hitzaGehitu(c);
		z1.hitzaGehitu(aa);
		z1.hitzaGehitu(a);
		z1.hitzaGehitu(s);
		z1.hitzaGehitu(t);
		
		System.out.println(a.getDatua()+" bilaketaren emaitza "+z1.hitzaBilatu(a.getDatua()).getDatua()+" izan da");
		if(z1.hitzaBilatu("g")==null)
			System.out.println("Ez dago g elementua zuhaitzean");
		String min = z1.ezabatuMin();
		System.out.println(min+" elementua da minimoa eta ezabatu da");
		
		System.out.println("Orain erroa "+z1.erroa.getInfo().getDatua()+" da");
		
		min = z1.ezabatuMin();
		System.out.println(min+" elementua da minimoa eta ezabatu da");
		
		System.out.println("Orain erroa "+z1.erroa.getInfo().getDatua()+" da");
		
		z1.bahetuHitzGakoak();
		
		if(z1.hutsaDa())
			
			System.out.println("Zuhaitza behetu ondoren lortutako emaitza");
		

	}
}