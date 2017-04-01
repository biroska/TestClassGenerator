package testes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BandaTest { 

	private Banda banda = new Banda();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetNome() {
		banda.setNome( "String" );
		assertEquals( new String("String"), banda.getNome() );
	}

	@Test
	public void testSetNome() {
		banda.setNome( "String" );
	}

	@Test
	public void testGetAnoDeFormacao() {
		banda.setAnoDeFormacao( 1 );
		assertEquals( 1, banda.getAnoDeFormacao() );
	}

	@Test
	public void testSetAnoDeFormacao() {
		banda.setAnoDeFormacao( 1 );
	}

	@Test
	public void testGetId() {
		banda.setId( 1 );
		assertEquals( 1, banda.getId() );
	}

	@Test
	public void testSetId() {
		banda.setId( 1 );
	}

	@Test
	public void testGetAlbuns() {
		List collection = new ArrayList<String>();
		banda.setAlbuns( collection );
		assertEquals( collection, banda.getAlbuns() );
	}

	@Test
	public void testSetAlbuns() {
	}

}