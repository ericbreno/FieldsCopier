package org.fields.utils.fieldsCopier;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

/**
 * Generic object properties copier.
 * 
 * @author Eric Breno - github.com/ericbreno/fields-copier
 */
public final class FieldsCopier {

	/**
	 * I don't think we need instances for this class, let's just make sure no
	 * one is trying to do it, just in case.
	 */
	private FieldsCopier() {
	}

	/**
	 * Creates a copy from a given Object. This method calls
	 * {@linkplain #copyTo(Object, Class)} with given object and it's type.
	 * 
	 * All rules and restrictions applied to {@linkplain #copyTo(Object, Class)}
	 * and {@linkplain #copy(Object, Object)} are valid here.
	 * 
	 * @param <T>
	 *            Origin and destination Object type.
	 * @param origin
	 *            Object to be copied.
	 * @return New instance from given object type with same properties.
	 * @throws OperationNotSupportedException
	 *             Thrown if there are no empty constructors for the given type.
	 * @throws SecurityException
	 *             If the security manager is present and one of the security
	 *             conditions are violated.
	 * @throws IllegalArgumentException
	 *             If two properties with same name have incompatible types.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createCopy(T origin)
			throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		return (T) copyTo(origin, (Class<T>) origin.getClass());
	}

	/**
	 * Copies fields from a given object to a new instance of another (or same)
	 * class. Internally it creates a new instance for the class and the calls
	 * {@linkplain #copy(Object, Object)} method. An empty constructor (even if
	 * it's private) is necessary for the given type, otherwise, and exception
	 * is thrown.
	 * 
	 * All rules and restrictions applied to {@linkplain #copy(Object, Object)}
	 * are valid here.
	 * 
	 * @param <T>
	 *            Origin Object type.
	 * @param <E>
	 *            Destination Object type.
	 * @param origin
	 *            Origin object.
	 * @param destClazz
	 *            Destination type object.
	 * @return New instance of the type given with properties copied from source
	 *         object.
	 * @throws OperationNotSupportedException
	 *             Thrown if there are no empty constructors for the given type.
	 * @throws SecurityException
	 *             If the security manager is present and one of the security
	 *             conditions are violated.
	 * @throws IllegalArgumentException
	 *             If two properties with same name have incompatible types.
	 */
	public static <T, E> E copyTo(T origin, Class<E> destClazz)
			throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		@SuppressWarnings("unchecked")
		Constructor<E>[] constructors = (Constructor<E>[]) destClazz.getDeclaredConstructors();
		for (Constructor<E> constructor : constructors) {
			E destObject = tryConstruct(constructor);
			if (isNull(destObject)) {
				continue;
			}
			copy(destObject, origin);
			return destObject;
		}
		throw new OperationNotSupportedException("Your destination class needs an empty construtor.");
	}

	/**
	 * Tries to create an instance with given constructor, working if the
	 * constructor needs no parameters. In case of success, returns the new
	 * instance, otherwise returns null.
	 * 
	 * @param <E>
	 *            Object type to be instantiated.
	 * @param constructor
	 *            Constructor to try to be used.
	 * @return Object instance in case of success, null otherwise.
	 */
	private static <E> E tryConstruct(Constructor<E> constructor) {
		boolean constructorState = constructor.isAccessible();
		if (constructor.getParameterCount() == 0) {
			constructor.setAccessible(true);
			try {
				E destObject = (E) constructor.newInstance();
				return destObject;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO: maybe you'll want to handle this.
			} finally {
				constructor.setAccessible(constructorState);
			}
		}
		return null;
	}

	/**
	 * Copies all properties with same name from a given origin object to a
	 * destination object. Gets all declared fields in destination object, and
	 * for each try to get the field with equal name in the origin object. If
	 * successful, the value is copied from the origin to the destination
	 * object.
	 * 
	 * @param <T>
	 *            Origin Object type.
	 * @param <E>
	 *            Destination Object type.
	 * @param dest
	 *            Destination object for copy.
	 * @param orig
	 *            Origin object for copy.
	 * @throws IllegalArgumentException
	 *             If two properties with same name have incompatible types.
	 * @throws SecurityException
	 *             If the security manager is present and one of the security
	 *             conditions are violated.
	 */
	public static <T, E> void copy(T dest, E orig) throws IllegalArgumentException, SecurityException {
		Class<? extends Object> destClazz = dest.getClass();
		Class<? extends Object> origClazz = orig.getClass();
		Map<String, Field> fieldsDest = getAllFields(destClazz);
		Map<String, Field> fieldsOrig = getAllFields(origClazz);

		for (String fieldDestName : fieldsDest.keySet()) {
			Field fieldDest = fieldsDest.get(fieldDestName);
			Field fieldOrig = fieldsOrig.get(fieldDestName);
			if (!isNull(fieldDest) && !isNull(fieldOrig))
				setValue(dest, orig, fieldDest, fieldOrig);
		}
	}

	/**
	 * Gets all fields from this class and all super classes except
	 * {@link Object}
	 * 
	 * @param clazz
	 *            Class to have fields extracted.
	 * @return Map with all fields from the class and all super classes.
	 */
	public static Map<String, Field> getAllFields(Class<?> clazz) {
		Map<String, Field> map = new HashMap<>();
		// while we're not in Object
		while (!isNull(clazz) && !clazz.equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				map.putIfAbsent(field.getName(), field);
			}
			clazz = clazz.getSuperclass();
		}
		return map;
	}

	/**
	 * Sets a value for given field, copying it from the origin object to the
	 * destination object. First, the accessibility to the field is set to true,
	 * then, value from origin object is get and set in the destination object.
	 * 
	 * @param <T>
	 *            Origin Object type.
	 * @param <E>
	 *            Destination Object type.
	 * @param dest
	 *            Destination object.
	 * @param orig
	 *            Origin object.
	 * @param fieldDest
	 *            Destination object field.
	 * @param fieldOrig
	 *            Origin object field.
	 * 
	 * @throws IllegalArgumentException
	 *             If two properties with same name have incompatible types.
	 */
	private static <T, E> void setValue(T dest, E orig, Field fieldDest, Field fieldOrig)
			throws IllegalArgumentException {
		boolean destState = fieldDest.isAccessible();
		boolean origState = fieldOrig.isAccessible();

		fieldDest.setAccessible(true);
		fieldOrig.setAccessible(true);

		try {
			Object origVal = fieldOrig.get(orig);
			fieldDest.set(dest, origVal);
		} catch (IllegalAccessException e) {
			// Never thrown
		} finally {
			fieldDest.setAccessible(destState);
			fieldOrig.setAccessible(origState);
		}
	}

	/**
	 * Checks if an Object is {@code null}.
	 * 
	 * @param o
	 *            Object.
	 * @return True if {@code null}
	 */
	private static boolean isNull(Object o) {
		return o == null;
	}
}
