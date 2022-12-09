package jUnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import bookstore.Book;

class BookUnitTesting {

	@Test
	void CreateSimpleBook() {
		// Create a Book using the 
		List<String> formats = Arrays.asList("eBook", "SoftCover", "HardCover");
		Book myBook = new Book("LA ESTRELLA DE OSITO", "OFE-DAS-2104", "La Coccinella-Panini", formats, 100, 59.90);
		
		assertNotNull(myBook);
	 	assertLinesMatch(myBook.getAvailableFormats(), formats);
		assertEquals(myBook.getBookTotalAmount(), 100 * 59.90, 0.001);
	}
	
	@Test
	void CreateSimpleBookBadFields() {
		List<String> formats = Arrays.asList("SoftCover");
		Book myBook = new Book("LA ESTRELLA DE OSITO", "OFE-DAS-2104", "La Coccinella-Panini", formats, -10, -900);
		
		assertNotNull(myBook);
		assertEquals(myBook.getPrice(), 0);
		assertEquals(myBook.getQuantity(), 0);
	}
	
	@Test
	void CreateSimpleBookWithoutTitle() {
		// Se le coloca un nombre predeterminado
		Book myBook = new Book(null, "OFE-DAS-2104", "La Coccinella-Panini",new ArrayList<String>() , -10, -900);
		assertEquals(myBook.getName(), "S.N."); // S.N. = Sin Nombre
		assertTrue(myBook.getAvailableFormats().contains("eBook"));
	}
	
	@Test
	void CreateSimpleWithNullFormats() {
		// Se le coloca un nombre predeterminado
		Book myBook = new Book(null, null, null, null , 0, 0);
		assertTrue(myBook.getAvailableFormats().contains("eBook"));
	}
	
	@Test
	void AddBadQuantity() {
		Book myBook1 = new Book(null, "OFE-DAS-2104", "La Coccinella-Panini",new ArrayList<String>() , 0, 0);
		Book myBook2 = new Book("LA ESTRELLA DE OSITO", "OFE-DAS-2104", "La Coccinella-Panini",new ArrayList<String>() , 10, 0);
		
		myBook1.addBooks(-5);
		myBook2.addBooks(5);
		
		assertEquals(myBook1.getQuantity(), 0);
		assertEquals(myBook2.getQuantity(), 15);
	}
	
	@Test
	void restBadQuantity() {
		Book myBook1 = new Book(null, null, null,new ArrayList<String>() , 0, 0);
		Book myBook2 = new Book(null, null, null,new ArrayList<String>() , 10, 0);
		Book myBook3 = new Book(null, null, null,new ArrayList<String>() , 50, 0);

		
		myBook1.restBooks(5);
		myBook2.restBooks(-2);
		myBook3.restBooks(51);
		
		assertEquals(myBook1.getQuantity(), 0);
		assertEquals(myBook2.getQuantity(), 10);
		assertEquals(myBook3.getQuantity(), 50);
	}
	
	@Test
	void ModifyBadPrice() {
		Book myBook = new Book(null, null, null,new ArrayList<String>() , 0, 10.0);
		
		myBook.modifyPrice(-35.5);
		
		assertEquals(myBook.getPrice(), 10.0);

	}
	
	@Test
	void ConsultDiscount() {
		Book myBook = new Book(null, null, null,new ArrayList<String>() , 0, 10.0);
		
		myBook.getDiscount(false, 0.25);
		assertEquals(myBook.getPrice(), 10.0);
		
	}
	
	@Test
	void ApplyDiscount() {
		Book myBook = new Book(null, null, null,new ArrayList<String>() , 0, 10.0);
		
		myBook.getDiscount(true, 0.25);
		assertEquals(myBook.getPrice(), 7.5);
	}
	
	@Test
	void AddDuplicatedFormat() {
		List<String> formats = Arrays.asList("SoftCover", "eBook");
		Book myBook = new Book("LA ESTRELLA DE OSITO", "OFE-DAS-2104", "La Coccinella-Panini", formats, -10, -900);
		
		myBook.addNewFormat("eBook");
		
	 	assertLinesMatch(myBook.getAvailableFormats(), formats);

	}
	
	@Test
	void RemoveInvalidFormat() {
		List<String> formats = Arrays.asList("SoftCover", "eBook", "SpecialEdition");
		Book myBook = new Book("LA ESTRELLA DE OSITO", "OFE-DAS-2104", "La Coccinella-Panini", formats, -10, -900);
		
		myBook.removeFormat("HardCover");
		
	 	assertLinesMatch(myBook.getAvailableFormats(), formats);
	}

}
