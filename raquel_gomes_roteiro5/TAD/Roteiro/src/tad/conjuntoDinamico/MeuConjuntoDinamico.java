package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	int tamanho;
	int capacidade;
	Integer[] conjunto_dinamico;
	
	public MeuConjuntoDinamico() {
		this.tamanho = 0;
		this.capacidade = 10;
		this.conjunto_dinamico = new Integer[10];
	}
	
	/* Insere um elemento no conjunto. Aumenta o tamanho do conjunto no processo.
	   Não retorna nada. O item a ser inserido no conjunto. */
	
	@Override
	public void inserir(Integer item) {
		this.incrementarTamanho();
		this.conjunto_dinamico[this.tamanho] = item;
		this.tamanho++;
	}

	/* Remove um elemento do conjunto. Reduz o tamanho do conjunto no processo.Retorna o Integer
	 removido no processo.O item o valor a ser procurado no conjunto a fim de ser removido.
	 @throws Exception lança uma exceção quando o valor procurado não foi encontrado.
 */
	
	@Override
	public Integer remover(Integer item) throws Exception {
		boolean foiEncontrado = false;
		int index = 0;
		Integer itemEncontrado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto_dinamico[i].equals(item)) {
				foiEncontrado = true;
				index = i;
				itemEncontrado = this.conjunto_dinamico[i];
				break;
			}
			i++;
		}
		
		if(foiEncontrado) {
			this.ajustarArray(index);
			this.tamanho--;
			return itemEncontrado;
		} else {
			throw new Exception("Item não encontrado.");
		}
	}

	/*Retorna o Integer antecessor ao valor procurado. Retorna null quando esse valor não possui um antecessor.
	  O item o valor a ser procurado no conjunto. @throws Exception lança uma exceção quando o conjunto 
	  está vazio ou quando o valor não foi encontrado.*/
	
	@Override
	public Integer predecessor(Integer item) throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vazio");
		
		boolean foiEncontrado = false;
		int index = 0;
		Integer itemEncontrado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto_dinamico[i].equals(item)) {
				foiEncontrado = true;
				index = i;
				itemEncontrado = this.conjunto_dinamico[i];
				break;
			}
			i++;
		}
		
		if(foiEncontrado) {
			if (index - 1 < 0) return null;
			else return this.conjunto_dinamico[index - 1];
		} else {
			throw new Exception("Item não encontrado.");
		}
	}

	/* Retorna o Integer sucessor ao valor procurado. Retorna null quando esse valor não possui um sucessor.
	  O item o valor a ser procurado no conjunto.@throws Exception lança uma exceção quando o
	   conjunto está vazio ou quando o valor não foi encontrado.*/
	
	@Override
	public Integer sucessor(Integer item) throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
		
		boolean foiEncontrado = false;
		int index = 0;
		Integer itemEncontrado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto_dinamico[i].equals(item)) {
				foiEncontrado = true;
				index = i;
				itemEncontrado = this.conjunto_dinamico[i];
				break;
			}
			i++;
		}
		
		if(foiEncontrado) {
			if (index == this.tamanho) return null;
			else return this.conjunto_dinamico[index + 1];
		} else {
			throw new Exception("Item não encontrado.");
		}
	}

	@Override
	public int tamanho() {
		return this.tamanho;
	}

	/* Retorna o Integer procurado. O item o valor a ser procurado no conjunto.
	  @throws Exception lança uma exceção quando o conjunto está vazio ou quando o valor não foi encontrado. */
	
	@Override
	public Integer buscar(Integer item) throws Exception {
		boolean foiEncontrado = false;
		Integer itemEncontrado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto_dinamico[i].equals(item)) {
				foiEncontrado = true;
				itemEncontrado = this.conjunto_dinamico[i];
				break;
			}
			i++;
		}
		
		if(foiEncontrado) return itemEncontrado;
		else throw new Exception("Item não encontrado.");
	}
	
	/* Retorna o menor valor do conjunto. @throws Exception lança uma exceção quando o conjunto está vazio. */

	@Override
	public Integer minimum() throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vazio");
		int min = this.conjunto_dinamico[0];
		for (int i = 0; i < this.tamanho; i++) {
			if(this.conjunto_dinamico[i] < min) min = this.conjunto_dinamico[i];
		}
		return min;
	}

	/* Retorna o maior valor do conjunto. @throws Exception lança uma exceção quando o conjunto está vazio.*/

	@Override
	public Integer maximum() throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
		int max = this.conjunto_dinamico[0];
		for (int i = 0; i < this.tamanho; i++) {
			if(this.conjunto_dinamico[i] > max) max = this.conjunto_dinamico[i];
		}
		return max;
	}
	
	private void incrementarTamanho() {
      if (this.tamanho == this.capacidade) {
      	Integer[] novoConjunto = new Integer[capacidade];
      	for (int i =0; i < this.tamanho; i++) {
      		novoConjunto[i] = this.conjunto_dinamico[i];
      	}
      }
      atualizarCapacidade();
	}
	
	private void atualizarCapacidade() {
		this.capacidade = this.conjunto_dinamico.length;
	}
	  
	private void ajustarArray(int index) {
		for (int i = index; i < this.tamanho; i++) {
			this.conjunto_dinamico[i] = this.conjunto_dinamico[i + 1];
		}
	}
}
