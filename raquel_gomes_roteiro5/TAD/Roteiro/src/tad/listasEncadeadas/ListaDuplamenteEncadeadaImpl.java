package tad.listasEncadeadas;

import tad.util.Conversor;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	NodoListaDuplamenteEncadeada<T> cabeca;
	NodoListaDuplamenteEncadeada<T> cauda;
	NodoListaDuplamenteEncadeada<T> sentinela;

	public ListaDuplamenteEncadeadaImpl() {
		this.sentinela = new NodoListaDuplamenteEncadeada<T>(null);
		this.sentinela.setAnterior(this.sentinela);
		this.sentinela.setProximo(this.sentinela);
	}
	
	@Override
	public boolean isEmpty() {
		return sentinela.getProximo() == sentinela;
	}

	@Override
	public int size() {
		int contador = 0;
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
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
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		if(auxiliar == sentinela) return null;
		return auxiliar;
	}
	
	/**
	 * Insere um elemento na lista.
	 * Não retorna nada.
	 * @param chave a chave a inserida na lista.
	 */

	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> lastNode;
		NodoListaDuplamenteEncadeada<T> newNode = new NodoListaDuplamenteEncadeada<T>(chave);
		newNode.setProximo(sentinela);
		newNode.setAnterior(sentinela.getAnterior());
		lastNode = sentinela.getAnterior();
		lastNode.setProximo(newNode);
		sentinela.setAnterior(newNode);
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		
	}
	
	/**
	 * Remove um elemento na lista.
	 * Retorna o Node em que a chave foi encontrada.
	 * @param chave a chave a inserida na lista.
	 * @throws ListaVaziaException lança uma exceção quando a lista está cheia.
	 */

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if(this.size() == 0) throw new ListaVaziaException();
		
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> anterior;
		NodoListaDuplamenteEncadeada<T> proximo;
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		
		anterior = (NodoListaDuplamenteEncadeada<T>) auxiliar.getAnterior();
		proximo = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		
		anterior.setProximo(proximo);
		proximo.setAnterior(anterior);
		
		return auxiliar;
	}

	@Override
	public String imprimeEmOrdem() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		while(auxiliar != sentinela) {
			result += auxiliar.getChave();
			if(auxiliar.getProximo() != sentinela) result += ", ";
			auxiliar = auxiliar.getProximo();
		}
		return result;
	}

	@Override
	public String imprimeInverso() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaDuplamenteEncadeada<T> auxiliar = sentinela.getAnterior();
		while(auxiliar != sentinela) {
			result += auxiliar.getChave();
			if(auxiliar.getAnterior() != sentinela) result += ", ";
			auxiliar = auxiliar.getAnterior();
		}
		return result;
	}

	/**
	 * Retorna o node que é o sucessor da chave procurada.
	 * Se não houver sucessor irá retornar null.
	 * @param chave a chave a procurada na lista.
	 */
	
	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		if(this.size()  == 0) return null;
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
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
		if(this.size()  == 0) return null;
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		if (auxiliar.getAnterior() == sentinela) return null;
		return auxiliar.getAnterior();
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
	
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		int i = 0;
		while (auxiliar != sentinela) {
			meuArray[i++] = auxiliar.getChave();
			auxiliar = auxiliar.getProximo();
		}
		return meuArray;
	}

	/**
	 * Insere um elemento no início da lista.
	 * Não retorna nada.
	 * @param elemento elemento a ser inserido no node, consequentemente na lista.
	 */
	
	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> temporaryNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> newNode = new NodoListaDuplamenteEncadeada<T>(elemento);
		newNode.setProximo(sentinela.getProximo());
		temporaryNode.setAnterior(newNode);
		sentinela.setProximo(newNode);
		newNode.setAnterior(sentinela);	
		
	}
	
	/**
	 * Remove o último elemento da lista.
	 * Retorna o node removido, se a lista estiver vazia irá retornar null.
	 */

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		if(this.size() == 0) return null;
		
		NodoListaDuplamenteEncadeada<T> lastNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		NodoListaDuplamenteEncadeada<T> previousNode = (NodoListaDuplamenteEncadeada<T>) lastNode.getAnterior();
		
		previousNode.setProximo(sentinela);
		sentinela.setAnterior(previousNode);
		
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		
		return lastNode;
	}
	
	/**
	 * Remove o primeiro elemento da lista.
	 * Retorna o node removido, se a lista estiver vazia irá retornar null.
	 */

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		if(this.size() == 0) return null;
		
		NodoListaDuplamenteEncadeada<T> nextNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> nextNextNode = (NodoListaDuplamenteEncadeada<T>) nextNode.getProximo();
		
		sentinela.setProximo(nextNextNode);
		nextNextNode.setAnterior(sentinela);
		
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		
		return nextNode;
	}

}
