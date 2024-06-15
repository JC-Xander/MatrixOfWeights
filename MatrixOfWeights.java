package ProgramLibraries;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * #Ejercicio optenido de la batalla de programadores 2024
 * 
 * Busca el camino con menos peso en una matriz de numeros de cualquier tamaño. 
 * - Punto de Origen: Elemento en la esquina superio izquierda.
 * - Punto Final: Elemento en la esquina inferior derecha.
 * Para buscar el camino se puede ir de arriba a abajo de izquierda a derecha
 * pero no se puede ir en diagonal
 * 
 * @author J-Xander
 * @version 1.0.0
 * @date 2024/06/14
 */
public class MatrixOfWeights {
	
	
	public static void main(String[] args) {
		// Matriz en la cual se realizara la busqueda.
		int EntradaDeDatos[][] = {
				{1, 1, 10},
				{20, 1, 10},
				{3 ,6, 4},
				{40, 5, 8, 9, 10}
		};
		
	
		// Combirtiendo la matriz de enteros a una matriz de nodos
		nodo matrizNodo[][] = new nodo[EntradaDeDatos.length][];
		
		for(int i = 0; i < matrizNodo.length; i++) {
			nodo arrayNodo[] = new nodo[EntradaDeDatos[i].length];
			for(int j = 0; j < arrayNodo.length; j++) {
				arrayNodo[j] = new nodo(EntradaDeDatos[i][j]);
			}
			matrizNodo[i] = arrayNodo;
		}
		
		ArrayList<nodo> Camino = MatrixOfWeights.busquedaCaminoMenosPeso(matrizNodo);
		
		
		// Imprimiendo el camino con iteradores
		Iterator<nodo> recorrerCamino = Camino.iterator();
		
		while(recorrerCamino.hasNext()) {
			System.out.print(recorrerCamino.next().getPeso());
			if(recorrerCamino.hasNext()) {
				System.out.print(" --> ");
			}	
		}
	}

	/**
	 * Recibe una matriz de nodos y retorna un array list con el camino con menos peso.
	 * @param matrizData Matriz de nodos en la cual se realizara la busqueda
	 * @return la lista de los nodos que conforma en camino.
	 */
	public static ArrayList<nodo> busquedaCaminoMenosPeso(nodo matrizData[][]) {
		
		ArrayList<ArrayList<nodo>> CaminosPosibles = new ArrayList<>();
		
		busquedaRecursiva(matrizData, 0, 0, new ArrayList<nodo>(), CaminosPosibles);
		
		
		ArrayList<nodo> CaminoMenosPesado = new ArrayList<>();
		int menosPeso = 0;
		boolean bandera = true;
		
		for(ArrayList<nodo> camino : CaminosPosibles) {
			int peso = 0;
			for(nodo num : camino) {
				peso += num.getPeso();
			}
			if(bandera) {
				menosPeso = peso;
				CaminoMenosPesado = camino;
				bandera = false;
			}else if(peso < menosPeso) {
				menosPeso = peso;
				CaminoMenosPesado = camino;
			}
		}
		
		return CaminoMenosPesado;
	}

	/**
	 * Realiza una busqueda recursiva de todos los caminos posibles en la matriz.
	 * si el camino llega al destino lo añade a la lista de caminos de lo contrario lo omite.
	 * @param MatrizData Matriz en la cual se realizara la busqueda.
	 * @param NumFila numero de fila del proximo elemento del camino.
	 * @param NumColumna numeor de columna del proximo elemento del camino.
	 * @param Camino Camino que se recorre actualmente.
	 * @param ListaCaminos Lista en la cual se almacenaran los caminos que lleguen al destino sin nodos repetidos.
	 * @return Verdadero si añade el camino a la lista sino retorna falso.
	 */
	@SuppressWarnings("unchecked")
	private static boolean busquedaRecursiva(nodo matrizData[][] ,int numFila, int numColumna, ArrayList<nodo> camino, ArrayList<ArrayList<nodo>> listaCaminos) {
		nodo current = null;
		try {
			current = matrizData[numFila][numColumna];
			for(nodo elemento: camino) {
				if(current == elemento) {
					return false;
				}
			}
			camino.add(current);
		}catch (Exception e) {
			return false;
		}
		
		
		if(current == matrizData[matrizData.length - 1][(matrizData[matrizData.length - 1].length) - 1]) {
			listaCaminos.add(camino);
			return true;
		}
			
		busquedaRecursiva(matrizData, numFila + 1, numColumna, (ArrayList<nodo>)camino.clone(), listaCaminos);
		busquedaRecursiva(matrizData, numFila - 1, numColumna, (ArrayList<nodo>)camino.clone(), listaCaminos);
		busquedaRecursiva(matrizData, numFila, numColumna + 1, (ArrayList<nodo>)camino.clone(), listaCaminos);
		busquedaRecursiva(matrizData, numFila, numColumna - 1, (ArrayList<nodo>)camino.clone(), listaCaminos);
		
		return true;
	}
}

/**
 * Objeto que nos permite almacenar un nuemero y asi podamos diferenciar por que nodos 
 * a pasado el camino y asi evitar repetidos.
 */
class nodo implements Cloneable{
	
	private int peso;
	
	nodo(int peso){
		this.peso = peso;
	}
	
	public int getPeso() {
		return this.peso;
	}
	
	@Override
    protected nodo clone() {
		try {
			nodo cloned = (nodo) super.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.peso);
	}
}
