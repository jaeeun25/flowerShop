import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FlowerManager {
	private Scanner in = new Scanner(System.in);
	private static ArrayList<CartList> cart;	// 장바구니
	private static ArrayList<OrderList> order;	// 주문담는 배열
	private static boolean e = false;	// 주문 목록 담은 파일 로드여부 확인 변수
	
	public FlowerManager() {
		cart = new ArrayList<>();
		order = new ArrayList<>();
		oload();	// 이전실행의 주문 목록이 저장되어 있는 파일 로드
	}

	public void buy(FlowerAdmin admin) {	// 상품 선택 및 장바구니 추가 함수
		if(cart.size() < 2) { // 장바구니 담긴 상품이 2개 미만일 때
			ArrayList<Flower> flowers = FlowerAdmin.getFlowers();
			CartFlower cf;
			admin.fshow();	// 등록된 꽃 목록 출력
			System.out.println("  * 꽃 3가지 선택(중복 가능)");
			System.out.println("  * 10만원이상 배송비 무료");
			int num, fqty, type, price=0;	// 상품(꽃)번호, 상품(꽃)수량, 꽃다발or꽃바구니 타입 저장 변수, 총가격
			String fname, fcolor, color, type1 = null, type2 = null; // 상품(꽃)이름, 상품(꽃)색상, 포장지or바구니 색상, 장바구니에 꽃바구니와 꽃다발 상품 여부 확인 변수
			for(int i=0; i<cart.size(); i++) {
				if(cart.get(i).type == 1) type1 = "y"; // 장바구니에 꽃바구니 상품이 담겨있는 경우
				else type2 = "y";	// 장바구니에 꽃다발 상품이 담겨있는 경우
			}
			System.out.print("1.꽃바구니 2.꽃다발 : "); type = in.nextInt();
			if(type == 1 && type1 != "y") {	// 장바구니에 꽃바구니 상품 담겨있지 않고 꽃바구니 타입을 선택했을 때
				System.out.print("바구니 색상 : "); color = in.next();
				Basket bkt = new Basket(color, type);
				for(int i=0; i<3; i++) {
					System.out.print("add: "); num = in.nextInt(); fqty = in.nextInt();
					boolean found = false;	// 선택한 번호에 해당하는 꽃의 여부 확인 변수
					for(int j=0; j<flowers.size(); j++) {
						if(num == j+1) { // 선택한 번호에 해당하는 꽃이 있을 경우
							fname = flowers.get(j).getName();
							fcolor = flowers.get(j).getColor();
							price += flowers.get(j).getPrice() * fqty;
							cf = new CartFlower(fname, fqty, fcolor);
							bkt.getFlower().add(cf);
							found = true;
						}
					}
					if(!found) { // 선택한 번호에 해당하는 꽃이 없는 경우
						System.out.println("해당하는 꽃이 없습니다.");
						i--; // 상품 선택 횟수 증가 된 것 다시 감소
					}
				}
				bkt.price = price;
				bkt.dprice();	// 배송비 추가 함수
				cart.add(bkt);	// 장바구니에 상품 추가
			}
			else if (type == 1 && type1 == "y") System.out.println("꽃바구니는 이미 장바구니에 담겨있습니다."); // 장바구니에 꽃바구니 상품 담겨있고 꽃바구니 타입을 선택했을 때
			if(type == 2 && type2 != "y") { // 장바구니에 꽃다발 상품 담겨있지 않고 꽃다발 타입을 선택했을 때
				System.out.print("포장지 색상 : "); color = in.next();
				Bouquet bqt = new Bouquet(color, type);
				for(int i=0; i<3; i++) {
					System.out.print("add: "); num = in.nextInt(); fqty = in.nextInt();
					boolean found = false; // 선택한 번호에 해당하는 꽃의 여부 확인 변수
					for(int j=0; j<flowers.size(); j++) {
						if(num == j+1) { // 선택한 번호에 해당하는 꽃이 있을 경우
							fname = flowers.get(j).getName();
							fcolor = flowers.get(j).getColor();
							price += flowers.get(j).getPrice() * fqty;
							cf = new CartFlower(fname, fqty, fcolor);
							bqt.getFlower().add(cf);
							found = true;
						}
					}
					if(!found) { // 선택한 번호에 해당하는 꽃이 없는 경우
						System.out.println("해당하는 꽃이 없습니다.");
						i--; // 상품 선택 횟수 증가 된 것 다시 감소
					}
				}
				bqt.price = price;
				bqt.dprice(); // 배송비 추가 함수
				cart.add(bqt); // 장바구니에 상품 추가
			}
			else if (type == 2 && type2 == "y") System.out.println("꽃다발은 이미 장바구니에 담겨있습니다."); // 장바구니에 꽃다발 상품 담겨있고 꽃다발 타입을 선택했을 때
		}
		else System.out.println("장바구니가 가득찼습니다.");	// 장바구니에 담긴 상품이 2개가 넘을 때
	}
	
	private void cartshow() {	// 장바구니 목록 출력
		int i = 1;
		for(CartList c : cart) {
			System.out.println("[" + i++ + "]");
			c.show();	// 상품 정보 출력
		}
	}
	
	public void cart() { // 장바구니 목록 보여주고 주문을 도와주는 함수
		int cnt = cart.size();	// 장바구니 상품 수량
		if(cnt > 0) {	// 장바구니가 비어있지 않으면
			cartshow();			// 장바구니 목록 출력
			char order;			// 주문 여부 받는 변수
			System.out.print("주문하시겠습니까?(y/n): "); order = in.next().charAt(0);	// 주문 여부를 물어봄
			if(order == 'y') {	// 주문할 경우
				String email;	// 주문자 이메일
				System.out.print("이메일: "); email = in.next();
				order(email);	// 주문
				for(CartList c : cart) {
					for(int i=2; i>=0; i--) {
						c.getFlower().remove(i);	// 장바구니에 담긴 상품(꽃다발or꽃바구니)에서 선택한 꽃들 삭제 
					}
				}
				for(int i=cnt-1; i>=0; i--) {
					cart.remove(i);	// 장바구니에 담긴 상품 삭제
				}
			}
			else System.out.println("주문을 취소하였습니다.");	// 주문 하지 않을 경우
		}
		else System.out.println("장바구니가 비어있습니다.");	// 장바구니가 비어있을 경우
	}
	
	public void cartdel() {	// 장바구니에 담긴 상품 삭제 함수
		int num, cnt = cart.size();	// 삭제할 상품 번호, 장바구니에 담긴 상품 개수
		boolean found = false;	// 장바구니에 삭제할 상품번호 존재 여부 확인 변수
		cartshow();	// 장바구니 목록 출력
		System.out.print("삭제 번호 : "); num = in.nextInt();
		for(int i=cnt-1; i>=0; i--) {
			if(num == i+1) {
				for(int j=cart.get(i).getFlower().size()-1; j>=0; j--)	cart.get(i).getFlower().remove(j); // 해당 상품에서 선택한 꽃들 삭제
				cart.remove(i);	// 해당 상품 삭제
				found = true;	// 삭제할 상품번호 존재
			}
		}
		if(!found) System.out.println("장바구니는  " + cnt + "번까지 있습니다.");	// 장바구니에 입력한 상품번호가 없을 경우
		else System.out.println("삭제되었습니다.");	// 상품이 삭제되었을 경우
	}
	
	public void order(String email) {	// 주문을 저장하는 함수
		OrderList list = null;
		CartFlower f;
		boolean found = false;	// 주문자 이메일 여부 확인 변수
		int no=order.size();	// 주문 개수
		for(CartList c : cart) {
			if(!found) {	// 들어온 주문자 이메일로 처음 주문을 받을 경우
				list = new OrderList(email);
				order.add(list);
				found = true;
			}
			if(c.type == 1) {	// 장바구니에 담긴 상품이 꽃바구니일 경우
				order.get(no).setColor1(c.color);
				int idx = order.get(no).getPidx1();
				for(int i=0; i<c.getFlower().size(); i++) { // 장바구니에 담긴 상품에서 선택한 꽃 저장
					f = new CartFlower(c.getFlower().get(i).getFname(), c.getFlower().get(i).getFqty(), c.getFlower().get(i).getFcolor());
					order.get(no).getList1().add(f); // 꽃 하나 저장
					idx++;
					order.get(no).setIdx1(idx);	// 꽃바구니 주문의 꽃들 저장하는 배열의 마지막 인덱스 기록
				}
				order.get(no).setPrice1(c.price); // 상품 가격 저장
			}
			else if(c.type == 2) {	// 장바구니에 담긴 상품이 꽃다발일 경우
				order.get(no).setColor2(c.color);
				int idx = order.get(no).getPidx2();
				for(int i=0; i<c.getFlower().size(); i++) { // 장바구니에 담긴 상품에서 선택한 꽃 저장
					f = new CartFlower(c.getFlower().get(i).getFname(), c.getFlower().get(i).getFqty(), c.getFlower().get(i).getFcolor());
					order.get(no).getList2().add(f); // 꽃 하나 저장
					idx++;
					order.get(no).setIdx2(idx);	// 꽃다발 주문의 꽃들 저장하는 배열의 마지막 인덱스 기록
				}
				order.get(no).setPrice2(c.price);	// 상품 가격 저장
			}
		}
		System.out.println("주문이 완료되었습니다.");
	}

	public void ordercheck() {	// 주문 검색 함수
		String email;	// 주문자 이메일
		boolean found = false;	// 주문자 이메일의 주문이 있는지 확인 여부 함수
		int cnt = order.size();	// 주문 개수
		int no=0;
		System.out.print("검색할 주문의 주문자 이메일 : "); email=in.next();
		for(OrderList o : order) {
			if(o.getEmail().equals(email)) {	// 주문자 이메일의 주문이 있을 경우
				int idx1 = o.getPidx1(), idx2 = o.getPidx2();	// 주문한 꽃들이 저장되는 배열에서 주문자 주문에 해당하는 첫번째 꽃의 인덱스(각각 꽃바구니, 꽃다발의 꽃 인덱스)
				if(o.getColor1() != null) {	// 꽃바구니 바구니 컬러가 있을 때(꽃바구니를 주문했다는 의미)
					System.out.print("["+((no++)+1)+"]");
					System.out.println("바구니 색상 : "+o.getColor1());
					for(int i=0; i<3; i++) { // 주문한 꽃들 출력
						System.out.println(o.getList1().get(idx1).getFname()+" : "+o.getList1().get(idx1).getFcolor()+" : "+o.getList1().get(idx1).getFqty()+"송이");
						idx1++; // 꽃의 인덱스 증가(다음 꽃으로 이동)
					}
					System.out.println("가격 : "+o.getPrice1());	// 꽃바구니 가격 출력
				}
				if(o.getColor2()!= null) { // 꽃다발 포장지 컬러가 있을 때(꽃다발을 주문했다는 의미)
					System.out.print("["+((no++)+1)+"]");
					System.out.println("포장지 색상 : "+o.getColor2());
					for(int i=0; i<3; i++) { // 주문한 꽃들 출력
						System.out.println(o.getList2().get(idx2).getFname()+" : "+o.getList2().get(idx2).getFcolor()+" : "+o.getList2().get(idx2).getFqty()+"송이");
						idx2++;	// 꽃의 인덱스 증가(다음 꽃으로 이동)
					}
					System.out.println("가격 : "+o.getPrice2()); // 꽃다발 가격 출력
				}
				found = true;	// 이메일에 대응하는 주문이 있음					
			}
		}
		if(!found) System.out.println("해당하는 이메일이 없습니다.");	// 이메일에 대응하는 주문이 없을 경우
	}
	
	public void orderdel() { //주문 취소 함수
		String email;	// 주문자 이메일
		boolean found = false;	// 주문자 이메일의 주문이 있는지 확인 여부 함수
		int cnt = order.size();	// 주문 개수
		System.out.print("삭제할 주문의 주문자 이메일 : "); email=in.next();
		for(int i=cnt-1; i>=0; i--) {
			if(order.get(i).getEmail().equals(email)) {	// 주문자 이메일의 주문이 있는 경우
				int idx1 = order.get(i).getPidx1(), idx2 = order.get(i).getPidx2(); // 주문한 꽃들이 저장되는 배열에서 주문자 주문에 해당하는 첫번째 꽃의 인덱스(각각 꽃바구니, 꽃다발의 꽃 인덱스)
				if(order.get(i).getColor1() != null) { // 꽃바구니 바구니 컬러가 있을 때(꽃바구니를 주문이 있다는 의미)
					order.get(i).getList1().size();
					if(order.size()-i != 1 && i !=0) { // 삭제할 주문이 주문 배열에서 첫 주문과 마지막 주문이 아닐 때
						for(int k=i+1; k<=order.size()-i; k++) { // 해당 주문 이후 모든 주문의 첫번째 꽃 인덱스-3
							order.get(k).setPidx1(order.get(k).getPidx1()-3);
						}
						for(int j=idx1+2; j>=idx1; j--) {	// 해당 주문의 꽃들 삭제
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) {	// 마지막 주문에 꽃바구니 주문이 있을 때
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()+3); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
					}
					else if(order.size()-i == 1) { // 삭제할 주문이 주문 배열에서 마지막 주문 일 때
						for(int j=idx1+2; j>=idx1; j--) {	// 마지막 주문의 꽃들 삭제
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) { // 마지막 주문에 꽃바구니 주문이 있을 때
							order.get(i).setIdx1(order.get(i).getPidx1()-3); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx1(order.get(i).getPidx1()); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
					}
					else if(i == 0) { // 삭제할 주문이 주문 배열에서 첫 주문 일 때
						for(int k=1; k<=order.size()-1; k++) { // 첫 주문 제외 모든 주문의 첫번째 꽃 인덱스-3
							order.get(k).setPidx1(order.get(k).getPidx1()-3);	
						}
						for(int j=idx1+2; j>=idx1; j--) {	// 첫 주문의 꽃들 삭제
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) { // 마지막 주문에 꽃바구니 주문이 있을 때
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()+3); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()); // list1에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
					}
				}
				if(order.get(i).getColor2() != null){ // 꽃다발 바구니 컬러가 있을 때(꽃다발를 주문이 있다는 의미)
					if(order.size()-i != 1 && i!=0) { // 삭제할 주문이 주문 배열에서 첫 주문과 마지막 주문이 아닐 때
						for(int k=i+1; k<=order.size()-i; k++) { // 해당 주문 이후 모든 주문의 첫번째 꽃 인덱스-3
							order.get(k).setPidx2(order.get(k).getPidx2()-3);	
						}
						for(int j=idx2+2; j>=idx2; j--) {	// 해당 주문의 꽃들 삭제
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // 마지막 주문에 꽃다발 주문이 있을 때
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()+3); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
					}
					else if(order.size()-i == 1) { // 삭제할 주문이 주문 배열에서 마지막 주문 일 때
						for(int j=idx2+2; j>=idx2; j--) {	// 마지막 주문의 꽃들 삭제
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // 마지막 주문에 꽃다발 주문이 있을 때
							order.get(i).setIdx2(order.get(i).getPidx2()-3); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx2(order.get(i).getPidx2()); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}	
					}
					else if(i == 0) { // 삭제할 주문이 주문 배열에서 첫 주문 일 때
						for(int k=1; k<=order.size()-1; k++) { // 첫 주문 제외 모든 주문의 첫번째 꽃 인덱스-3
							order.get(k).setPidx2(order.get(k).getPidx2()-3);
						}
						for(int j=idx2+2; j>=idx2; j--) {	// 첫 주문의 꽃들 삭제
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // 마지막 주문에 꽃다발 주문이 있을 때
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()+3); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
						else {
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()); // list2에서 다음 주문의 꽃이 저장 될 인덱스 변경
						}
					}
				}
				order.remove(i); // 해당 주문 삭제
				found = true; //주문자 이메일에 해당하는 주문이 있음
			}
		}
		if(found == true) System.out.println("삭제되었습니다.");	// 주문자 이메일에 해당하는 주문이 있을 때
		else if(!found) System.out.println("해당하는 이메일이 없습니다."); // 주문자 이메일에 해당하는 주문이 없을 때
	}
	
	public void osave() {	// 주문 목록 파일에 저장하는 함수
		String orderlist = "orderlist.txt";
		int idx;
		try {
			FileWriter writer = new FileWriter(orderlist);
			BufferedWriter buf = new BufferedWriter(writer);
			for(OrderList o : order) {
				if(o.getColor1() != null) {	// 꽃바구니 바구니 컬러가 있을 때(꽃바구니를 주문했다는 의미)
					idx = o.getPidx1();
					buf.write("1/");	// 타입 1(꽃바구니)
					buf.write(o.getEmail()+"/");
					buf.write(o.getColor1()+"/");
					buf.write(o.getPrice1()+"/");
					for(int i=0; i<3; i++) {	// 해당 상품에서 주문한 꽃들 저장
						buf.write(o.getList1().get(idx).getFname()+"/");
						buf.write(o.getList1().get(idx).getFcolor()+"/");
						if(i==2) buf.write(String.valueOf(o.getList1().get(idx).getFqty())); // 마지막 3번째 꽃의 수량은 뒤에 / 없이 저장
						else buf.write(o.getList1().get(idx++).getFqty()+"/");
					}
					buf.newLine();
				}
				if(o.getColor2() != null) { // 꽃다발 바구니 컬러가 있을 때(꽃다발을 주문했다는 의미)
					idx = o.getPidx2();
					buf.write("2/");	// 타입 2(꽃다발)
					buf.write(o.getEmail()+"/");
					buf.write(o.getColor2()+"/");
					buf.write(o.getPrice2()+"/");
					for(int i=0; i<3; i++) { // 해당 상품에서 주문한 꽃들 저장
						buf.write(o.getList2().get(idx).getFname()+"/");
						buf.write(o.getList2().get(idx).getFcolor()+"/");
						if(i==2) buf.write(String.valueOf(o.getList2().get(idx).getFqty())); // 마지막 3번째 꽃의 수량은 뒤에 / 없이 저장
						else buf.write(o.getList2().get(idx++).getFqty()+"/");
					}
					buf.newLine();
				}
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void oload() {	// 이전실행들에서 주문한 주문 목록 담은 파일 로드하는 함수
		if(!e) {	// 한번도 로드하지 않았을 경우
			String orderlist = "orderlist.txt";
			OrderList list = null;
			CartFlower f;
			int idx=0;	// 주문 배열 인덱스를 지정할 함수
			try {
				FileReader reader = new FileReader(orderlist);
				BufferedReader buf = new BufferedReader(reader);
				String line;
				while((line = buf.readLine()) != null) {
					boolean found = false;	// 주문 배열에 동일한 이메일이 있는지 여부 확인 변수
					StringTokenizer token = new StringTokenizer(line, "/");
					int type = Integer.parseInt(token.nextToken());
					String email = token.nextToken();
					String color = token.nextToken();
					int price = Integer.parseInt(token.nextToken());
					for(int i=0; i<order.size(); i++) {
						if(order.get(i).getEmail().equals(email)) {	// 주문 배열에 동일한 이메일이 있는 경우
							idx=i;	// 동일 이메일 주문의 인덱스로 변경
							found = true;
						}
					}
					if(!found) {	// 주문 배열에 동일 이메일이 없는 경우
						list = new OrderList(email);
						order.add(list);	// 주문 새로 생성
					}
					if(type == 1) {	// 꽃바구니 주문인 경우
						int id = order.get(idx).getPidx1();
						for(int i=0; i<3; i++) { // 주문한 꽃들 저장
							String fname = token.nextToken();
							String fcolor = token.nextToken();
							int fqty = Integer.parseInt(token.nextToken());
							f = new CartFlower(fname, fqty,fcolor);
							order.get(idx).getList1().add(f);	// 주문한 꽃 하나 저장
							id++;
							order.get(idx).setIdx1(id);
						}
						order.get(idx).setColor1(color);	// 바구니 색상 저장
						order.get(idx).setPrice1(price);	// 꽃바구니 가격 저장
					}
					else if(type == 2) {	// 꽃다발인 경우
						int id = order.get(idx).getPidx2();
						for(int i=0; i<3; i++) { // 주문한 꽃들 저장
							String fname = token.nextToken();
							String fcolor = token.nextToken();
							int fqty = Integer.parseInt(token.nextToken());
							f = new CartFlower(fname, fqty,fcolor);
							order.get(idx).getList2().add(f); // 주문한 꽃 하나 저장
							id++;
							order.get(idx).setIdx2(id);
						}
						order.get(idx).setColor2(color); // 포장지 색상 저장
						order.get(idx).setPrice2(price); // 꽃다발 가격 저장
					}
					idx++;	// 주문 배열 인덱스 증가
				}
				buf.close();
				e = true;	// 로드 했음
			} catch (FileNotFoundException e) {
				System.out.println("File open error : "+orderlist+" 파일을 찾을 수 없습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
