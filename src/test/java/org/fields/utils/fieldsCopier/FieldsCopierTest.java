package org.fields.utils.fieldsCopier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.fields.utils.fieldsCopier.resources.CopierDefs;
import org.fields.utils.fieldsCopier.resources.CopierDefs.InvalidTypeForConstruct;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectDTO;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectPOJO;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectPOJOExtended;
import org.fields.utils.fieldsCopier.resources.CopierDefs.SimpleTypePrivateConstruct;
import org.junit.Assert;
import org.junit.Test;

public class FieldsCopierTest {

	@Test
	public void basicTestDTOPOJO() {
		ObjectDTO dto = CopierDefs.newDTO();
		ObjectPOJO pojo = new CopierDefs.ObjectPOJO();

		System.out.println(Arrays.toString(CopierDefs.ObjectPOJOExtended.class.getDeclaredFields()));

		Assert.assertNotEquals(dto, pojo);

		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestDTOPOJOExtended() {
		ObjectDTO dto = CopierDefs.newDTO();
		CopierDefs.ObjectPOJOExtended pojo = new CopierDefs.ObjectPOJOExtended();

		Assert.assertNotEquals(dto, pojo);

		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestPOJOExtendedDTO() {
		ObjectDTO dto = new CopierDefs.ObjectDTO();
		CopierDefs.ObjectPOJOExtended pojo = new CopierDefs.ObjectPOJOExtended();
		pojo.setIntegerExemplo(23);
		pojo.setStringExemplo("Teste");

		Assert.assertNotEquals(dto, pojo);

		FieldsCopier.copy(dto, pojo);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestPOJODTO() {
		ObjectDTO dto = new CopierDefs.ObjectDTO();
		ObjectPOJO pojo = CopierDefs.newPOJO();

		Assert.assertNotEquals(dto, pojo);

		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestPOJOConstructor() throws OperationNotSupportedException {
		ObjectDTO dto = CopierDefs.newDTO();
		ObjectPOJO pojo = FieldsCopier.copyTo(dto, ObjectPOJO.class);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestPOJOExtendedConstructor() throws OperationNotSupportedException {
		ObjectDTO dto = CopierDefs.newDTO();
		CopierDefs.ObjectPOJOExtended pojo = FieldsCopier.copyTo(dto, ObjectPOJOExtended.class);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestDTOConstructor() throws OperationNotSupportedException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		ObjectDTO dto = FieldsCopier.copyTo(pojo, ObjectDTO.class);

		assertProps(dto, pojo);
	}

	@Test
	public void basicTestPrivateConstructor() throws OperationNotSupportedException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		SimpleTypePrivateConstruct dto = FieldsCopier.copyTo(pojo, SimpleTypePrivateConstruct.class);

		assertProps(dto, pojo);
	}

	@Test(expected = OperationNotSupportedException.class)
	public void testCopyToInvalidType() throws OperationNotSupportedException {
		ObjectDTO dto = CopierDefs.newDTO();
		FieldsCopier.copyTo(dto, InvalidTypeForConstruct.class);
		Assert.fail("Should have failed.");
	}

	/**
	 * For fields with same name in two given objects, asserts if they have
	 * equal values.
	 * 
	 * @param o1
	 *            First object.
	 * @param o2
	 *            Second object.
	 */
	private void assertProps(Object o1, Object o2) {
		Class<? extends Object> o1Clazz = o1.getClass();
		Class<? extends Object> o2Clazz = o2.getClass();
		Map<String, Field> fieldsDest = FieldsCopier.getAllFields(o1Clazz);
		Map<String, Field> fieldsOrig = FieldsCopier.getAllFields(o2Clazz);

		for (String fieldDestName : fieldsDest.keySet()) {
			Field fieldO1 = fieldsDest.get(fieldDestName);
			Field fieldO2 = fieldsOrig.get(fieldDestName);
			assertValue(o1, o2, fieldO1, fieldO2);
		}
	}

	private static <T, E> void assertValue(T o1, E o2, Field fieldO1, Field fieldO2) {
		boolean o1State = fieldO1.isAccessible();
		boolean o2State = fieldO2.isAccessible();

		fieldO1.setAccessible(true);
		fieldO2.setAccessible(true);

		try {
			Object o1Value = fieldO1.get(o1);
			Object o2Value = fieldO2.get(o2);

			Assert.assertEquals(o2Value, o1Value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// Suppressed for test purposes.
		} finally {
			fieldO1.setAccessible(o1State);
			fieldO2.setAccessible(o2State);
		}
	}
}
