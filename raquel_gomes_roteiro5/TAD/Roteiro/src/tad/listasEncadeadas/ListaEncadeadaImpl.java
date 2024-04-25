package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.util.Conversor;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T>{
	
	private NodoListaEncadeada<T> cabeca = null;

	@Override
	public boolean isEmpty() {
		if (cabeca == null) return true;
		else return false;
	}

	@Override
	public int size() {
		int contador = 0;
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			auxiliar = auxiliar.getProximo();
			contador++;
		}
		return contador;
	}
	
	/**
	 * Procura um elemento na lista.
	 * Retorna o Node em que a chave foi encontrada.
	 * Se não encontrar a chave irá retornar null.
	 * @param chave a chave a procurada na lista.
	 */

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = auxiliar.getProximo();
		}
		if(naoEncontrado(auxiliar)) return null;
		return auxiliar;
	}
	
	/**
	 * Insere um elemento na lista.
	 * Não retorna nada.
	 * @param chave a chave a inserida na lista.
	 */
	
	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> newNode = new NodoListaEncadeada<>(chave);
		if(cabeca == null) {
			cabeca = newNode;
		} else {
			NodoListaEncadeada<T> anterior = null;
			NodoListaEncadeada<T> auxiliar = cabeca;
			while(auxiliar != null) {
				anterior = auxiliar;
				auxiliar = auxiliar.getProximo();
			}
			anterior.setProximo(newNode);
			newNode.setProximo(auxiliar);
		}	
	}
	
	/**
	 * Remove um elemento na lista.
	 * Retorna o Node em que a chave foi encontrada.
	 * @param chave a chave a inserida na lista.
	 * @throws ListaVaziaException lança uma exceção quando a lista está cheia.
	 */

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if(cabeca == null) throw new ListaVaziaException();
		NodoListaEncadeada<T> auxiliar = cabeca;
		if(cabeca.getChave().compareTo(chave) == 0) {
			this.cabeca = this.cabeca.getProximo();
		} else {
			NodoListaEncadeada<T> anterior = null;
			while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
				anterior = auxiliar;
				auxiliar = auxiliar.getProximo();
			}
			if (naoEncontrado(auxiliar)) return null;
			anterior.setProximo(auxiliar.getProximo());
		}
		return auxiliar;
	}
	
	private boolean naoEncontrado(NodoListaEncadeada<T> item) {
		return item == null;
	}

	/**
	 * Transforma a lista em um array.
	 * Retorna o array com os elementos da lista.
	 * Se a lista estiver vazia irá retornar null;
	 * @param clazz tipo da classe que a lista será convertida.
	 */
	
	@Override
	public T[] toArray(Class<T> clazz) {
		if (this.size() == 0) return null;
		
		Conversor<T> c = new Conversor<T>();
		T[] meuArray = c.gerarArray(clazz, this.size());
		
		NodoListaEncadeada<T> auxiliar = cabeca;
		int i = 0;
		while (auxiliar != null) {
			meuArray[i++] = auxiliar.getChave();
			auxiliar = auxiliar.getProximo();
		}
		return meuArray;
	}

	@Override
	public String imprimeEmOrdem() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			result += auxiliar.getChave();
			if(auxiliar.getProximo() != null) result += ", ";
			auxiliar = auxiliar.getProximo();
		}
		return result;
	}

	@Override
	public String imprimeInverso() {
		if (this.isEmpty()) return "";
		String current = "";
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			String toConcat = ""; 
			if(auxiliar.getProximo() != null) toConcat += ", ";
			toConcat += auxiliar.getChave();
			toConcat += current;
			current = toConcat;
			auxiliar = auxiliar.getProximo();
		}
		return current;
	}
	
	/**
	 * Retorna o node que é o sucessor da chave procurada.
	 * Se não houver sucessor irá retornar null.
	 * @param chave a chave a procurada na lista.
	 */

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = auxiliar.getProximo();
		}
		return auxiliar.getProximo();
	}
	
	/**
	 * Retorna o node que é o antecessor da chave procurada.
	 * Se não houver sucessor irá retornar null.
	 * @param chave a chave a procurada na lista.
	 */

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		NodoListaEncadeada<T> anterior = null;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			anterior = auxiliar;
			auxiliar = auxiliar.getProximo();
		}
		return anterior;
	}

	public NodoListaEncadeada<T> getCabeca() {
		return this.cabeca;
	}
	
	public NodoListaEncadeada<T> getCauda() {
		NodoListaEncadeada<T> auxiliar = cabeca;
		NodoListaEncadeada<T> anterior = null;
		while(auxiliar != null) {
			anterior = auxiliar;
			auxiliar = auxiliar.getProximo();
		}
		return anterior;
	}
	
	/**
	 * Retorna o node da cauda removida.
	 * @throws ListaVaziaException lança uma exceção quando a lista está vazia.
	 */
	
	public NodoListaEncadeada<T> removerCauda() throws ListaVaziaException {
		if(cabeca == null) throw new ListaVaziaException();
		NodoListaEncadeada<T> auxiliar = cabeca;
		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> anteriorACauda = cabeca;
		if(this.size() == 1) {
			this.cabeca = this.cabeca.getProximo();
		} else {
			while(auxiliar != null) {
				anteriorACauda = anterior;
				anterior = auxiliar;
				auxiliar = auxiliar.getProximo();
			}
			anteriorACauda.setProximo(auxiliar);
		}
		return anterior;
	}
	
}
