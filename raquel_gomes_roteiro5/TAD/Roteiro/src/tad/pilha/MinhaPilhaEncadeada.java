package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	ListaEncadeadaImpl<Integer> pilha = new ListaEncadeadaImpl<>();
	
	/*Insere um elemento na pilha. Não retorna nada. O item a ser inserido na pilha. @throws PilhaCheiaException]
	 lança uma exceção quando a pilha está cheia. */
	
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		pilha.insert(item);	
	}

	/* Remove um elemento da pilha. Retorna o Integer removido. @throws PilhaVaziaException lança 
	uma exceção quando a pilha está vazia. */
	
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (pilha.isEmpty()) throw new PilhaVaziaException();
		NodoListaEncadeada<Integer> elemento;
		try {
			elemento = this.pilha.removerCauda();
		} catch (ListaVaziaException e) {
			throw new PilhaVaziaException();
		}
		if(naoEncontrado(elemento)) return null;
		else return elemento.getChave();
	}

	/* Retorna o topo da pilha, se não houver elementos irá retornar null.*/
	
	@Override
	public Integer topo() {
		NodoListaEncadeada<Integer> topo = this.pilha.getCauda();
		
		if(naoEncontrado(topo)) return null;
		else return topo.getChave();
	}
	
	/*Remove K elementos da pilha. Retorna a pilha sem os elementos excluídos. @throws PilhaVaziaException 
	lança uma exceção quando a pilha está vazia.*/

	@Override
	public PilhaIF<Integer> multiPop(int k) throws PilhaVaziaException {
		if(this.pilha.isEmpty()) throw new PilhaVaziaException();
		for (int i = 0; i < k; i++) {
			if(this.pilha.isEmpty()) throw new PilhaVaziaException();
			NodoListaEncadeada<Integer> elemento;
			try {
				elemento = this.pilha.removerCauda();
			} catch (ListaVaziaException e) {
				e.printStackTrace();
			}
		}
		return this;		
	}

	@Override
	public boolean isEmpty() {
		return pilha.isEmpty();
	}
	
	private boolean naoEncontrado(NodoListaEncadeada<Integer> item) {
		return item == null;
	}
	
    @Override
    public String toString() {
		if (this.isEmpty()) return "";
		String resultado = "";
		NodoListaEncadeada<Integer> aux = this.pilha.getCabeca();
		while(aux != null) {
			resultado += aux.getChave();
			if(aux.getProximo() != null) resultado += ", ";
			aux = aux.getProximo();
		}
		return resultado;
    }
    
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
