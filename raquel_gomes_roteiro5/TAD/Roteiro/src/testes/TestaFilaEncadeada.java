package testes;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;
import tad.fila.MinhaFila;
import tad.fila.MinhaFilaEncadeada;

public class TestaFilaEncadeada {

	private FilaIF<Integer> fila = null;

	@Before
	public void iniciar() {
		fila = new MinhaFilaEncadeada();
	}

	@Test
	public void enfileirarTest() {
		try {
			fila.enfileirar(3);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
		} catch(FilaCheiaException fce) {
			Assert.fail("fila cheia exception lançado indevidamente");
		}
	}

	@Test
	public void verificarCabecaTest() {
		Assert.assertNull(fila.verificarCabeca());
		try {
			fila.enfileirar(3);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
		}catch (FilaCheiaException fce) {
			Assert.fail("fila cheia exception lançado indevidamente");
		}

	}

	// configurar o tamanho da fila para 5
	@Test
	public void desenfileirarTest() {
		fila = new MinhaFila(5);
		try {
			fila.desenfileirar();
			Assert.fail("deveria lan�ar uma exce��o quando chamar o desenfileirar de uma fila vazia");
		} catch (Exception e) {}
		try {
			fila.enfileirar(3);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			Assert.assertEquals(Integer.valueOf(3), fila.desenfileirar());
			Assert.assertEquals(Integer.valueOf(5), fila.verificarCabeca());
	
			fila.enfileirar(15);
			
			Assert.assertEquals(Integer.valueOf(5), fila.desenfileirar());
			Assert.assertEquals(Integer.valueOf(7), fila.verificarCabeca());
	
			fila.enfileirar(20);
			
			Assert.assertEquals(Integer.valueOf(7), fila.desenfileirar());
			Assert.assertEquals(Integer.valueOf(4), fila.verificarCabeca());
	
			Assert.assertEquals(Integer.valueOf(4), fila.desenfileirar());
			Assert.assertEquals(Integer.valueOf(2), fila.verificarCabeca());
	
			Assert.assertEquals(Integer.valueOf(2), fila.desenfileirar());
		} catch(FilaCheiaException fce) {
			Assert.fail("fila cheia exception lançado indevidamente");
		} catch(FilaVaziaException fve) {
			Assert.fail("fila vazia exception lançado indevidamente");
		}
	}

	@Test
	public void isEmptyTest() {
		try {
			Assert.assertTrue(fila.isEmpty());
			fila.enfileirar(3);
			Assert.assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			Assert.assertFalse(fila.isEmpty());
			fila.desenfileirar();
			Assert.assertTrue(fila.isEmpty());
		} catch(FilaCheiaException fce) {
			Assert.fail("fila cheia exception lançado indevidamente");
		} catch(FilaVaziaException fve) {
			Assert.fail("fila vazia exception lançado indevidamente");
		}
	}
	
	@Test
	public void filaVaziaTest() {
		assertThrows(FilaVaziaException.class, () -> {
			fila.enfileirar(3);
			fila.enfileirar(2);
			fila.enfileirar(10);
			fila.desenfileirar();
			fila.desenfileirar();
			fila.desenfileirar();
			fila.desenfileirar();
	    });
	}
}
