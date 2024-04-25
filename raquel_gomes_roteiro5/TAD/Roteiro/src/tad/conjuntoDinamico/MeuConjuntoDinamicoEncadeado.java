package tad.conjuntoDinamico;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

	ListaEncadeadaImpl<Integer> conjunto_dinamico;
	
	public MeuConjuntoDinamicoEncadeado() {
		this.conjunto_dinamico = new ListaEncadeadaImpl<Integer>();
	}
	
	/* Insere um item no conjunto. Aumenta o tamanho dinamicamente do conjunto no processo. Não retorna valor 
	e o parametro item que será inserido no conjunto */
	
	@Override
	public void inserir(Integer item) {
		this.conjunto_dinamico.insert(item);
	}

	/* Remove um elemento do conjunto. Reduz o tamanho dinamicamente do conjunto no processo, retornando Integer
	que foi removido. Item será procurado no conjunto para ser retirado, o @throws Exception quando o valor 
	for procurado, lança uma exceção */
	
	@Override
	public Integer remover(Integer item) throws Exception {
		try {
			return this.conjunto_dinamico.remove(item).getChave();
		} catch (ListaVaziaException e) {
			throw new Exception("Item não encontrado."); 
		}
	}
	
	/* Retorna o Integer anterior ao valor procurado. Retorna null quando esse valor não possui um antecessor.
	  O item  será o valor a ser procurado no conjunto. @throws Exception lança uma exceção quando o
	   conjunto está vazio ou quando o valor não foi encontrado. */

	@Override
	public Integer predecessor(Integer item) throws Exception {
		NodoListaEncadeada<Integer> antecessor = null;
		NodoListaEncadeada<Integer> aux = this.conjunto_dinamico.getCabeca();
		while(aux != null && aux.getChave() != item) {
			antecessor = aux;
			aux = aux.getProximo();
		}
		
		if(naoFoiEncontrado(aux)) throw new Exception("Item não encontrado.");
		else {
			return naoFoiEncontrado(antecessor) ? null : antecessor.getChave();
		}
	}
	
	/* Retorna o Integer sucessor ao valor procurado. Retorna null quando esse valor não 
	possui um sucessor. @param item o valor a ser procurado no conjunto.@throws Exception lança uma exceção quando o conjunto está vazio.	 */

	@Override
	public Integer sucessor(Integer item) throws Exception {
		NodoListaEncadeada<Integer> aux = this.conjunto_dinamico.search(item);
		NodoListaEncadeada<Integer> posterior = aux.getProximo();
		if(naoFoiEncontrado(aux)) throw new Exception("Item não encontrado.");
		else {
			return naoFoiEncontrado(posterior) ? null : posterior.getChave();
		}
	}

	@Override
	public int tamanho() {
		return this.conjunto_dinamico.size();
	}
	
	/* Retorna o Integer procurado. item o valor a ser procurado no conjunto.  @throws Exception lança 
	uma exceção quando o conjunto está vazio ou quando o valor não foi encontrado.*/

	@Override
	public Integer buscar(Integer item) throws Exception {
		NodoListaEncadeada<Integer> aux = this.conjunto_dinamico.search(item);
		if(naoFoiEncontrado(aux)) throw new Exception("Item não encontrado.");
		else return aux.getChave();
	}

	/*Retorna o menor valor do conjunto. @throws Exception lança uma 
	exceção quando o conjunto está vazio. */
	
	@Override
	public Integer minimum() throws Exception {
		if (this.conjunto_dinamico.size() == 0) throw new Exception("Conjunto vázio");
		NodoListaEncadeada<Integer> min = new NodoListaEncadeada<>(Integer.MAX_VALUE);
		NodoListaEncadeada<Integer> aux = this.conjunto_dinamico.getCabeca();
		while(aux != null) {	
			if(aux.getChave() < min.getChave()) min = aux;
			aux = aux.getProximo();
		}
		return min.getChave();
	}
	
	/* Retorna o maior valor do conjunto. @throws Exception lança uma
	 exceção quando o conjunto está vazio. */

	@Override
	public Integer maximum() throws Exception {
		if (this.conjunto_dinamico.size() == 0) throw new Exception("Conjunto vázio");
		NodoListaEncadeada<Integer> max = new NodoListaEncadeada<>(Integer.MIN_VALUE);
		NodoListaEncadeada<Integer> aux = this.conjunto_dinamico.getCabeca();
		while(aux != null) {	
			if(aux.getChave() > max.getChave()) max = aux;
			aux = aux.getProximo();
		}
		return max.getChave();
	}
	
	private boolean naoFoiEncontrado(NodoListaEncadeada<Integer> item) {
		return item == null;
	}

}
