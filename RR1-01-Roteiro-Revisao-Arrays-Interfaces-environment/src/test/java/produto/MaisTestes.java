package produto;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class MaisTestes {
	
	RepositorioProdutoPerecivelArray repo;
	
	@Before
	public void iniciar() {
		repo = new RepositorioProdutoPerecivelArray(1);
	}
	
	@Test
	public void testExiste() {
		assertFalse(repo.existe(1));
	}
	@Test
	public void testExiste2() {
		repo.inserir(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
		assertTrue(repo.existe(1));
		assertFalse(repo.existe(2));
	}
	
	@Test
	public void testInserir() {
		repo.inserir(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
		repo.inserir(new ProdutoPerecivel(2, "o2", 10, "dc > marvel", new GregorianCalendar()));
		assertTrue(repo.existe(2));
	}
	@Test
	public void testAtualizar() {
		repo.inserir(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
		repo.atualizar(new ProdutoPerecivel(1, "o2", 2017, "dc > marvel", new GregorianCalendar()));
		assertEquals(repo.procurar(1).getPreco(), 2017.0, 0.01);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAtualizarIN() {
		repo.atualizar(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
	}
	@Test(expected=IllegalArgumentException.class)
	public void testProcuraIn() {
		repo.procurar(2);
		repo.inserir(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
		repo.procurar(3);
	}
	@Test
	public  void testRemover() {
		repo.inserir(new ProdutoPerecivel(1, "p1", 1, "p1 > p2", new GregorianCalendar()));
		assertTrue(repo.existe(1));
		repo.remover(1);
		assertFalse(repo.existe(1));
	} 
	@Test(expected=IllegalArgumentException.class)
	public void testRemoverIN() {
		repo.remover(1);
	}
}
