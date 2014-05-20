package org.reflection.firststeps;

import java.lang.reflect.Constructor;

public class MyClass {
	
	public int myInteger;
	public double myDouble;
	
	private void myMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException{
		Class<?> myClass = Class.forName("javax.crypto.Cipher");
		String className = myClass.getName();
		String classSimpleName = myClass.getSimpleName();
		
		Package myPackage = myClass.getPackage();
		Class<?> myClassSuperclass = myClass.getSuperclass();
		Class<?>[] myClassInterfaces = myClass.getInterfaces();
		
		Constructor<?>[] myConstructors = myClass.getConstructors();
		Constructor<?> myConstructor = myClass.getConstructor(new Class[] {String.class, Integer.class});
	}

}
