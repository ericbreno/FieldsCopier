package org.fields.utils.fieldsCopier.resources;

import java.util.Random;

public class CopierDefs {

	private static Random random = new Random();

	public static ObjectPOJO newPOJO() {
		ObjectPOJO r = new ObjectPOJO();
		r.setLongExemplo(new Long(random.nextLong()));
		r.setIntegerExemplo(new Integer(random.nextInt()));
		r.setIntExemplo(random.nextInt());
		r.setDoubleExemplo(new Double(random.nextDouble()));
		r.setDoubleExemplo2(new Double(random.nextDouble()));
		r.setBoolExemplo(random.nextInt() % 2 == 0);
		r.setBooleanExemplo(random.nextInt() % 2 == 0 ? Boolean.TRUE : Boolean.FALSE);
		r.setFloatExemplo(random.nextFloat());
		r.setFloatExemplo2(new Float(random.nextFloat()));
		r.setStringExemplo(" ata" + random.nextDouble() + " == " + random.nextInt());
		r.setObjetoExemplo(new Object());
		return r;
	}

	public static ObjectDTO newDTO() {
		ObjectDTO r = new ObjectDTO();
		r.setLongExemplo(new Long(random.nextLong()));
		r.setIntegerExemplo(new Integer(random.nextInt()));
		r.setIntExemplo(random.nextInt());
		r.setDoubleExemplo(new Double(random.nextDouble()));
		r.setDoubleExemplo2(new Double(random.nextDouble()));
		r.setBoolExemplo(random.nextInt() % 2 == 0);
		r.setBooleanExemplo(random.nextInt() % 2 == 0 ? Boolean.TRUE : Boolean.FALSE);
		r.setFloatExemplo(random.nextFloat());
		r.setFloatExemplo2(new Float(random.nextFloat()));
		r.setStringExemplo(" ata" + random.nextDouble() + " == " + random.nextInt());
		r.setObjetoExemplo(new Object());

		r.setStringTransient(" nao sei" + random.nextInt());
		r.setBooleanTransient(Boolean.FALSE);
		r.setLongTransient(new Long(random.nextLong()));
		return r;
	}

	/**
	 * Invalid type for copy, containing no empty constructor.
	 */
	public static class InvalidTypeForConstruct {

		// should never be created
		private Long longExemplo;

		public InvalidTypeForConstruct(Object o1) {
		}
	}

	/**
	 * Valid type for copy, with a single private constructor.
	 */
	public static class SimpleTypePrivateConstruct {

		private Long longExemplo;

		private SimpleTypePrivateConstruct() {
		}

		public SimpleTypePrivateConstruct(Long test) {
			this.longExemplo = test;
		}

		public Long getLongExemplo() {
			return longExemplo;
		}

		public void setLongExemplo(Long longExemplo) {
			this.longExemplo = longExemplo;
		}
	}

	/**
	 * Valid type for copy, with inheritance.
	 */
	public static class ObjectPOJOExtended extends ObjectPOJO {

	}

	/**
	 * Simplified type of {@link ObjectPOJO}.
	 */
	public static class SimplePOJO {
		private Integer integerExemplo;

		private String stringExemplo;

		public SimplePOJO() {

		}

		public String getStringExemplo() {
			return stringExemplo;
		}

		public void setStringExemplo(String stringExemplo) {
			this.stringExemplo = stringExemplo;
		}

		public Integer getIntegerExemplo() {
			return integerExemplo;
		}

		public void setIntegerExemplo(Integer integerExemplo) {
			this.integerExemplo = integerExemplo;
		}
	}

	/**
	 * Valid type for copy.
	 */
	public static class ObjectPOJO {

		private Long longExemplo;

		private Integer integerExemplo;

		private int intExemplo;

		private Double doubleExemplo;

		private double doubleExemplo2;

		private boolean boolExemplo;

		private Boolean booleanExemplo;

		private float floatExemplo;

		private Float floatExemplo2;

		private String stringExemplo;

		private Object objetoExemplo;

		public Long getLongExemplo() {
			return longExemplo;
		}

		public void setLongExemplo(Long longExemplo) {
			this.longExemplo = longExemplo;
		}

		public Integer getIntegerExemplo() {
			return integerExemplo;
		}

		public void setIntegerExemplo(Integer integerExemplo) {
			this.integerExemplo = integerExemplo;
		}

		public int getIntExemplo() {
			return intExemplo;
		}

		public void setIntExemplo(int intExemplo) {
			this.intExemplo = intExemplo;
		}

		public Double getDoubleExemplo() {
			return doubleExemplo;
		}

		public void setDoubleExemplo(Double doubleExemplo) {
			this.doubleExemplo = doubleExemplo;
		}

		public double getDoubleExemplo2() {
			return doubleExemplo2;
		}

		public void setDoubleExemplo2(double doubleExemplo2) {
			this.doubleExemplo2 = doubleExemplo2;
		}

		public boolean isBoolExemplo() {
			return boolExemplo;
		}

		public void setBoolExemplo(boolean boolExemplo) {
			this.boolExemplo = boolExemplo;
		}

		public Boolean getBooleanExemplo() {
			return booleanExemplo;
		}

		public void setBooleanExemplo(Boolean booleanExemplo) {
			this.booleanExemplo = booleanExemplo;
		}

		public float getFloatExemplo() {
			return floatExemplo;
		}

		public void setFloatExemplo(float floatExemplo) {
			this.floatExemplo = floatExemplo;
		}

		public Float getFloatExemplo2() {
			return floatExemplo2;
		}

		public void setFloatExemplo2(Float floatExemplo2) {
			this.floatExemplo2 = floatExemplo2;
		}

		public String getStringExemplo() {
			return stringExemplo;
		}

		public void setStringExemplo(String stringExemplo) {
			this.stringExemplo = stringExemplo;
		}

		public Object getObjetoExemplo() {
			return objetoExemplo;
		}

		public void setObjetoExemplo(Object objetoExemplo) {
			this.objetoExemplo = objetoExemplo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (boolExemplo ? 1231 : 1237);
			result = prime * result + ((booleanExemplo == null) ? 0 : booleanExemplo.hashCode());
			result = prime * result + ((doubleExemplo == null) ? 0 : doubleExemplo.hashCode());
			long temp;
			temp = Double.doubleToLongBits(doubleExemplo2);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + Float.floatToIntBits(floatExemplo);
			result = prime * result + ((floatExemplo2 == null) ? 0 : floatExemplo2.hashCode());
			result = prime * result + intExemplo;
			result = prime * result + ((integerExemplo == null) ? 0 : integerExemplo.hashCode());
			result = prime * result + ((longExemplo == null) ? 0 : longExemplo.hashCode());
			result = prime * result + ((objetoExemplo == null) ? 0 : objetoExemplo.hashCode());
			result = prime * result + ((stringExemplo == null) ? 0 : stringExemplo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ObjectPOJO other = (ObjectPOJO) obj;
			if (boolExemplo != other.boolExemplo) {
				return false;
			}
			if (booleanExemplo == null) {
				if (other.booleanExemplo != null) {
					return false;
				}
			} else if (!booleanExemplo.equals(other.booleanExemplo)) {
				return false;
			}
			if (doubleExemplo == null) {
				if (other.doubleExemplo != null) {
					return false;
				}
			} else if (!doubleExemplo.equals(other.doubleExemplo)) {
				return false;
			}
			if (Double.doubleToLongBits(doubleExemplo2) != Double.doubleToLongBits(other.doubleExemplo2)) {
				return false;
			}
			if (Float.floatToIntBits(floatExemplo) != Float.floatToIntBits(other.floatExemplo)) {
				return false;
			}
			if (floatExemplo2 == null) {
				if (other.floatExemplo2 != null) {
					return false;
				}
			} else if (!floatExemplo2.equals(other.floatExemplo2)) {
				return false;
			}
			if (intExemplo != other.intExemplo) {
				return false;
			}
			if (integerExemplo == null) {
				if (other.integerExemplo != null) {
					return false;
				}
			} else if (!integerExemplo.equals(other.integerExemplo)) {
				return false;
			}
			if (longExemplo == null) {
				if (other.longExemplo != null) {
					return false;
				}
			} else if (!longExemplo.equals(other.longExemplo)) {
				return false;
			}
			if (objetoExemplo == null) {
				if (other.objetoExemplo != null) {
					return false;
				}
			} else if (!objetoExemplo.equals(other.objetoExemplo)) {
				return false;
			}
			if (stringExemplo == null) {
				if (other.stringExemplo != null) {
					return false;
				}
			} else if (!stringExemplo.equals(other.stringExemplo)) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Valid type for copy.
	 */
	public static class ObjectDTO {
		// transients example

		private String stringTransient;

		private Boolean booleanTransient;

		private Long longTransient;

		// transients example end

		private Long longExemplo;

		private Integer integerExemplo;

		private int intExemplo;

		private Double doubleExemplo;

		private double doubleExemplo2;

		private boolean boolExemplo;

		private Boolean booleanExemplo;

		private float floatExemplo;

		private Float floatExemplo2;

		private String stringExemplo;

		private Object objetoExemplo;

		public Long getLongExemplo() {
			return longExemplo;
		}

		public void setLongExemplo(Long longExemplo) {
			this.longExemplo = longExemplo;
		}

		public Integer getIntegerExemplo() {
			return integerExemplo;
		}

		public void setIntegerExemplo(Integer integerExemplo) {
			this.integerExemplo = integerExemplo;
		}

		public int getIntExemplo() {
			return intExemplo;
		}

		public void setIntExemplo(int intExemplo) {
			this.intExemplo = intExemplo;
		}

		public Double getDoubleExemplo() {
			return doubleExemplo;
		}

		public void setDoubleExemplo(Double doubleExemplo) {
			this.doubleExemplo = doubleExemplo;
		}

		public double getDoubleExemplo2() {
			return doubleExemplo2;
		}

		public void setDoubleExemplo2(double doubleExemplo2) {
			this.doubleExemplo2 = doubleExemplo2;
		}

		public boolean isBoolExemplo() {
			return boolExemplo;
		}

		public void setBoolExemplo(boolean boolExemplo) {
			this.boolExemplo = boolExemplo;
		}

		public Boolean getBooleanExemplo() {
			return booleanExemplo;
		}

		public void setBooleanExemplo(Boolean booleanExemplo) {
			this.booleanExemplo = booleanExemplo;
		}

		public float getFloatExemplo() {
			return floatExemplo;
		}

		public void setFloatExemplo(float floatExemplo) {
			this.floatExemplo = floatExemplo;
		}

		public Float getFloatExemplo2() {
			return floatExemplo2;
		}

		public void setFloatExemplo2(Float floatExemplo2) {
			this.floatExemplo2 = floatExemplo2;
		}

		public String getStringExemplo() {
			return stringExemplo;
		}

		public void setStringExemplo(String stringExemplo) {
			this.stringExemplo = stringExemplo;
		}

		public Object getObjetoExemplo() {
			return objetoExemplo;
		}

		public void setObjetoExemplo(Object objetoExemplo) {
			this.objetoExemplo = objetoExemplo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (boolExemplo ? 1231 : 1237);
			result = prime * result + ((booleanExemplo == null) ? 0 : booleanExemplo.hashCode());
			result = prime * result + ((doubleExemplo == null) ? 0 : doubleExemplo.hashCode());
			long temp;
			temp = Double.doubleToLongBits(doubleExemplo2);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + Float.floatToIntBits(floatExemplo);
			result = prime * result + ((floatExemplo2 == null) ? 0 : floatExemplo2.hashCode());
			result = prime * result + intExemplo;
			result = prime * result + ((integerExemplo == null) ? 0 : integerExemplo.hashCode());
			result = prime * result + ((longExemplo == null) ? 0 : longExemplo.hashCode());
			result = prime * result + ((objetoExemplo == null) ? 0 : objetoExemplo.hashCode());
			result = prime * result + ((stringExemplo == null) ? 0 : stringExemplo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ObjectDTO other = (ObjectDTO) obj;
			if (boolExemplo != other.boolExemplo) {
				return false;
			}
			if (booleanExemplo == null) {
				if (other.booleanExemplo != null) {
					return false;
				}
			} else if (!booleanExemplo.equals(other.booleanExemplo)) {
				return false;
			}
			if (doubleExemplo == null) {
				if (other.doubleExemplo != null) {
					return false;
				}
			} else if (!doubleExemplo.equals(other.doubleExemplo)) {
				return false;
			}
			if (Double.doubleToLongBits(doubleExemplo2) != Double.doubleToLongBits(other.doubleExemplo2)) {
				return false;
			}
			if (Float.floatToIntBits(floatExemplo) != Float.floatToIntBits(other.floatExemplo)) {
				return false;
			}
			if (floatExemplo2 == null) {
				if (other.floatExemplo2 != null) {
					return false;
				}
			} else if (!floatExemplo2.equals(other.floatExemplo2)) {
				return false;
			}
			if (intExemplo != other.intExemplo) {
				return false;
			}
			if (integerExemplo == null) {
				if (other.integerExemplo != null) {
					return false;
				}
			} else if (!integerExemplo.equals(other.integerExemplo)) {
				return false;
			}
			if (longExemplo == null) {
				if (other.longExemplo != null) {
					return false;
				}
			} else if (!longExemplo.equals(other.longExemplo)) {
				return false;
			}
			if (objetoExemplo == null) {
				if (other.objetoExemplo != null) {
					return false;
				}
			} else if (!objetoExemplo.equals(other.objetoExemplo)) {
				return false;
			}
			if (stringExemplo == null) {
				if (other.stringExemplo != null) {
					return false;
				}
			} else if (!stringExemplo.equals(other.stringExemplo)) {
				return false;
			}
			return true;
		}

		public String getStringTransient() {
			return stringTransient;
		}

		public void setStringTransient(String stringTransient) {
			this.stringTransient = stringTransient;
		}

		public Boolean getBooleanTransient() {
			return booleanTransient;
		}

		public void setBooleanTransient(Boolean booleanTransient) {
			this.booleanTransient = booleanTransient;
		}

		public Long getLongTransient() {
			return longTransient;
		}

		public void setLongTransient(Long longTransient) {
			this.longTransient = longTransient;
		}
	}
	
	public static class DifferentType { 
		private Long stringExemplo; 
		
		public DifferentType(){} 
	}

}