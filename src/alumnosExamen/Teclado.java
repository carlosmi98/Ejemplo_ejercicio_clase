package alumnosExamen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Teclado {
	String leerString() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;
	}
	int leerInt() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i;
		String s = br.readLine();
		
		try {
			i= Integer.parseInt(s);
		}catch(NumberFormatException nfe) {
			System.err.println("Formato incorrecto. Introduce un numero entero.");
			i = Integer.MAX_VALUE;
		}
		return i;
	}
	
	double leerDouble() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double d;
		String s = br.readLine();
		
		try {
			d= Double.parseDouble(s);
		}catch(NumberFormatException nfe) {
			System.err.println("Formato incorrecto. Decimal '.' o entero.");
			d = Double.MAX_VALUE;
		}
		return d;
	}
	
	float leerFloat() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		float f;
		String s = br.readLine();
		
		try {
			f = Float.parseFloat(s);
		}catch(NumberFormatException nfe) {
			System.err.println("Formato incorrecto. Decimal '.' o entero.");
			f = Float.MAX_VALUE;
		}
		
		return f;
	}
	
	char leerChar() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char c;
		String s = br.readLine();
		c = s.charAt(0);
		
		return c;
	}
	
	void leerSalto() throws IOException{
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	br.readLine();
	}
}
