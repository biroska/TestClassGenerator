package testes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Banda {

	private String nome;
	private int anoDeFormacao;
	private int id;
	private List<String> albuns;
	private Map<String,Object> mapAlbuns;
	
	public Banda(){}
	
	public Banda( int id){
		this.id = id;
	}
	
	public Banda( int id, String nome, int anoDeFormacao ){
		
		this.setId( id );
		this.setNome( nome );
		this.setAnoDeFormacao( anoDeFormacao );
		this.albuns = new ArrayList<String>();
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoDeFormacao() {
		return anoDeFormacao;
	}
	public void setAnoDeFormacao(int anoDeFormacao) {
		this.anoDeFormacao = anoDeFormacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<String> albuns) {
		this.albuns = albuns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banda other = (Banda) obj;
		if (id != other.id)
			return false;
		return true;
	}

}