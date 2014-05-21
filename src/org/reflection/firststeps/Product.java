package org.reflection.firststeps;

public class Product {
	public String name;
	public String partNumber = null;
	private String serialNumber;

	public Product(String name)
	{
		this.name = name;
	}

	public void setPartNumber(String partNumber)
	{
		this.partNumber = partNumber;
	}

	public String getPartNumber()
	{
		if (partNumber == null)
		{
			return name;
		}
		else
		{
			return partNumber;
		}
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
