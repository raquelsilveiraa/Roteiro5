package tad.listasEncadeadas;

public class NodoListaDuplamenteEncadeadaSentinela<T extends Comparable<T>> extends NodoListaDuplamenteEncadeada<T> {

	public NodoListaDuplamenteEncadeadaSentinela(T chave) {
		super(chave);
	}
	
	@Override
	public NodoListaEncadeada<T> getProximo() {
		if(isSentinel()) return null;
		return this.proximo;
	}
	
	private boolean isSentinel() {
		return this.proximo.getChave() == null;
	}
	
}
