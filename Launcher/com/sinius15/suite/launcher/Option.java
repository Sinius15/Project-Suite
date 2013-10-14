package com.sinius15.suite.launcher;

public class Option<E> {

	public E defaultValue;
	public E value;
	public String key;
	public Class<?> type;
	
	public Update<E> setValue;
	
	public Option(Class<?> type, E defaultValue, String key ,Update<E> setValue) {
		this.setValue = setValue;
		this.defaultValue = defaultValue;
		this.key = key;
		this.type = type;
	}
	
	public String getType(){
		if(this.type.equals(Integer.class))
			return "int";
		if(this.type.equals(Boolean.class))
			return "boolean";
		if(this.type.equals(String.class))
			return "String";
		return null;
	}
	
	public static interface Update<E> {
	    public abstract void run(E value);
	}
	
}
