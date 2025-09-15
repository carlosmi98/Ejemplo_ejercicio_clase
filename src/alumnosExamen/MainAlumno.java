package alumnosExamen;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MainAlumno {
	static final String rutaA = "C:\\Users\\carlo\\Desktop\\Nueva carpeta\\random";

	public static void main(String[] args) throws IOException {
		MainAlumno ma = new MainAlumno();
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");
		fich.close();

		char op;

		do {
			op = ma.menu();
			switch (op) {
			case 'A':
				ma.altas();
				break;
			case 'B':
				ma.bajas();
				break;
			case 'L':
				op = ma.menuListado();
				switch(op) {
					case 'G':
						ma.listadoGeneral();
						break;
					case 'A':
						ma.listadoAptos();
						break;
					case 'S':
						ma.listadoNoAptos();
						break;
					case 'V':
						break;
				}
				break;
			case 'F':
				ma.fin();
				break;
			}
		} while (op != 'F');
	}

	char menu() throws IOException {
		Teclado t = new Teclado();
		char op;

		System.out.println("   Menu");
		System.out.println("\tA. Altas.");
		System.out.println("\tB. Bajas.");
		System.out.println("\tL. Listados.");
		System.out.println("\tF. Fin.");

		do {
			System.out.println("Teclee opcion 1-4: ");
			try {
				op = Character.toUpperCase(t.leerChar());
			}catch(Exception e){
				op = ' ';
			}
		} while ("ABLF".indexOf(op) == -1 || op == ' ');

		return op;
	}

	void altas() throws IOException {
		Teclado t = new Teclado();
		Alumno alV = new Alumno(0, " ", 0, 0, ' ');
		Alumno al = new Alumno(0, " ", 0, 0, ' ');
		int numero;
		String nombre;
		float nota1, nota2;
		char confirmarA;
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");

		System.out.println("\n\tALTAS\n");
		do {
			System.out.println("Número (0 para finalizar): ");
			numero = t.leerInt();
		} while (numero == Integer.MAX_VALUE);

		while (numero != 0) {
			fich.seek(numero * alV.tamano());
			alV.leerDeArchivo(fich);

			if (alV.getNumero() != 0) {
				System.out.println("\n\tEl registro ya existe.\n");
			} else {
				do {
					System.out.println("Nombre: ");
					nombre = t.leerString();
				} while (nombre.length() > 25 || nombre.isEmpty());
				do {
					System.out.println("Nota1: ");
					nota1 = t.leerFloat();
				} while (nota1 < 1 || nota1 > 10);
				do {
					System.out.println("Nota2: ");
					nota2 = t.leerFloat();
				} while (nota2 < 1 || nota2 > 10);

				if (numero * al.tamano() > fich.length()) {
					fich.seek(fich.length());
				}
				while (numero * al.tamano() > fich.length())
					alV.grabarEnArchivo(fich);
				al = new Alumno(numero, nombre, nota1, nota2, ' ');
				fich.seek(numero * al.tamano());
				al.grabarEnArchivo(fich);

			}
			do {
				System.out.println("Número (0 para finalizar): ");
				numero = t.leerInt();
			} while (numero == Integer.MAX_VALUE);

			alV = new Alumno(0, " ", 0, 0, ' ');
		}
		fich.close();
	}

	void bajas() throws IOException {
		Teclado t = new Teclado();
		Alumno al = new Alumno(0, " ", 0, 0, ' ');
		int numBus;
		char confirmar;
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");
		
		do {
			do {
				System.out.println("Introduce número a buscar: ");
				numBus = t.leerInt();
			} while (numBus == Integer.MAX_VALUE);
			fich.seek(numBus * al.tamano());
			al.leerDeArchivo(fich);
			if (al.getNumero() == 0) {
				System.out.println("El alumno no existe.");
				return;
			} else {
				al.mostrarDatos(2);
				do {
					System.out.println("Confirmar borrado? s/n: ");
					confirmar = Character.toLowerCase(t.leerChar());
				} while ("sn".indexOf(confirmar) == -1);
				
				if (confirmar == 's') {
					fich.seek(al.getNumero() * al.tamano());
					al = new Alumno(0, " ", 0, 0, ' ');
					al.grabarEnArchivo(fich);
					System.out.println("Alumno borrado correctamente.");
				}
			}
			do {
				System.out.println("Borrar otro registro? ");
				confirmar = Character.toLowerCase(t.leerChar());
			} while ("sn".indexOf(confirmar) == -1);

		} while (confirmar == 's');
		fich.close();
	}
	
	char menuListado() throws IOException {
		Teclado t = new Teclado();
		char op;

		System.out.println("   Menu");
		System.out.println("\tG. Listado general.");
		System.out.println("\tA. Listado aptos.");
		System.out.println("\tS. Listado no aptos.");
		System.out.println("\t3. Menu principal.");

		do {
			System.out.println("Teclee opcion 1-3: ");
			op = Character.toUpperCase(t.leerChar());
		} while ("GASV".indexOf(op) == -1);

		return op;
	}
	
	void listadoGeneral() throws IOException{
		Alumno al = new Alumno(0, " ", 0, 0, ' ');
		Teclado t = new Teclado();
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");
		final int LINEAS = 5;
		int contA = 0, contNA = 0, contAF = 0, contNAF = 0, contL = 0, pag = 1;
		boolean fin = false;
		System.out.println("ghdf");
		do {
			fin = al.leerDeArchivo(fich);
		}while(al.getNumero()==0 && !fin);
		do {
			if(!fin){
				System.out.println("\n\n\tLISTADO PERSONAS\tPag.: "+ pag++ +"\n\t================\n");
				System.out.println("\nNúmero\tNombre\t\t\tnota1\tnota2\tapto\tmedia");
				System.out.println("---------------------------------------");
				contL=0;
			}
			
			while(contL < LINEAS && !fin) {
				if(al.getNumero() != 0) {
					contL++;
					if(al.getApto() == 'S') {
						al.mostrarDatos(3);
						contA++;
						contAF++;
					}else {
						al.mostrarDatos(3);
						contNA++;
						contNAF++;
					}
				}
				fin = al.leerDeArchivo(fich);
			}
			if (contL==LINEAS || fin){
				System.out.println("\nTotal aprobados en esta pagina: " + contA);
				System.out.println("\nTotal suspensos en esta pagina: " + contNA);
				System.out.println("\n\tPulse <Intro> para continuar....");
				t.leerSalto();
				}
//			if (contL!=LINEAS){
//				System.out.println("\nTotal aprobados en esta pagina: " + contA);
//				System.out.println("\nTotal suspensos en esta pagina: " + contNA);
//			}
			contL = 0;
			contA = 0;
			contNA = 0;
		}while(!fin);
		System.out.println("\n\nFin de archivo:");
		System.out.println("\nTotal aprobados: " + contAF);
		System.out.println("Total suspensos: " + contNAF);
		System.out.println("\nPulsa <INTRO> para siguiente página.");
		t.leerSalto();
		
	}
	
	void listadoAptos() throws IOException{
		Alumno al = new Alumno(0, " ", 0, 0, ' ');
		Teclado t = new Teclado();
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");
		final int LINEAS = 5;
		int contA = 0, contL = 0, pag = 1;
		boolean fin = false;
		float media = 0;

		do {
			fin = al.leerDeArchivo(fich);
		}while(al.getNumero()==0 && !fin);
		do {
			if(!fin){
				System.out.println("\n\n\tLISTADO PERSONAS APROBADAS\tPag.: "+ pag++ +"\n\t================\n");
				System.out.println("\nNúmero\tNombre\t\t\tnota1\tnota2\tapto\tmedia");
				System.out.println("---------------------------------------");
				contL=0;
			}
			
			while(contL < LINEAS && !fin) {
				if(al.getNumero() != 0) {
					if(al.getApto() == 'S') {
						al.mostrarDatos(3);
						contA++;
						contL++;
						media+=(al.getNota1() + al.getNota2())/2;
					}
				}
				fin = al.leerDeArchivo(fich);
			}
			if (contL==LINEAS){
				System.out.println("\n\tPulse <Intro> para continuar....");
				t.leerSalto();
				}
			contL = 0;
		}while(!fin);
		System.out.println("La media global es de: " + media/contA);
		System.out.println("\n\nFin de archivo:");
		System.out.println("\nPulsa <INTRO> para siguiente página.");
		t.leerSalto();
		
	}
	
	void listadoNoAptos() throws IOException {
		Alumno al = new Alumno(0, " ", 0, 0, ' ');
		Teclado t = new Teclado();
		RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "r");
		final int LINEAS = 5;
		int contA = 0, contL = 0, pag = 1;
		boolean fin = false;
		float media = 0;
		
		do {
			fin = al.leerDeArchivo(fich);
		} while (al.getNumero() == 0 && !fin);
		do {
			if (!fin) {
				System.out.println("\n\n\tLISTADO PERSONAS SUSPENSAS\tPag.: " + pag++ + "\n\t================\n");
				System.out.println("\nNúmero\tNombre\t\t\tnota1\tnota2\tapto\tmedia");
				System.out.println("---------------------------------------");
				contL = 0;
			}

			while (contL < LINEAS && !fin) {
				if (al.getNumero() != 0) {
					if (al.getApto() == 'N') {
						al.mostrarDatos(3);
						contA++;
						contL++;
						media += (al.getNota1() + al.getNota2()) / 2;
					}
				}
				fin = al.leerDeArchivo(fich);
			}
			if (contL == LINEAS) {
				System.out.println("\n\tPulse <Intro> para continuar....");
				t.leerSalto();
			}
			contL = 0;
		} while (!fin);
		System.out.println("La media global es de: " + media / contA);
		System.out.println("\n\nFin de archivo:");
		System.out.println("\nPulsa <INTRO> para siguiente página.");
		t.leerSalto();

	}

	void fin() {
		System.out.println("Fin del programa.");
	}
}

/*
	void altas() throws IOException{
Teclado t = new Teclado();
Alumno alV = new Alumno(0, " ",0, 0, ' ');
Alumno al = new Alumno(0, " ",0, 0, ' ');
int numero;
String nombre;
float nota1, nota2;
char confirmarA;
RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "rw");

System.out.println("\n\tALTAS\n");
do {
	System.out.println("Número (0 oara finalizar): ");
	numero = t.leerInt();
}while(numero == Integer.MAX_VALUE);

while(numero != 0) {
	fich.seek(numero * alV.tamano());
	alV.leerPersona(fich);
	
	if(alV.getNumero() != 0) {
		System.out.println("\n\tEl registro ya existe.\n");
	}else{
		do {
			System.out.println("Nombre: ");
			nombre = t.leerString();
		}while(nombre.length() > 20 || nombre.isEmpty());
		do {
			System.out.println("Nota1: ");
			nota1 = t.leerFloat();
		}while(nota1 < 1 || nota1 > 10);
		do {
			System.out.println("Nota2: ");
			nota2 = t.leerFloat();
		}while(nota2 < 1 || nota2 > 10);
		
		do {
			System.out.println("Confirmar alta s/n: ");
			confirmarA = Character.toLowerCase(t.leerChar());
		}while("sn".indexOf(confirmarA) == -1);
		if(confirmarA == 's') {
			al = new Alumno(numero, nombre, nota1, nota2, ' ');
			if(numero * al.tamano() > fich.length()) {
				fich.seek(fich.length());
			}
			while(numero * al.tamano() > fich.length())
				alV.grabarArchivo(fich);
			fich.seek(numero * al.tamano());
			al.grabarArchivo(fich);
		}
	}
	al.verDatosPersona(0);
	do {
		System.out.println("Número (0 oara finalizar): ");
		numero = t.leerInt();
	}while(numero == Integer.MAX_VALUE);
	
	alV = new Alumno(0, " ",0, 0, ' ');
}
fich.close();
}


	void listadoNoAptos() throws IOException {
		Teclado teclado = new Teclado();
        RandomAccessFile fich = new RandomAccessFile(rutaA + "AlumnosR.dat", "r");
        Alumno al = new Alumno(0, "", 0, 0, ' ');
        final int LINEAS = 5;
        int contL = 0, pagina = 0;
        boolean fin = false;

        do {
            if (!fin) {
                System.out.println("\nLISTADO\t\t\tPágina " + ++pagina);
                System.out.println("Número \t Nombre \t\t\tNota1\t\tNota2\tApto\tMedia");
            }
            while ((contL < LINEAS) && !fin) {
                if (al.getNumero() != 0) {
                    if (al.getApto() == 'N') {
                        contL++;
                        al.verDatosPersona(3);
                    }
                }
                fin = al.leerPersona(fich);

                if (contL == LINEAS || fin) {
                    System.out.println("\nPulsa <Intro> para continuar....");
                    teclado.leerSalto();
                }
            }
            contL = 0;
        } while (!fin);
        System.out.println("\n\tFIN DEL LISTADO\n\t================");
        System.out.println("\nPulsa <Intro> para continuar....");
        teclado.leerSalto();
        fich.close();
    }
*/
