
public class CartFlower {
	private String fname, fcolor;	// ������ �� �̸�, ������ �� ����
	private int fqty;	// ������ ����
	
	public CartFlower(String fname, int fqty, String fcolor) {
		super();
		this.fname = fname;
		this.fqty = fqty;
		this.fcolor = fcolor;
	}

	public String getFname() {
		return fname;
	}

	public String getFcolor() {
		return fcolor;
	}

	public int getFqty() {
		return fqty;
	}
	
}
