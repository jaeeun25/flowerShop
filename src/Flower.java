
public class Flower {
	private String name, color;	// ��ǰ���� ��� �� �� �̸�, �� ����
	private int price;	// �� ����(1���� ����)
	
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
