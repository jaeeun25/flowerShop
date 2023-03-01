import java.util.Scanner;

public class FlowerMain {
	public static void menu() {	// Flower Shop 메뉴 출력 함수
		System.out.println("**Flower Shop**");
		System.out.println("1. 상품선택");
		System.out.println("2. 장바구니");
		System.out.println("3. 장바구니취소");
		System.out.println("4. 주문확인");
		System.out.println("5. 주문취소");
		System.out.println("6. 관리자");
		System.out.println("0. 종료");
	}
	public static void admin(FlowerAdmin a) { // 관리자 페이지 입장할 수 있는 함수
		Scanner in = new Scanner(System.in);
		int choice = 0, pwd, apwd = 20191478;	// apwd: 관리자 계정 비밀번호
		String email, aemail = "fadmin@daejin.ac.kr";	// aemail: 관리자 계정 이메일
		a.fload();	// 이전 실행들에서 등록한 꽃 목록이 저장되어 있는 파일 로드
		System.out.print("email : "); email = in.next(); // 관리자  email 입력
		System.out.print("password : "); pwd = in.nextInt();	// 관리자 비밀번호 입력
		if(email.equals(aemail) && pwd == apwd) {	// 관리자 계정 올바르게 입력시 관리자 페이지 입장
			System.out.println("\n**Admin Page**");
			System.out.println("1. 꽃 추가");
			System.out.println("2. 꽃 목록");
			System.out.println("3. 꽃 삭제");
			while(true) {
				System.out.print("Choice(0:Exit) > "); choice = in.nextInt();
				if(choice == 0) {	// 0 입력 시 꽃 목록 파일에 저장 후 관리자 페이지에서 나감
					a.fsave();
					break;
				}
				switch(choice) {
				case 1: 
					String name, color;	// 등록할 꽃 이름, 꽃 색상
					int price;	// 꽃 가격 (1송이 기준)
					boolean f = false; // 이미 등록된 상품인지 확인하는 변수
					System.out.print("추가할 꽃 이름, 1송이 가격, 색상 : "); name=in.next();  price=in.nextInt(); color=in.next();
					for(int i=0; i<a.getFlowers().size(); i++) {
						if(a.getFlowers().get(i).getName().equals(name) && a.getFlowers().get(i).getColor().equals(color)) { // 꽃 목록에 입력한 꽃이름과 색상이 동일한 꽃이 있으면
							System.out.println("이미 등록된 꽃입니다");
							f = true;
						}
					}
					if(!f) a.fadd(name, price, color); // 등록된 상품이 아니면 꽃 등록
					break;
				case 2: a.fshow(); break;	// 등록된 꽃 목록 출력
				case 3: a.fdel(); break;	// 등록된 꽃 삭제
				}
			}
		}
		else {
			System.out.println("관리자계정이 아닙니다. 이전페이지로 돌아갑니다.");	// 관리자 계정 올바르게 입력하지 못했을 경우
		}
	}
	
	public static void main(String[] args) {
		menu();	// Flower Shop 메뉴 출력
		FlowerManager manager = new FlowerManager();
		FlowerAdmin admin = new FlowerAdmin("flowerlist.txt");
		Scanner in = new Scanner(System.in);
		int choice;
		while(true) {
			System.out.print("Choice(0:Exit) > "); choice = in.nextInt();
			if(choice == 0) {
				manager.osave();	// 0 입력 시 주문 목록 파일에 저장하고 프로그램 종료
				break;
			}
			switch(choice) {
				case 1: manager.buy(admin); break;	// 상품 선택 및 장바구니에 추가
				case 2: manager.cart(); break;		// 장바구니 목록 출력 및 주문
				case 3: manager.cartdel();break;	// 장바구니 삭제
				case 4: {
					manager.ordercheck(); break;	// 주문 검색
				}
				case 5: manager.orderdel(); break;	// 주문 취소
				case 6: 
					admin(admin);					// 관리자 페이지 입장 함수 실행
					System.out.print("\n");
					menu();							// Flower Shop 메뉴 출력
					break;
			}
		}
		 
	}

}
