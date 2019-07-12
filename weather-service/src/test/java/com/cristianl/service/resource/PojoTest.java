package com.cristianl.service.resource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test constructors, equals(), hashCode(), toString() and each get or set
 * method
 *
 * @param <T>
 *            the class to test
 */
public abstract class PojoTest<T> {

	private final T instance;

	private final java.util.List<String> ignore;

	protected PojoTest() {
		this.instance = getInstance();

		final String[] fieldsToIgnore = ignoreFields();
		if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
			this.ignore = java.util.Arrays.asList(ignoreFields());
		} else {
			this.ignore = new java.util.ArrayList<String>(1);
		}
	}

	@Test
	public void CtorTest() {
		Assert.assertNotNull(this.instance);
	}

	@Test
	public void EqualsTest() {
		Assert.assertNotNull(this.instance);
		Assert.assertTrue(instance.equals(instance));
	}

	@Test
	public void HashcodeTest() {
		Assert.assertNotNull(this.instance);
		Assert.assertTrue(this.instance.hashCode() > -1);
	}

	@Test
	public void ToStringTest() {
		Assert.assertNotNull(this.instance);
		Assert.assertNotNull(this.instance.toString());
	}

	@Test
	public void GetterSetterTest() throws ReflectiveOperationException {
		Assert.assertNotNull(this.instance);

		final Field[] fields = this.instance.getClass().getDeclaredFields();
		Assert.assertNotNull(fields);
		for (final Field field : fields) {
			if (!this.ignore.contains(field.getName())) {
				FieldTest(this.instance, field);
			}
		}
	}

	protected final void FieldTest(final T instance, final Field field) throws ReflectiveOperationException {
		// mock value
		field.setAccessible(true);
		final Object value = getFieldValue(instance, field);
		Assert.assertNotNull(value);

		SetterTest(instance, field, value);
		GetterTest(instance, field, value);
	}

	/**
	 * Test the setter method using reflection of "set" +
	 * "field_with_capital_letter"
	 */
	protected final void SetterTest(final T instance, final Field field, final Object value)
			throws ReflectiveOperationException {
		final String name = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		Method method = instance.getClass().getDeclaredMethod(name, field.getType());
		Assert.assertNotNull(method);

		// set value
		method.invoke(instance, value);
		final Object setResult = field.get(instance);

		// verify the value was set correctly
		Assert.assertNotNull(setResult);
		Assert.assertEquals(value, setResult);
	}

	/**
	 * Test the getter method using reflection of "get/is" +
	 * "field_with_capital_letter"
	 */
	protected final void GetterTest(final T instance, final Field field, final Object value)
			throws ReflectiveOperationException {
		// try "get" + "field_with_capital_letter"
		String name = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		Method method = null;
		try {
			method = instance.getClass().getDeclaredMethod(name);
		} catch (NoSuchMethodError e) {
			// method not exists, continue
			// try "is" + "field_with_capital_letter"
			name = "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			method = instance.getClass().getDeclaredMethod(name);
		}

		Assert.assertNotNull(method);
		final Object getResult = method.invoke(instance);

		Assert.assertNotNull(getResult);
		Assert.assertEquals(value, getResult);
	}

	/**
	 * Mock a test value for getters and setters using the field class. </br>
	 * For example if the field type is int.class than return 1, if String.class
	 * than return "abc", if List.class then return new ArrayList(). </br>
	 * By default will try to return the value set in getInstance() method, or
	 * Class.newInstance()
	 */
	protected Object getFieldValue(final T instance, final Field field) throws ReflectiveOperationException {
		final Object result = field.get(instance);
		if (result != null) {
			return result; // field value was set
		} else if (byte.class == field.getType()) {
			return 1;
		} else if (byte[].class == field.getType()) {
			return new byte[] { 1 };
		} else if (Byte.class == field.getType()) {
			return new Byte((byte) 1);
		} else if (Byte[].class == field.getType()) {
			return new Byte[] { new Byte((byte) 1) };
		} else if (short.class == field.getType()) {
			return 1;
		} else if (short[].class == field.getType()) {
			return new short[] { 1 };
		} else if (Short.class == field.getType()) {
			return new Short((short) 1);
		} else if (Short[].class == field.getType()) {
			return new Short[] { new Short((short) 1) };
		} else if (char.class == field.getType()) {
			return 'a';
		} else if (char[].class == field.getType()) {
			return new char[] { 'a' };
		} else if (Character.class == field.getType()) {
			return new Character('a');
		} else if (Character[].class == field.getType()) {
			return new Character[] { new Character('a') };
		} else if (int.class == field.getType()) {
			return 1;
		} else if (int[].class == field.getType()) {
			return new int[] { 1 };
		} else if (Integer.class == field.getType()) {
			return new Integer(1);
		} else if (Integer[].class == field.getType()) {
			return new Integer[] { new Integer(1) };
		} else if (long.class == field.getType()) {
			return 1L;
		} else if (long[].class == field.getType()) {
			return new long[] { 1L };
		} else if (Long.class == field.getType()) {
			return new Long(1L);
		} else if (Long[].class == field.getType()) {
			return new Long[] { new Long(1L) };
		} else if (float.class == field.getType()) {
			return 1.0F;
		} else if (float[].class == field.getType()) {
			return new float[] { 1.0F };
		} else if (Float.class == field.getType()) {
			return new Float(1.0F);
		} else if (Float[].class == field.getType()) {
			return new Float[] { new Float(1.0F) };
		} else if (double.class == field.getType()) {
			return 1.0D;
		} else if (double[].class == field.getType()) {
			return new double[] { 1.0D };
		} else if (Double.class == field.getType()) {
			return new Double(1.0D);
		} else if (Double[].class == field.getType()) {
			return new Double[] { new Double(1.0D) };
		} else if (boolean.class == field.getType()) {
			return true;
		} else if (boolean[].class == field.getType()) {
			return new boolean[] { true };
		} else if (Boolean.class == field.getType()) {
			return Boolean.TRUE;
		} else if (Boolean[].class == field.getType()) {
			return new Boolean[] { Boolean.TRUE };
		} else if (String.class == field.getType()) {
			return "abc";
		} else if (String[].class == field.getType()) {
			return new String[] { "abc" };
		} else if (java.math.BigInteger.class == field.getType()) {
			return java.math.BigInteger.ONE;
		} else if (java.math.BigInteger[].class == field.getType()) {
			return new java.math.BigInteger[] { java.math.BigInteger.ONE };
		} else if (java.math.BigDecimal.class == field.getType()) {
			return java.math.BigDecimal.ONE;
		} else if (java.math.BigDecimal[].class == field.getType()) {
			return new java.math.BigDecimal[] { java.math.BigDecimal.ONE };
		} else if (java.util.List.class == field.getType()) {
			return new java.util.ArrayList<>();
		} else if (java.util.Set.class == field.getType()) {
			return new java.util.HashSet<>();
		} else if (java.util.Map.class == field.getType()) {
			return new java.util.HashMap<>();
		} else {
			return field.getType().newInstance();
		}
	}

	/**
	 * Return an instance of the pojo you want to test. </br>
	 * Optionally you can set also the properties.
	 * 
	 * @return new object
	 */
	protected abstract T getInstance();

	/**
	 * Return a list of properties you would like to ignore when testing getters and
	 * setters.
	 */
	protected abstract String[] ignoreFields();

}
