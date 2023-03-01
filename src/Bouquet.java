import java.util.ArrayList;

public class Bouquet extends CartList implements Delivery{
	private static ArrayList<CartFlower> bqtf = new ArrayList<>(); // 선택한 꽃 담는 배열
	
	public Bouquet(String color, int type) {
		super(color, type);
	}
	
	@Override
	public ArrayList<CartFlower> getFlower() { // 선택한 꽃 담은 배열 반환
		return bqtf;
	}
	
	@Override
	public void show() { // 꽃다발 상품 정보 출력
		System.out.println("포장지 색상 : " + color);
		for(CartFlower f : bqtf) System.out.println(f.getFname() + " : " + f.getFcolor() + " : " + f.getFqty() + "송이");
		System.out.println("가격 : " + price);
	}
	
	@Override
	public void dprice() { // 배송비 추가 함수
		if(price < 100000) price += dp; // 상품의 총 가격 10만원 이하이면 배송비 2500원 추가
	}
}
