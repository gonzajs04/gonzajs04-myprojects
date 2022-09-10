package configuraciones;

public enum Configuraciones {
	
	PPM("PPM",0.5f);
	private float valor;
	private String nombre;
	Configuraciones(String nombre,float valor){
		this.nombre=nombre;
		this.valor=valor;
	}
	public float getValor() {
		return valor;
	}
	public String getNombre() {
		return nombre;
	}

}
