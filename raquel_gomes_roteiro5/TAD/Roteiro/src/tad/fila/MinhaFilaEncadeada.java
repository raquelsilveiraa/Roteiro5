package tad.fila;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;
import tad.pilha.PilhaVaziaException;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	ListaEncadeadaImpl<Integer> fila = new ListaEncadeadaImpl<>();
	
	
	/*Insere um elemento na fila.Não retorna nada. O item a ser inserido na fila.
	 @throws FilaCheiaException lança uma exceção quando a fila está cheia. */
	
	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		fila.insert(item);
	}
	
	/* Remove um elemento da fila. Retorna o Integer removido. O item a ser removido na fila.
	  @throws FilaVaziaException lança uma exceção quando a fila está vazia.*/

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if(fila.isEmpty()) throw new FilaVaziaException();
		NodoListaEncadeada<Integer> elemento = this.fila.getCabeca();
		try {
			elemento = fila.remove(elemento.getChave());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			e.printStackTrace();
		}
		return elemento.getChave();
	}

	/* Verifica e retorna o elemento da cauda. Se não houver elementos na fila irá retornar null.*/
	
	@Override
	public Integer verificarCauda() {
		if(fila.isEmpty()) return null;
		NodoListaEncadeada<Integer> element = this.fila.getCauda();
		return element.getChave();
	}
	
	/* Verifica e retorna o elemento da cabeca. Se não houver elementos na fila irá retornar null.*/

	@Override
	public Integer verificarCabeca() {
		if(fila.isEmpty()) return null;
		NodoListaEncadeada<Integer> elemento = this.fila.getCabeca();
		return elemento.getChave();
	}

	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}
	
}
