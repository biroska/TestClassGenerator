package br.com.galdino.testClassGenerator.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class Attribute implements Serializable {
	
	private static final long serialVersionUID = 2046756016016670920L;

	private String type;
	
	private String name;
	
	public boolean isDefined(){
		if ( StringUtils.isNotBlank( type ) && 
			 StringUtils.isNotBlank( name ) ){
			return true;
		}
		
		return false;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attribute [type=" + type + ", name=" + name + "]";
	}
}
