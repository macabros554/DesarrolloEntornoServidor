package Ejercicios1;

public class CafeConLeche {
	private double precio = 1.20;
	private int cantidadDisponible = 10;
	private int pedidos = 0;
	
	
	public int getPedidos() {
		return pedidos;
	}
	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
		cantidadDisponible=cantidadDisponible-pedidos;
	}
	public CafeConLeche() {
		super();
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public double calcularPrecio(){
		double precioTotal=precio*pedidos;
		return precioTotal;
	}
	
	
}
