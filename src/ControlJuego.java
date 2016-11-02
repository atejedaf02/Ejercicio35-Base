import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posición guarda el número -1 Si no hay
 * una mina, se guarda cuántas minas hay alrededor. Almacena la puntuación de
 * la partida
 * 
 * @author �lvaro Tejeda
 *
 */
public class ControlJuego {

	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	public int[][] tablero;
	private int puntuacion;

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
	}

	/**
	 * Método para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habrá inicializado con tantas minas como
	 *        marque la variable MINAS_INICIALES. El resto de posiciones que no
	 *        son minas guardan en el entero cuántas minas hay alrededor de la
	 *        celda
	 */
	public void inicializarPartida() {
		// Borro del tablero la información que pudiera haber anteriormente
		// (los pongo todos a cero):

		// Me creo LADO_TABLERO*LADO_TABLERO números en un array list, uno para
		// cada una de las posiciones del tablero:

		// Saco 20 posiciones sin repetir del array y les coloco una mina en el
		// tablero:

		// Calculo para todas las posiciones que no tienen minas, cuántas minas
		// hay alrededor.

		// Pongo la puntuación a cero:

		puntuacion = 0;

		for (int i = 0; i < MINAS_INICIALES; i++) { // hacemos un for de 20

			boolean mina = false;

			while (!mina) { // mientras mina sea falso se repite

				int x = (int) (Math.random() * 10); // creamos posiciones
													// aleatorias
				int y = (int) (Math.random() * 10);

				if (tablero[x][y] != MINA) { // si lo que hay dentro de la
												// casilla es no es una mina se
												// coloca una y mina se cambia a
												// true para salirse del bucle
					mina = true;
					tablero[x][y] = MINA;
				}

			}

		}

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if(tablero[i][j]!=MINA){ //comprobamos que no es una mina para asi asignarle la cantidad de minas que tiene a su alrededor
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}

		depurarTablero();

	}

	/**
	 * Cálculo de las minas adjuntas: Para calcular el número de minas tenemos
	 * que tener en cuenta que no nos salimos nunca del tablero. Por lo tanto,
	 * como mucho la i y la j valdrán LADO_TABLERO-1. Por lo tanto, como mucho
	 * la i y la j valdrán como poco 0.
	 * 
	 * @param i:
	 *            posición verticalmente de la casilla a rellenar
	 * @param j:
	 *            posición horizontalmente de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 */
	private int calculoMinasAdjuntas(int i, int j) {
		int cont = 0;
		int imin = i, imax = i; //le asignamos la posicion inicial
		int jmin = j, jmax = j;
		
		if (i > 0) { imin = i-1;} //comprobamos que no se pase de la i de 0

		if (i < LADO_TABLERO-1) { imax = i+1;} //comprobamos que no se pase i del max

		if (j > 0) { jmin = j-1;} //comprobamos que no se pase de la j de 0

		if (j < LADO_TABLERO-1) { jmax = j+1;} //comprobamos que no se pase j del max

		for (int k = imin; k <= imax; k++) { //recorremos solo las celdas de alrededor
			for (int k2 = jmin; k2 <= jmax; k2++) {
				if (tablero[k][k2] == MINA) { //comprobamos si tiene una mina y le sumamos 1 al contador

					cont++;
				}
			}
		}
		
		return cont;
	}

	/**
	 * Método que nos permite
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado
	 *      por el GestorJuego. Por lo tanto siempre sumaremos puntos
	 * @param i:
	 *            posición verticalmente de la casilla a abrir
	 * @param j:
	 *            posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	 public boolean abrirCasilla(int i, int j){
		 
		 if(tablero[i][j]==MINA){ //si es una mina falso
			 puntuacion++;
			 return false;
		 }
		 
		 return true;
	 }

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto
	 * todas las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	 public boolean esFinJuego(){
		 if(puntuacion==80){
			 return true;
		 }
		 return false;
	 }

	/**
	 * Método que pinta por pantalla toda la información del tablero, se
	 * utiliza para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: " + puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una
	 * celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace
	 *      falta calcularlo, símplemente consultarlo
	 * @param i
	 *            : posición vertical de la celda.
	 * @param j
	 *            : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la
	 *         celda
	 */
	// public int getMinasAlrededor(int i, int j) {
	// }

	/**
	 * Método que devuelve la puntuación actual
	 * 
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

}
