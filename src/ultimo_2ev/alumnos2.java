package ultimo_2ev;

import java.util.Scanner;
import java.util.InputMismatchException;

public class alumnos2 {
	private int numero;
	private String nombre;
	private char sexo;
	private double nota1, nota2, nota3;
	
	alumnos2(){};
	alumnos2(int numero, String nombre, char sexo, double nota1, double nota2, double nota3){
		this.numero = numero;
		this.nombre = nombre;
		this.sexo = sexo;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
	}

	
	public static void altas(Scanner sc, alumnos2[]alumn, int tam) {
		int numero;
		String nombre;
		char sexo;
		double nota1, nota2, nota3;
		boolean existe = false;
		
		for(int i = 0; i < tam; i++) {
			do {
				System.out.println("Inserte numero de alumno: ");
				try {
					numero = sc.nextInt();
					existe = existeNumero(alumn, tam, numero);
					if(existe)
						System.out.println("El numero ya existe, elija otro.");
				}catch(InputMismatchException ime) {
					numero = Integer.MAX_VALUE;
					System.err.println("El numero no puede ser una letra ni un caracter especial.");
				}finally {
					sc.nextLine();
				}
			}while(existe || numero == Integer.MAX_VALUE);
			
			do {
				System.out.println("Introduce nombre: ");
				nombre = sc.nextLine().trim();
			}while(nombre.length()>15 || nombre.isEmpty());
			
			do {
				
				System.out.println("Introduce sexo: ");
				sexo = sc.next().trim().toUpperCase().charAt(0);
				if("VM".indexOf(sexo) < 0)
					System.out.println("El sexo solo puede ser v o m.");
			}while("VM".indexOf(sexo) < 0);
			
			do {
				System.out.println("Introduce la 1ª nota: ");
				try {
					nota1 = sc.nextDouble();
				}catch(InputMismatchException ime) {
					nota1 = Integer.MAX_VALUE;
					System.err.println("El numero no puede ser una letra ni un caracter especial.");
				}finally {
					sc.nextLine();
				}
			}while(nota1 < 1 || nota1 >10);
			
			do {
				System.out.println("Introduce la 2ª nota: ");
				try {
					nota2 = sc.nextDouble();
				}catch(InputMismatchException ime) {
					nota2 = Integer.MAX_VALUE;
					System.err.println("El numero no puede ser una letra ni un caracter especial.");
				}finally {
					sc.nextLine();
				}
			}while(nota2 < 1 || nota2 >10);
			
			do {
				System.out.println("Introduce la 3ª nota: ");
				try {
					nota3 = sc.nextDouble();
				}catch(InputMismatchException ime) {
					nota3 = Integer.MAX_VALUE;
					System.err.println("El numero no puede ser una letra ni un caracter especial.");
				}finally {
					sc.nextLine();
				}
			}while(nota3 < 1 || nota3 >10);
			
			alumn[i] = new alumnos2(numero, nombre, sexo, nota1, nota2, nota3);
		}
	}
	
	public static void listado(alumnos2[]alumn, int tam) {
		if(!arrayInicializado(alumn)) return;
		
		double notaMedia;
		
		for(int i = 0; i < tam; i++) {
			notaMedia = alumn[i].calcularNotaMedia();
			System.out.print(alumn[i].getNumero() + "\t\t" + alumn[i].getNombre()+ "\t\t" + alumn[i].getSexo()+ "\t\t" + alumn[i].getNota1()+ "\t\t" + alumn[i].getNota2() + "\t\t" + alumn[i].getNota3() + "\t\t" + notaMedia);
			System.out.println();
		}
	}
	
	public double calcularNotaMedia() {
	    return (this.nota1 + this.nota2 + this.nota3) / 3;
	}
	
	public static void ordenarNombre(alumnos2[]alumn, int tam) {
		if(!arrayInicializado(alumn)) return;
		alumnos2 alumnoAux;
		for(int i = 0; i < tam - 1; i++) {
			for(int f = i + 1; f < tam; f++) {
				if(alumn[i].getNombre().toLowerCase().compareToIgnoreCase(alumn[f].getNombre().toLowerCase()) > 0) {
					alumnoAux = alumn[i];
					alumn[i] = alumn[f];
					alumn[f] = alumnoAux;
				}
			}
		}
	}
	
	public static void ordenarNumero(alumnos2[]alumn, int tam) {
		if(!arrayInicializado(alumn)) return;
		alumnos2 alumnoAux;
		for(int i = 0; i < tam - 1; i++) {
			for(int f = i+1; f < tam; f++) {
				if(alumn[i].getNumero() > alumn[f].getNumero()) {
					alumnoAux = alumn[i];
					alumn[i] = alumn[f];
					alumn[f] = alumnoAux;
				}
			}
		}
	}
	public static void busquedaNombre(Scanner sc, alumnos2[]alumn, int tam) {
		if(!arrayInicializado(alumn)) return;
		String nombre;
		int contador = 0;
		do {
			System.out.println("Introduce nombre a buscar: ");
			nombre = sc.nextLine().toLowerCase();
		}while(nombre.length() > 15 || nombre.isEmpty());
		
		for(int i = 0; i < tam; i++) {
			if(alumn[i].getNombre().equalsIgnoreCase(nombre)) {
				System.out.println(alumn[i].getNombre() + "\t\t" + alumn[i].getNumero() + "\t\t" + alumn[i].getSexo() + "\t\t" + alumn[i].getNota1() + "\t\t" + alumn[i].getNota2() + "\t\t" + alumn[i].getNota3());
				break;
			} else {
				contador++;
			}
		}
		if(contador==tam)
			System.out.println("No existe el alumno " + nombre + " en nuestra base.");
	}

	public static void modificarNota(Scanner sc, alumnos2[] alumn, int tam) {
		if (!arrayInicializado(alumn))
			return;

		int numeroAl = 0, numeroNota = 0, i = 0, indice = 0;
		double notaModificada = 0;
		boolean existe = false;
		char confirmacion = ' ', cambiarNota = ' ', cambiarAl = ' ';

		do {
			existe = false;
			i = 0;
			indice = 0;
			do {
				System.out.println("Introduce numero de alumno al que le quieres modificar la nota: ");
				try {
					numeroAl = sc.nextInt();
					existe = existeNumero(alumn, tam, numeroAl);
					if (existe) {
						for (; i < tam; i++) {
							if (alumn[i].getNumero() == numeroAl) {
								indice = i;
								break;
							}
						}
					} else
						System.out.println("El numero no coincide con ningún alumno.");
				} catch (InputMismatchException ime) {
					numeroAl = Integer.MAX_VALUE;
					System.err.println("La nota no puede ser una letra ni un carecter especial.");
				} finally {
					sc.nextLine();
				}
			} while (numeroAl == Integer.MAX_VALUE || !existe);

			System.out.println("El alumno número: " + alumn[indice].getNumero() + " tiene las siguientes notas: ");
			System.out.println("1. " + alumn[indice].getNota1() + "\n2. " + alumn[indice].getNota2() + "\n3. "
					+ alumn[indice].getNota3());

			do {
				do {
					System.out.println("Introduce el número de nota que quieres modificar entre 1 y 3: ");
					try {
						numeroNota = sc.nextInt();
					} catch (InputMismatchException ime) {
						numeroNota = Integer.MAX_VALUE;
						System.err.println("El numero de nota no puede ser una letra ni un carecter especial.");
					} finally {
						sc.nextLine();
					}
				} while (numeroNota < 1 && numeroNota > 3);

				do {

					System.out.println("Introduce la nueva nota del alumno : ");
					try {
						notaModificada = sc.nextDouble();
						if (notaModificada >= 1 && notaModificada <= 10) {
							do {
								System.out.print("Introduce s para confirmar la modificacion en caso contrario n: ");
								confirmacion = sc.next().toLowerCase().charAt(0);
							} while (confirmacion != 's' && confirmacion != 'n');
							if (confirmacion == 's') {
								switch (numeroNota) {
								case 1:
									alumn[indice].setNota1(notaModificada);
									break;
								case 2:
									alumn[indice].setNota2(notaModificada);
									break;
								case 3:
									alumn[indice].setNota3(notaModificada);
									break;
								default:
									System.err.println("Error inesperado.");
								}
								System.out.println("¡Nota modificada correctamente!");
							}
						} else {
							System.err.println("La nota debe estar entre 1 y 10.");
						}

					} catch (InputMismatchException ime) {
						notaModificada = Double.MAX_VALUE;
						System.err.println("La nota no puede ser una letra ni un carecter especial.");
					} finally {
						sc.nextLine();
					}

					do {
						System.out.println("Quieres cambiar otra nota? Teclea s para si n en caso contrario: ");
						cambiarNota = sc.next().toLowerCase().charAt(0);
					} while (cambiarNota != 's' && cambiarNota != 'n');

				} while (notaModificada == Double.MAX_VALUE);
			} while (cambiarNota == 's');
			do {
				System.out.println("Quieres cambiar notas de otro alumno? Teclea s para si n en caso contrario: ");
				cambiarAl = sc.next().toLowerCase().charAt(0);
			} while (cambiarAl != 's' && cambiarAl != 'n');
		} while (cambiarAl == 's');
	}

	public static void fin() {
		
		System.out.println("Fin del programa.");
	}
	
	public static boolean existeNumero(alumnos2[] alumn, int tam, int numero) {
		
		for(int i = 0; i < tam; i++) {
			if(alumn[i] != null && alumn[i].getNumero() == numero) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean arrayInicializado(alumnos2[] alumn) {
	    for (alumnos2 alumnoIndividual : alumn) {
	        if (alumnoIndividual != null) {
	            return true; // El array está inicializado y tiene datos válidos
	        }
	    }

	    System.out.println("El array está inicializado, pero no contiene datos. Por favor, utiliza la opción 1 del menú primero.");
	    return false;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public char getSexo() {
		return this.sexo;
	}
	
	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}
	
	public double getNota1() {
		return this.nota1;
	}
	
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}
	
	public double getNota2() {
		return this.nota2;
	}
	
	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}
	
	public double getNota3() {
		return this.nota3;
	}
}
