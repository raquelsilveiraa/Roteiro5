package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tad.pilha.MinhaPilha;
import tad.pilha.PilhaCheiaException;
import tad.pilha.PilhaIF;
import tad.pilha.PilhaVaziaException;

public class TestaPilha {
	
	protected PilhaIF<Integer> pilha = null;
	
	@BeforeEach
	public void iniciar() {
		pilha = new MinhaPilha();
	}
	
	@Test
	public void empilharTest() {
		try {
			pilha.empilhar(3);	
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			Assert.assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			Assert.assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			Assert.assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
			Assert.assertEquals(Integer.valueOf(2), pilha.topo());
		} catch (PilhaCheiaException e) {
			Assert.fail("empilharTest está estourando a pilha erroneamente");
		}
	}
	
	@Test
	public void topoTest() {
		Assert.assertNull(pilha.topo());
		try {
			pilha.empilhar(3);
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			Assert.assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			Assert.assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			Assert.assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
			Assert.assertEquals(Integer.valueOf(2), pilha.topo());
		} catch (PilhaCheiaException e) {
			Assert.fail(" está estourando a pilha erroneamente");
		}
		
	}
	
	@Test
	public void desempilharTest() {
		try {
			pilha.desempilhar();
			Assert.fail("deveria lançar uma exceção quando chamar o desempilhar de uma pilha vazia");
		} catch (Exception e) {}
		try {
			pilha.empilhar(3);
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			Assert.assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			Assert.assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			Assert.assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
		} catch (PilhaCheiaException e) {
			Assert.fail(" está estourando a pilha erroneamente");
		} 
		try {
			Assert.assertEquals(Integer.valueOf(2), pilha.topo());
			Assert.assertEquals(Integer.valueOf(2), pilha.desempilhar());
			Assert.assertEquals(Integer.valueOf(4), pilha.topo());
			
			Assert.assertEquals(Integer.valueOf(4), pilha.desempilhar());
			Assert.assertEquals(Integer.valueOf(7), pilha.topo());
			
			Assert.assertEquals(Integer.valueOf(7), pilha.desempilhar());
			Assert.assertEquals(Integer.valueOf(5), pilha.topo());
			
			Assert.assertEquals(Integer.valueOf(5), pilha.desempilhar());
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
			
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
		} catch (PilhaVaziaException e) {
			Assert.fail(" está esvaziando a pilha erroneamente");
		}
	}
	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(pilha.isEmpty());
		try { 
			pilha.empilhar(3);
			Assert.assertEquals(Integer.valueOf(3), pilha.topo());
			Assert.assertFalse(pilha.isEmpty());
			pilha.desempilhar();
			Assert.assertTrue(pilha.isEmpty());
			pilha.empilhar(4);
			pilha.empilhar(6);
			Assert.assertFalse(pilha.isEmpty());
		} catch (PilhaCheiaException e) {
			Assert.fail(" está estourando a pilha erroneamente");
		} catch (PilhaVaziaException e) {
			Assert.fail(" está esvaziando a pilha erroneamente");
		}
	}
	
	@Test
	public void pilhaVaziaTest() {
		assertThrows(PilhaVaziaException.class, () -> {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.desempilhar();
			pilha.desempilhar();
			pilha.desempilhar();
			pilha.desempilhar();
	    });
	}
	
	@Test
	public void pilhaCheiaTest() {
		assertThrows(PilhaCheiaException.class, () -> {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			pilha.empilhar(2);
	    });
	}
	
	@Test
	public void multitopTest() {
		PilhaIF<Integer> saida = new MinhaPilha();
		PilhaIF<Integer> saida2 = new MinhaPilha();
		try {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			saida.empilhar(3);
			saida.empilhar(10);
			saida.empilhar(2);
			saida2.empilhar(3);
		} catch (PilhaCheiaException e) {
			Assert.fail("Estouro inexperado da pilha");
		}
		try {			
			assertEquals(saida, pilha.multiPop(3));
			assertEquals(saida2, pilha.multiPop(5));
		} catch (PilhaVaziaException e) {
			Assert.fail("Estouro inexperado da pilha");
		}
		
		
		assertThrows(PilhaVaziaException.class, () -> {
			pilha.multiPop(10);
	    });
	}
	

	

}
