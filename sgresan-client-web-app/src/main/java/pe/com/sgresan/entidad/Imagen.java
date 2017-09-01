package pe.com.sgresan.entidad;

import java.util.List;

public class Imagen {
	
	private String idImagen;
	private byte[] imagen;
	private String imagenBase64;
	private String descripcion;
	private String descripcion2;
	private List<Imagen> lstImagen;
	
	/**
	 * Returns attribute idImagen
	 * @return idImagen <code>String</code>
	 */
	public String getIdImagen() {
		return idImagen;
	}
	
	/**
	 * Sets attribute idImagen
	 * @param idImagen <code>String</code>
	 */
	public void setIdImagen(String idImagen) {
		this.idImagen = idImagen;
	}
	
	/**
	 * Returns attribute imagen
	 * @return imagen <code>byte[]</code>
	 */
	public byte[] getImagen() {
		return imagen;
	}
	
	/**
	 * Sets attribute imagen
	 * @param imagen <code>byte[]</code>
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Returns attribute imagenBase64
	 * @return imagenBase64 <code>String</code>
	 */
	public String getImagenBase64() {
		return imagenBase64;
	}
	
	/**
	 * Sets attribute imagenBase64
	 * @param imagenBase64 <code>String</code>
	 */
	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}
	
	/**
	 * Returns attribute descripcion
	 * @return descripcion <code>String</code>
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets attribute descripcion
	 * @param descripcion <code>String</code>
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Returns attribute descripcion2
	 * @return descripcion2 <code>String</code>
	 */
	public String getDescripcion2() {
		return descripcion2;
	}
	
	/**
	 * Sets attribute descripcion2
	 * @param descripcion2 <code>String</code>
	 */
	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	/**
	 * Returns attribute lstImagen
	 * @return lstImagen <code>List<Imagen></code>
	 */
	public List<Imagen> getLstImagen() {
		return lstImagen;
	}

	/**
	 * Sets attribute lstImagen
	 * @param lstImagen <code>List<Imagen></code>
	 */
	public void setLstImagen(List<Imagen> lstImagen) {
		this.lstImagen = lstImagen;
	}

}
