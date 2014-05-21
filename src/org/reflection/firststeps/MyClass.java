package org.reflection.firststeps;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class MyClass {
	
	public int myInteger;
	public double myDouble;
	
	public MyClass(int myInteger, double myDouble){
		this.myInteger = myInteger;
		this.myDouble = myDouble;
	}
	
	public static void main(String[] args){
		MyClass myObject = new MyClass(1, 1.0);
		try{
			myObject.myMethod();
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void myMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
		
		//Clase, constructor e instancia
		Class<?> productClass = Class.forName("org.reflection.firststeps.Product");
		Constructor<?> productConstructor = productClass.getConstructor(new Class[] {String.class});
		Product product = (Product) productConstructor.newInstance("UAV-Producción");
		
		//Atributos
		Field productNameField = productClass.getField("name");
		Field[] productFields = productClass.getFields();
		for (Field field : productFields) {
			System.out.println("Nombre del parámetro: " + field.getName());
		}
		System.out.println("Nombre del parámetro -nombre-: " + productNameField.getName());
		
		//Atributos privados
		Field[] allProductFields = productClass.getDeclaredFields();
		for (Field field : allProductFields) {
			System.out.println("Nombre del parámetro declarado: " + field.getName());
		}
		
		//Acceder a valores de los atributos
		Object fieldValue = productNameField.get(product);
		String fieldValueString = fieldValue.toString();
		System.out.println("Valor del parámetro name: "+fieldValueString);
		
		//Cambiar valores de los atributos
		productNameField.set(product, "UAV-Cambiando");
		fieldValue = productNameField.get(product);
		fieldValueString = fieldValue.toString();
		System.out.println("Valor del parámetro name: "+fieldValueString);
		
		//Acceder y cambiar valores de atributos privados
		Field productSerialNumberField = productClass.getDeclaredField("serialNumber");
		productSerialNumberField.setAccessible(true);
		productSerialNumberField.set(product, "AVN-11548");
		fieldValue = productSerialNumberField.get(product);
		fieldValueString = fieldValue.toString();
		System.out.println("Valor del parámetro serial number: "+fieldValueString);
		
		//Acceder a los métodos de la clase
		Method[] productMethods = productClass.getMethods();
		for (Method method : productMethods) {
			System.out.println("nombre del método: " + method.getName());
		}
		
		//Acceder a métodos e invocarlos
		Method productMethod = productClass.getMethod("setPartNumber", new Class[] {String.class});
		System.out.println("número de parámetros del método: " + productMethod.getParameterCount());
		productMethod.invoke(product, "AB211-SE453");
		Field productPartNumbeField = productClass.getDeclaredField("partNumber");
		fieldValue = productPartNumbeField.get(product);
		fieldValueString = fieldValue.toString();
		System.out.println("Valor del parámetro part number: "+fieldValueString);
		
		//Arrays
		String[] products = (String[]) Array.newInstance(String.class, 3);
		Array.set(products, 0, "Quadrotor");
		System.out.println("El producto 0 es: " + Array.get(products, 0).toString());
		
		//Obtener la clase de un array primitivo
		Class<?> intArrayClass = Class.forName("[I");
		//boolean: Z, byte: B, short: S, int: I, long: J, char: C, float: F, double: D
		
		//Obtener la clase de un array objetual (L)
		Class<?> stringArrayClass = Class.forName("[Ljava.lang.String;");
		
		//Genéricos (List, Iterator y demás)
		Field piecesField = CompoundProduct.class.getDeclaredField("pieces");
		Type piecesGenericType = piecesField.getGenericType();
		System.out.println("Tipo genérico: " + piecesGenericType.getTypeName());
		ParameterizedType piecesParameterizedType = null;
		if (piecesGenericType instanceof ParameterizedType){
			piecesParameterizedType = (ParameterizedType) piecesGenericType;
		}
		System.out.println("Tipo parametrizado: " + piecesParameterizedType.getTypeName());
		Type[] piecesType = piecesParameterizedType.getActualTypeArguments();
		Class<?> product2Class = (Class<?>) piecesType[0];
		System.out.println("Clase: " + product2Class.getCanonicalName());
		
		//Parámetros genéricos de un método
		Method getPiecesMethod = CompoundProduct.class.getMethod("getPieces", null);
		Type returnType = getPiecesMethod.getGenericReturnType();
		System.out.println("Tipo devuelto por getPieces es: " + returnType.getTypeName());
		Method setPiecesMethod = CompoundProduct.class.getMethod("setPieces", List.class);
		Type[] parameterTypes = setPiecesMethod.getGenericParameterTypes();
		for (Type type : parameterTypes) {
			System.out.println("Tipo de entrada a setPieces: " + type.getTypeName());
		}
	}

}
