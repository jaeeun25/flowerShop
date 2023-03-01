import java.util.ArrayList;

public class OrderList {
	private static ArrayList<CartFlower> list1 = new ArrayList<>();	// 꽃바구니로 주문했을 때 선택한 꽃 담을 배열
	private static ArrayList<CartFlower> list2 = new ArrayList<>(); // 꽃다발로 주문했을 때 선택한 꽃 담을 배열
	private String email, color1, color2;	// 주문자 이메일, 바구니 색상, 포장지 색상
	private int pidx1=0, pidx2=0, price1=0, price2=0;	// 각 꽃바구니 주문의 list1에 저장될 꽃들 중 첫번째 꽃 인덱스, 각 꽃다발 주문의 list2에 저장될 꽃들 중 첫번째 꽃 인덱스, 꽃바구니 총 가격, 꽃다발 총 가격
	private static int idx1=0, idx2=0;	// list1에서 다음 주문의 꽃이 저장 될 인덱스, list2에서 다음 주문의 꽃이 저장 될 인덱스
	
	public OrderList(String email) {
		super();
		this.email = email;
		pidx1 = idx1;	// 각 꽃바구니 주문의 꽃들 중 첫번째 꽃 인덱스 지정
		pidx2 = idx2;	// 각 꽃다발 주문의 꽃들 중 첫번째 꽃 인덱스 지정	
	}
	
	public static ArrayList<CartFlower> getList1() {
		return list1;
	}
	public static ArrayList<CartFlower> getList2() {
		return list2;
	}

	public static void setIdx1(int idx1) {
		OrderList.idx1 = idx1;
	}

	public static void setIdx2(int idx2) {
		OrderList.idx2 = idx2;
	}
	
	public void setPidx1(int pidx1) {
		this.pidx1 = pidx1;
	}

	public void setPidx2(int pidx2) {
		this.pidx2 = pidx2;
	}

	public int getPidx1() {
		return pidx1;
	}

	public int getPidx2() {
		return pidx2;
	}

	public String getEmail() {
		return email;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor1() {
		return color1;
	}
	
	public String getColor2() {
		return color2;
	}

	public int getPrice1() {
		return price1;
	}

	public void setPrice1(int price1) {
		this.price1 = price1;
	}

	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}

}
