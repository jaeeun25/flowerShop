import java.util.Scanner;

public class FlowerMain {
	public static void menu() {	// Flower Shop �޴� ��� �Լ�
		System.out.println("**Flower Shop**");
		System.out.println("1. ��ǰ����");
		System.out.println("2. ��ٱ���");
		System.out.println("3. ��ٱ������");
		System.out.println("4. �ֹ�Ȯ��");
		System.out.println("5. �ֹ����");
		System.out.println("6. ������");
		System.out.println("0. ����");
	}
	public static void admin(FlowerAdmin a) { // ������ ������ ������ �� �ִ� �Լ�
		Scanner in = new Scanner(System.in);
		int choice = 0, pwd, apwd = 20191478;	// apwd: ������ ���� ��й�ȣ
		String email, aemail = "fadmin@daejin.ac.kr";	// aemail: ������ ���� �̸���
		a.fload();	// ���� ����鿡�� ����� �� ����� ����Ǿ� �ִ� ���� �ε�
		System.out.print("email : "); email = in.next(); // ������  email �Է�
		System.out.print("password : "); pwd = in.nextInt();	// ������ ��й�ȣ �Է�
		if(email.equals(aemail) && pwd == apwd) {	// ������ ���� �ùٸ��� �Է½� ������ ������ ����
			System.out.println("\n**Admin Page**");
			System.out.println("1. �� �߰�");
			System.out.println("2. �� ���");
			System.out.println("3. �� ����");
			while(true) {
				System.out.print("Choice(0:Exit) > "); choice = in.nextInt();
				if(choice == 0) {	// 0 �Է� �� �� ��� ���Ͽ� ���� �� ������ ���������� ����
					a.fsave();
					break;
				}
				switch(choice) {
				case 1: 
					String name, color;	// ����� �� �̸�, �� ����
					int price;	// �� ���� (1���� ����)
					boolean f = false; // �̹� ��ϵ� ��ǰ���� Ȯ���ϴ� ����
					System.out.print("�߰��� �� �̸�, 1���� ����, ���� : "); name=in.next();  price=in.nextInt(); color=in.next();
					for(int i=0; i<a.getFlowers().size(); i++) {
						if(a.getFlowers().get(i).getName().equals(name) && a.getFlowers().get(i).getColor().equals(color)) { // �� ��Ͽ� �Է��� ���̸��� ������ ������ ���� ������
							System.out.println("�̹� ��ϵ� ���Դϴ�");
							f = true;
						}
					}
					if(!f) a.fadd(name, price, color); // ��ϵ� ��ǰ�� �ƴϸ� �� ���
					break;
				case 2: a.fshow(); break;	// ��ϵ� �� ��� ���
				case 3: a.fdel(); break;	// ��ϵ� �� ����
				}
			}
		}
		else {
			System.out.println("�����ڰ����� �ƴմϴ�. ������������ ���ư��ϴ�.");	// ������ ���� �ùٸ��� �Է����� ������ ���
		}
	}
	
	public static void main(String[] args) {
		menu();	// Flower Shop �޴� ���
		FlowerManager manager = new FlowerManager();
		FlowerAdmin admin = new FlowerAdmin("flowerlist.txt");
		Scanner in = new Scanner(System.in);
		int choice;
		while(true) {
			System.out.print("Choice(0:Exit) > "); choice = in.nextInt();
			if(choice == 0) {
				manager.osave();	// 0 �Է� �� �ֹ� ��� ���Ͽ� �����ϰ� ���α׷� ����
				break;
			}
			switch(choice) {
				case 1: manager.buy(admin); break;	// ��ǰ ���� �� ��ٱ��Ͽ� �߰�
				case 2: manager.cart(); break;		// ��ٱ��� ��� ��� �� �ֹ�
				case 3: manager.cartdel();break;	// ��ٱ��� ����
				case 4: {
					manager.ordercheck(); break;	// �ֹ� �˻�
				}
				case 5: manager.orderdel(); break;	// �ֹ� ���
				case 6: 
					admin(admin);					// ������ ������ ���� �Լ� ����
					System.out.print("\n");
					menu();							// Flower Shop �޴� ���
					break;
			}
		}
		 
	}

}
