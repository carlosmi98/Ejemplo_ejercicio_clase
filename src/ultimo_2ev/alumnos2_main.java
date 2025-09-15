package ultimo_2ev;

import java.util.Scanner;

import java.util.InputMismatchException;

public class alumnos2_main {
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		int op = 0, tam = 3;
		alumnos2[] alumn = new alumnos2[tam];
		
		do {
			op = menu(sc);
			switch(op) {
			case 1:
				alumnos2.altas(sc, alumn, tam);
				break;
			case 2:
				alumnos2.listado(alumn, tam);
				break;
			case 3:
				alumnos2.ordenarNombre(alumn, tam);
				break;
			case 4:
				alumnos2.ordenarNumero(alumn, tam);
				break;
			case 5:
				alumnos2.busquedaNombre(sc, alumn, tam);
				break;
			case 6:
				alumnos2.modificarNota(sc, alumn, tam);
				break;
			case 7:
				alumnos2.fin();
				break;
			}
		}while(op!=7);
	}
	
	static int menu(Scanner sc) {
		int op;
		
		System.out.println("   Menu");
		System.out.println("\t1. Altas.");
		System.out.println("\t2. Listado.");
		System.out.println("\t3. Ordenar por nombre.");
		System.out.println("\t4. Ordenar por número.");
		System.out.println("\t5. Busqueda por nombre.");
		System.out.println("\t6. Modificar nota.");
		System.out.println("\t7. Fin");
		
		
		do {
			System.out.print("Introduce opción del menú: ");
			try {
				op=sc.nextInt();
			}catch(InputMismatchException ime) {
				op = Integer.MAX_VALUE;
				System.err.println("No puedes introducir letras ni caracteres especiales.");
			} finally {
				sc.nextLine();
			}
		}while(op < 1 || op >7);
		return op;
	}
}
