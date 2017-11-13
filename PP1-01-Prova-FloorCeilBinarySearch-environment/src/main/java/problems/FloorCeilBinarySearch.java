package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer retorno = null;
		if (array != null && x != null && array.length > 0) {
			retorno = buscaBinariaFloor(array, x, 0, array.length);
		}
		return retorno;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer retorno = null;
		if (array != null && x != null && array.length > 0) {
			retorno = buscaBinariaCeil(array, x, 0, array.length);
		}
		return retorno;
	}
	private Integer buscaBinariaCeil(Integer[] array, Integer x, int inicio, int fim) {
		Integer retorno = null;
		if (array != null && x != null && inicio >= 0 && fim <= array.length && fim > 0) {
			int meio = (inicio + fim) / 2;
			
			if (array[array.length - 1] < x) {
				retorno = array[array.length - 1];
			}
			else if (array[0] > x) {
				retorno = array[0];
			}
			else if (array[meio] == x) {
				retorno = array[meio];
			}
			else if (array[meio] < x && array[meio + 1] > x) {
				retorno = array[meio + 1];
			}
			else if (array[meio] > x && array[meio - 1] < x) {
				retorno = array[meio];
			}
			else if (array[meio] > x) {
				retorno = buscaBinariaCeil(array, x, inicio, meio - 1);
			}
			else if (array[meio] < x) {
				retorno = buscaBinariaCeil(array, x, meio + 1, fim);
			}
		}
		return retorno;
	}
	private Integer buscaBinariaFloor(Integer[] array, Integer x, int inicio, int fim) {
		Integer resultado = null;
		if (array != null && x != null && inicio >= 0 && fim <= array.length && fim > 0 && inicio < array.length) {
			int meio = (inicio + fim) / 2;
			
			if (array[array.length - 1]  < x) {
				resultado = array[array.length - 1];
			}
			else if (array[meio] == x) {
				resultado = array[meio];
			}
			else if (meio > 0 && array[meio] > x && array[meio-1] < x) {
				resultado = array[meio-1];
			}
			else if(array[meio] > x) {
				resultado = buscaBinariaFloor(array, x, inicio, meio - 1);
			}
			else if (array[meio] < x) {
				resultado = buscaBinariaFloor(array, x, meio + 1, fim);
			}
		}
		return resultado;
	}
}
