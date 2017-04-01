package org.fields.utils.fieldsCopier;

import java.lang.reflect.Field;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.fields.utils.fieldsCopier.resources.CopierDefs;
import org.fields.utils.fieldsCopier.resources.CopierDefs.InvalidTypeForConstruct;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectDTO;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectPOJO;
import org.fields.utils.fieldsCopier.resources.CopierDefs.ObjectPOJOExtended;
import org.fields.utils.fieldsCopier.resources.CopierDefs.SimplePOJO;
import org.fields.utils.fieldsCopier.resources.CopierDefs.SimpleTypePrivateConstruct;
import org.junit.Assert;
import org.junit.Test;

public class FieldsCopierTest {

	@Test
	public void testCopyPojo() throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectPOJO pojo1 = CopierDefs.newPOJO();
		ObjectPOJO pojo2 = FieldsCopier.createCopy(pojo1);

		Assert.assertEquals(pojo1, pojo2);
		assertProps(pojo1, pojo2);
	}

	@Test
	public void testPojoToPojo() {
		ObjectPOJO pojo1 = new ObjectPOJO();
		ObjectPOJO pojo2 = CopierDefs.newPOJO();

		Assert.assertNotEquals(pojo1, pojo2);

		FieldsCopier.copy(pojo1, pojo2);

		Assert.assertEquals(pojo1, pojo2);
		assertProps(pojo1, pojo2);
	}

	@Test
	public void testPojoToDto() {
		ObjectDTO dto = new CopierDefs.ObjectDTO();
		ObjectPOJO pojo = CopierDefs.newPOJO();
		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void testPojoToSimplePojo() {
		SimplePOJO simplePojo = new SimplePOJO();
		simplePojo.setIntegerExemplo(new Integer(99));
		simplePojo.setStringExemplo("Ata");
		ObjectPOJO pojo = new ObjectPOJO();
		FieldsCopier.copy(pojo, simplePojo);

		assertProps(simplePojo, pojo);
	}

	@Test
	public void testPojoToNewPojo() throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectPOJO pojo1 = CopierDefs.newPOJO();
		ObjectPOJO pojo2 = FieldsCopier.copyTo(pojo1, ObjectPOJO.class);

		Assert.assertEquals(pojo1, pojo2);
		assertProps(pojo1, pojo2);
	}

	@Test
	public void testPojoNewDto() throws OperationNotSupportedException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		ObjectDTO dto = FieldsCopier.copyTo(pojo, ObjectDTO.class);

		assertProps(dto, pojo);
	}

	@Test
	public void testPojoNewSimplePojo()
			throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		SimplePOJO simplePojo = FieldsCopier.copyTo(pojo, SimplePOJO.class);

		assertProps(pojo, simplePojo);
	}

	@Test
	public void testPojoNewExtendedPojo()
			throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		ObjectPOJOExtended extendedPojo = FieldsCopier.copyTo(pojo, ObjectPOJOExtended.class);

		assertProps(pojo, extendedPojo);
	}

	@Test
	public void testCopyDto() throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectDTO dto1 = CopierDefs.newDTO();
		ObjectDTO dto2 = FieldsCopier.createCopy(dto1);
		
		Assert.assertEquals(dto1, dto2);
		assertProps(dto1, dto2);
	}

	@Test
	public void testDtoToDto() {
		ObjectDTO dto1 = new ObjectDTO();
		ObjectDTO dto2 = CopierDefs.newDTO();

		Assert.assertNotEquals(dto1, dto2);

		FieldsCopier.copy(dto1, dto2);

		Assert.assertEquals(dto1, dto2);
		assertProps(dto1, dto2);
	}

	@Test
	public void testDtoToPojo() {
		ObjectDTO dto = CopierDefs.newDTO();
		ObjectPOJO pojo = new CopierDefs.ObjectPOJO();
		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void testDtoToPojoExtended() {
		ObjectDTO dto = CopierDefs.newDTO();
		CopierDefs.ObjectPOJOExtended pojo = new CopierDefs.ObjectPOJOExtended();
		FieldsCopier.copy(pojo, dto);

		assertProps(dto, pojo);
	}

	@Test
	public void testDtoToNewDto() throws OperationNotSupportedException, SecurityException, IllegalArgumentException {
		ObjectDTO dto1 = CopierDefs.newDTO();
		ObjectDTO dto2 = FieldsCopier.copyTo(dto1, ObjectDTO.class);

		Assert.assertEquals(dto1, dto2);
		assertProps(dto1, dto2);
	}

	@Test
	public void testDtoNewPojo() throws OperationNotSupportedException {
		ObjectDTO dto = CopierDefs.newDTO();
		ObjectPOJO pojo = FieldsCopier.copyTo(dto, ObjectPOJO.class);

		assertProps(dto, pojo);
	}

	@Test
	public void testDtoNewPojoExtended() throws OperationNotSupportedException {
		ObjectDTO dto = CopierDefs.newDTO();
		CopierDefs.ObjectPOJOExtended pojo = FieldsCopier.copyTo(dto, ObjectPOJOExtended.class);

		assertProps(dto, pojo);
	}

	@Test
	public void testPrivateConstructor() throws OperationNotSupportedException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		SimpleTypePrivateConstruct dto = FieldsCopier.copyTo(pojo, SimpleTypePrivateConstruct.class);

		assertProps(dto, pojo);
	}

	@Test
	public void testPrivateConstructorInverse() throws OperationNotSupportedException {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		SimpleTypePrivateConstruct dtoPrivateConstruct = FieldsCopier.copyTo(pojo, SimpleTypePrivateConstruct.class);
		ObjectDTO dto = FieldsCopier.copyTo(dtoPrivateConstruct, ObjectDTO.class);

		assertProps(dto, dtoPrivateConstruct);
	}

	@Test
	public void testPojoExtendedToDto() {
		ObjectDTO dto = new CopierDefs.ObjectDTO();
		CopierDefs.ObjectPOJOExtended pojo = new CopierDefs.ObjectPOJOExtended();
		pojo.setIntegerExemplo(23);
		pojo.setStringExemplo("Teste");
		FieldsCopier.copy(dto, pojo);

		assertProps(dto, pojo);
	}

	@Test
	public void testSimplePojoToPojo() {
		ObjectPOJO pojo = CopierDefs.newPOJO();
		SimplePOJO simplePojo = new SimplePOJO();

		FieldsCopier.copy(simplePojo, pojo);

		assertProps(pojo, simplePojo);
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
			if (fieldO2 != null && fieldO1 != null)
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
