package alumnosExamen;

import java.io.IOException;
import java.io.EOFException;
import java.io.RandomAccessFile;

public class Alumno {

	private int numero;
	private String nombre;
	private float nota1;
	private float nota2;
	private char apto;
	final int LNombre = 25;
	
	Alumno(int numero, String nombre, float nota1, float nota2, char apto){
		this.numero = numero;
		this.nombre = nombre;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.apto = apto;
	}

	int tamano() {
		return (4+20+2+4+4+2);
	}
	
	boolean leerDeArchivo(RandomAccessFile f) {
		boolean finArchivo = false;
		
		try {
			numero = f.readInt();
			nombre = f.readUTF();
			nota1 = f.readFloat();
			nota2 = f.readFloat();
			apto = f.readChar();
		}catch(EOFException eofe) {
			finArchivo = true;
		}catch(IOException ioe) {
			System.out.println("Error." + ioe.getMessage());
		}
		return finArchivo;
	}
	
	String construirNombre() {
		String aux;
		int relleno;
		nombre.trim();
		relleno = LNombre - nombre.length();
		aux = nombre + blancos(relleno);
		
		return aux;
	}
	
	String blancos(int relleno) {
		char[] blancos = new char[relleno];
		for(int i = 0; i < relleno; i++)
			blancos[i] = ' ';
		String nomBlancos = new String(blancos);
		
		return nomBlancos;
	}
	char conocerAviso() {
		
		if(nota1 >= 4.5 && nota2 >= 4.5)
			return 'S';
		else
			return 'N';
		
	}
	void grabarEnArchivo(RandomAccessFile f) {
		String aux;
		try {
			f.writeInt(numero);
			
			aux = construirNombre();
			f.writeUTF(aux);
			
			f.writeFloat(nota1);
			f.writeFloat(nota2);
			
			apto = conocerAviso();
			f.writeChar(apto);
			
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	void mostrarDatos(int tv){
		switch(tv){
		case 0:
			System.out.println(numero + "\t" + nombre + "\t"+ nota1 + "\t" + nota2 + "\t"+ apto);
			break;
		case 1:
			System.out.println("1.Numero..............: "+numero);
			System.out.println("2.Nombre............: "+nombre);
			System.out.println("3.Nota1..............: "+nota1);
			System.out.println("4.Nota2..............: "+nota2);
			System.out.println("apto..............: "+apto);
			break;
		case 2:
			System.out.println("Numero..............: "+numero);
			System.out.println("Nombre............: "+nombre);
			System.out.println("Nota1..............: "+nota1);
			System.out.println("Nota2..............: "+nota2);
			System.out.println("apto..............: "+apto);
			break;
		case 3:
			System.out.println(numero + "\t" + nombre + "\t"+ nota1 + "\t" + nota2 + "\t"+ apto+ "\t" + (nota1+nota2)/2);
			break;
			
		}
	}
		
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getNota1() {
		return nota1;
	}

	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}

	public float getNota2() {
		return nota2;
	}

	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}

	public char getApto() {
		return apto;
	}

	public void setApto(char apto) {
		this.apto = apto;
	}
	
	int longNombre() {
		return LNombre;
	}
}
