package tad.pilha;

public interface PilhaIF<E> {
	
	public void empilhar(E item) throws PilhaCheiaException;
	
	public E desempilhar() throws PilhaVaziaException;
	
	public E topo();
	
	public PilhaIF<E> multiPop(int k) throws PilhaVaziaException;
	
	public boolean isEmpty();

}
