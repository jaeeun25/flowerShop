import java.util.ArrayList;

public abstract class CartList{
	protected String color;	// 바구니 or 포장지 색상
	protected int type, price;	// 꽃바구니와 꽃다발 구분위한 변수, 상품의 총 가격(배송비포함)
	
	public CartList(String color, int type) {
		this.color = color;
		this.type = type;
	}

	public abstract void show();
	public abstract ArrayList<CartFlower> getFlower();
}
