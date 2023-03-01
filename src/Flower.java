
public class Flower {
	private String name, color;	// 상품으로 등록 할 꽃 이름, 꽃 색상
	private int price;	// 꽃 가격(1송이 기준)
	
	public Flower(String name, int price, String color) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getColor() {
		return color;
	}
	
}
