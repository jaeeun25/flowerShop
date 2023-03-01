
public class CartFlower {
	private String fname, fcolor;	// 선택한 꽃 이름, 선택한 꽃 색상
	private int fqty;	// 선택한 수량
	
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
