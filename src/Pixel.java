import javax.swing.JButton;


public class Pixel extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float value;
	public int id;
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
