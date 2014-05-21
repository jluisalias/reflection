package org.reflection.firststeps;

import java.util.List;

public class CompoundProduct extends Product {
	
	private List<Product> pieces;

	public List<Product> getPieces()
	{
		return pieces;
	}

	public void setPieces(List<Product> pieces)
	{
		this.pieces = pieces;
	}

	public CompoundProduct(String name) {
		super(name);
	}

}
