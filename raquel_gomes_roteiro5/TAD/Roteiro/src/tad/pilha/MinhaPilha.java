package tad.pilha;

import java.util.Arrays;

import tad.fila.FilaCheiaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaPilha implements PilhaIF<Integer> {
	
	public Integer[] pilha;
	
	private int tamanho = -1;
	private int capacidade = 10;

	public MinhaPilha(int tamanho) {
		this.pilha = new Integer[capacidade];
		this.capacidade = tamanho;
		this.preencherPilhaComNulo();
	}
	
	public MinhaPilha() {
		this.pilha = new Integer[this.capacidade];
		this.preencherPilhaComNulo();
	}

	/*Insere um elemento na pilha.Não retorna nada. O item a ser inserido na pilha. @throws PilhaCheiaException
	 lança uma exceção quando a pilha está cheia. */
	
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (tamanho == capacidade - 1) throw new PilhaCheiaException();
		this.tamanho++;
		this.pilha[tamanho] = item;
	}

	/*Remove um elemento da pilha.Retorna o Integer removido. @throws PilhaVaziaException 
	lança uma exceção quando a pilha está vazia. */
	
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (tamanho < 0) throw new PilhaVaziaException();
		this.tamanho--;
		return this.pilha[tamanho + 1];
	}
	
	/*Retorna o topo da pilha, se não houver elementos irá retornar null. */

	@Override
	public Integer topo() {
		if (tamanho < 0) return null;
		return this.pilha[tamanho];
	}

	/*Remove K elementos da pilha.Retorna a pilha sem os elementos excluídos.@throws PilhaVaziaException
	 lança uma exceção quando a pilha está vazia. */
	
	@Override
	public PilhaIF<Integer> multiPop(int k) throws PilhaVaziaException {
		if(tamanho < 0) throw new PilhaVaziaException();
		for (int i = 0; i < k; i++) {
			if(this.tamanho < 0) throw new PilhaVaziaException();
			this.pilha[this.tamanho--] = null;
    	}
		return this;
	}

	@Override
	public boolean isEmpty() {
		return this.tamanho < 0;
	}

    private void preencherPilhaComNulo() {
    	for (int i = 0; i < this.capacidade; i++) {
    		this.pilha[i] = null;
    	}
    }
    
    @Override
    public String toString() {
    	return Arrays.toString(pilha);
    }
    
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
   
}
