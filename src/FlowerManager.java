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
	private static ArrayList<CartList> cart;	// ��ٱ���
	private static ArrayList<OrderList> order;	// �ֹ���� �迭
	private static boolean e = false;	// �ֹ� ��� ���� ���� �ε忩�� Ȯ�� ����
	
	public FlowerManager() {
		cart = new ArrayList<>();
		order = new ArrayList<>();
		oload();	// ���������� �ֹ� ����� ����Ǿ� �ִ� ���� �ε�
	}

	public void buy(FlowerAdmin admin) {	// ��ǰ ���� �� ��ٱ��� �߰� �Լ�
		if(cart.size() < 2) { // ��ٱ��� ��� ��ǰ�� 2�� �̸��� ��
			ArrayList<Flower> flowers = FlowerAdmin.getFlowers();
			CartFlower cf;
			admin.fshow();	// ��ϵ� �� ��� ���
			System.out.println("  * �� 3���� ����(�ߺ� ����)");
			System.out.println("  * 10�����̻� ��ۺ� ����");
			int num, fqty, type, price=0;	// ��ǰ(��)��ȣ, ��ǰ(��)����, �ɴٹ�or�ɹٱ��� Ÿ�� ���� ����, �Ѱ���
			String fname, fcolor, color, type1 = null, type2 = null; // ��ǰ(��)�̸�, ��ǰ(��)����, ������or�ٱ��� ����, ��ٱ��Ͽ� �ɹٱ��Ͽ� �ɴٹ� ��ǰ ���� Ȯ�� ����
			for(int i=0; i<cart.size(); i++) {
				if(cart.get(i).type == 1) type1 = "y"; // ��ٱ��Ͽ� �ɹٱ��� ��ǰ�� ����ִ� ���
				else type2 = "y";	// ��ٱ��Ͽ� �ɴٹ� ��ǰ�� ����ִ� ���
			}
			System.out.print("1.�ɹٱ��� 2.�ɴٹ� : "); type = in.nextInt();
			if(type == 1 && type1 != "y") {	// ��ٱ��Ͽ� �ɹٱ��� ��ǰ ������� �ʰ� �ɹٱ��� Ÿ���� �������� ��
				System.out.print("�ٱ��� ���� : "); color = in.next();
				Basket bkt = new Basket(color, type);
				for(int i=0; i<3; i++) {
					System.out.print("add: "); num = in.nextInt(); fqty = in.nextInt();
					boolean found = false;	// ������ ��ȣ�� �ش��ϴ� ���� ���� Ȯ�� ����
					for(int j=0; j<flowers.size(); j++) {
						if(num == j+1) { // ������ ��ȣ�� �ش��ϴ� ���� ���� ���
							fname = flowers.get(j).getName();
							fcolor = flowers.get(j).getColor();
							price += flowers.get(j).getPrice() * fqty;
							cf = new CartFlower(fname, fqty, fcolor);
							bkt.getFlower().add(cf);
							found = true;
						}
					}
					if(!found) { // ������ ��ȣ�� �ش��ϴ� ���� ���� ���
						System.out.println("�ش��ϴ� ���� �����ϴ�.");
						i--; // ��ǰ ���� Ƚ�� ���� �� �� �ٽ� ����
					}
				}
				bkt.price = price;
				bkt.dprice();	// ��ۺ� �߰� �Լ�
				cart.add(bkt);	// ��ٱ��Ͽ� ��ǰ �߰�
			}
			else if (type == 1 && type1 == "y") System.out.println("�ɹٱ��ϴ� �̹� ��ٱ��Ͽ� ����ֽ��ϴ�."); // ��ٱ��Ͽ� �ɹٱ��� ��ǰ ����ְ� �ɹٱ��� Ÿ���� �������� ��
			if(type == 2 && type2 != "y") { // ��ٱ��Ͽ� �ɴٹ� ��ǰ ������� �ʰ� �ɴٹ� Ÿ���� �������� ��
				System.out.print("������ ���� : "); color = in.next();
				Bouquet bqt = new Bouquet(color, type);
				for(int i=0; i<3; i++) {
					System.out.print("add: "); num = in.nextInt(); fqty = in.nextInt();
					boolean found = false; // ������ ��ȣ�� �ش��ϴ� ���� ���� Ȯ�� ����
					for(int j=0; j<flowers.size(); j++) {
						if(num == j+1) { // ������ ��ȣ�� �ش��ϴ� ���� ���� ���
							fname = flowers.get(j).getName();
							fcolor = flowers.get(j).getColor();
							price += flowers.get(j).getPrice() * fqty;
							cf = new CartFlower(fname, fqty, fcolor);
							bqt.getFlower().add(cf);
							found = true;
						}
					}
					if(!found) { // ������ ��ȣ�� �ش��ϴ� ���� ���� ���
						System.out.println("�ش��ϴ� ���� �����ϴ�.");
						i--; // ��ǰ ���� Ƚ�� ���� �� �� �ٽ� ����
					}
				}
				bqt.price = price;
				bqt.dprice(); // ��ۺ� �߰� �Լ�
				cart.add(bqt); // ��ٱ��Ͽ� ��ǰ �߰�
			}
			else if (type == 2 && type2 == "y") System.out.println("�ɴٹ��� �̹� ��ٱ��Ͽ� ����ֽ��ϴ�."); // ��ٱ��Ͽ� �ɴٹ� ��ǰ ����ְ� �ɴٹ� Ÿ���� �������� ��
		}
		else System.out.println("��ٱ��ϰ� ����á���ϴ�.");	// ��ٱ��Ͽ� ��� ��ǰ�� 2���� ���� ��
	}
	
	private void cartshow() {	// ��ٱ��� ��� ���
		int i = 1;
		for(CartList c : cart) {
			System.out.println("[" + i++ + "]");
			c.show();	// ��ǰ ���� ���
		}
	}
	
	public void cart() { // ��ٱ��� ��� �����ְ� �ֹ��� �����ִ� �Լ�
		int cnt = cart.size();	// ��ٱ��� ��ǰ ����
		if(cnt > 0) {	// ��ٱ��ϰ� ������� ������
			cartshow();			// ��ٱ��� ��� ���
			char order;			// �ֹ� ���� �޴� ����
			System.out.print("�ֹ��Ͻðڽ��ϱ�?(y/n): "); order = in.next().charAt(0);	// �ֹ� ���θ� ���
			if(order == 'y') {	// �ֹ��� ���
				String email;	// �ֹ��� �̸���
				System.out.print("�̸���: "); email = in.next();
				order(email);	// �ֹ�
				for(CartList c : cart) {
					for(int i=2; i>=0; i--) {
						c.getFlower().remove(i);	// ��ٱ��Ͽ� ��� ��ǰ(�ɴٹ�or�ɹٱ���)���� ������ �ɵ� ���� 
					}
				}
				for(int i=cnt-1; i>=0; i--) {
					cart.remove(i);	// ��ٱ��Ͽ� ��� ��ǰ ����
				}
			}
			else System.out.println("�ֹ��� ����Ͽ����ϴ�.");	// �ֹ� ���� ���� ���
		}
		else System.out.println("��ٱ��ϰ� ����ֽ��ϴ�.");	// ��ٱ��ϰ� ������� ���
	}
	
	public void cartdel() {	// ��ٱ��Ͽ� ��� ��ǰ ���� �Լ�
		int num, cnt = cart.size();	// ������ ��ǰ ��ȣ, ��ٱ��Ͽ� ��� ��ǰ ����
		boolean found = false;	// ��ٱ��Ͽ� ������ ��ǰ��ȣ ���� ���� Ȯ�� ����
		cartshow();	// ��ٱ��� ��� ���
		System.out.print("���� ��ȣ : "); num = in.nextInt();
		for(int i=cnt-1; i>=0; i--) {
			if(num == i+1) {
				for(int j=cart.get(i).getFlower().size()-1; j>=0; j--)	cart.get(i).getFlower().remove(j); // �ش� ��ǰ���� ������ �ɵ� ����
				cart.remove(i);	// �ش� ��ǰ ����
				found = true;	// ������ ��ǰ��ȣ ����
			}
		}
		if(!found) System.out.println("��ٱ��ϴ�  " + cnt + "������ �ֽ��ϴ�.");	// ��ٱ��Ͽ� �Է��� ��ǰ��ȣ�� ���� ���
		else System.out.println("�����Ǿ����ϴ�.");	// ��ǰ�� �����Ǿ��� ���
	}
	
	public void order(String email) {	// �ֹ��� �����ϴ� �Լ�
		OrderList list = null;
		CartFlower f;
		boolean found = false;	// �ֹ��� �̸��� ���� Ȯ�� ����
		int no=order.size();	// �ֹ� ����
		for(CartList c : cart) {
			if(!found) {	// ���� �ֹ��� �̸��Ϸ� ó�� �ֹ��� ���� ���
				list = new OrderList(email);
				order.add(list);
				found = true;
			}
			if(c.type == 1) {	// ��ٱ��Ͽ� ��� ��ǰ�� �ɹٱ����� ���
				order.get(no).setColor1(c.color);
				int idx = order.get(no).getPidx1();
				for(int i=0; i<c.getFlower().size(); i++) { // ��ٱ��Ͽ� ��� ��ǰ���� ������ �� ����
					f = new CartFlower(c.getFlower().get(i).getFname(), c.getFlower().get(i).getFqty(), c.getFlower().get(i).getFcolor());
					order.get(no).getList1().add(f); // �� �ϳ� ����
					idx++;
					order.get(no).setIdx1(idx);	// �ɹٱ��� �ֹ��� �ɵ� �����ϴ� �迭�� ������ �ε��� ���
				}
				order.get(no).setPrice1(c.price); // ��ǰ ���� ����
			}
			else if(c.type == 2) {	// ��ٱ��Ͽ� ��� ��ǰ�� �ɴٹ��� ���
				order.get(no).setColor2(c.color);
				int idx = order.get(no).getPidx2();
				for(int i=0; i<c.getFlower().size(); i++) { // ��ٱ��Ͽ� ��� ��ǰ���� ������ �� ����
					f = new CartFlower(c.getFlower().get(i).getFname(), c.getFlower().get(i).getFqty(), c.getFlower().get(i).getFcolor());
					order.get(no).getList2().add(f); // �� �ϳ� ����
					idx++;
					order.get(no).setIdx2(idx);	// �ɴٹ� �ֹ��� �ɵ� �����ϴ� �迭�� ������ �ε��� ���
				}
				order.get(no).setPrice2(c.price);	// ��ǰ ���� ����
			}
		}
		System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
	}

	public void ordercheck() {	// �ֹ� �˻� �Լ�
		String email;	// �ֹ��� �̸���
		boolean found = false;	// �ֹ��� �̸����� �ֹ��� �ִ��� Ȯ�� ���� �Լ�
		int cnt = order.size();	// �ֹ� ����
		int no=0;
		System.out.print("�˻��� �ֹ��� �ֹ��� �̸��� : "); email=in.next();
		for(OrderList o : order) {
			if(o.getEmail().equals(email)) {	// �ֹ��� �̸����� �ֹ��� ���� ���
				int idx1 = o.getPidx1(), idx2 = o.getPidx2();	// �ֹ��� �ɵ��� ����Ǵ� �迭���� �ֹ��� �ֹ��� �ش��ϴ� ù��° ���� �ε���(���� �ɹٱ���, �ɴٹ��� �� �ε���)
				if(o.getColor1() != null) {	// �ɹٱ��� �ٱ��� �÷��� ���� ��(�ɹٱ��ϸ� �ֹ��ߴٴ� �ǹ�)
					System.out.print("["+((no++)+1)+"]");
					System.out.println("�ٱ��� ���� : "+o.getColor1());
					for(int i=0; i<3; i++) { // �ֹ��� �ɵ� ���
						System.out.println(o.getList1().get(idx1).getFname()+" : "+o.getList1().get(idx1).getFcolor()+" : "+o.getList1().get(idx1).getFqty()+"����");
						idx1++; // ���� �ε��� ����(���� ������ �̵�)
					}
					System.out.println("���� : "+o.getPrice1());	// �ɹٱ��� ���� ���
				}
				if(o.getColor2()!= null) { // �ɴٹ� ������ �÷��� ���� ��(�ɴٹ��� �ֹ��ߴٴ� �ǹ�)
					System.out.print("["+((no++)+1)+"]");
					System.out.println("������ ���� : "+o.getColor2());
					for(int i=0; i<3; i++) { // �ֹ��� �ɵ� ���
						System.out.println(o.getList2().get(idx2).getFname()+" : "+o.getList2().get(idx2).getFcolor()+" : "+o.getList2().get(idx2).getFqty()+"����");
						idx2++;	// ���� �ε��� ����(���� ������ �̵�)
					}
					System.out.println("���� : "+o.getPrice2()); // �ɴٹ� ���� ���
				}
				found = true;	// �̸��Ͽ� �����ϴ� �ֹ��� ����					
			}
		}
		if(!found) System.out.println("�ش��ϴ� �̸����� �����ϴ�.");	// �̸��Ͽ� �����ϴ� �ֹ��� ���� ���
	}
	
	public void orderdel() { //�ֹ� ��� �Լ�
		String email;	// �ֹ��� �̸���
		boolean found = false;	// �ֹ��� �̸����� �ֹ��� �ִ��� Ȯ�� ���� �Լ�
		int cnt = order.size();	// �ֹ� ����
		System.out.print("������ �ֹ��� �ֹ��� �̸��� : "); email=in.next();
		for(int i=cnt-1; i>=0; i--) {
			if(order.get(i).getEmail().equals(email)) {	// �ֹ��� �̸����� �ֹ��� �ִ� ���
				int idx1 = order.get(i).getPidx1(), idx2 = order.get(i).getPidx2(); // �ֹ��� �ɵ��� ����Ǵ� �迭���� �ֹ��� �ֹ��� �ش��ϴ� ù��° ���� �ε���(���� �ɹٱ���, �ɴٹ��� �� �ε���)
				if(order.get(i).getColor1() != null) { // �ɹٱ��� �ٱ��� �÷��� ���� ��(�ɹٱ��ϸ� �ֹ��� �ִٴ� �ǹ�)
					order.get(i).getList1().size();
					if(order.size()-i != 1 && i !=0) { // ������ �ֹ��� �ֹ� �迭���� ù �ֹ��� ������ �ֹ��� �ƴ� ��
						for(int k=i+1; k<=order.size()-i; k++) { // �ش� �ֹ� ���� ��� �ֹ��� ù��° �� �ε���-3
							order.get(k).setPidx1(order.get(k).getPidx1()-3);
						}
						for(int j=idx1+2; j>=idx1; j--) {	// �ش� �ֹ��� �ɵ� ����
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) {	// ������ �ֹ��� �ɹٱ��� �ֹ��� ���� ��
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()+3); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
					}
					else if(order.size()-i == 1) { // ������ �ֹ��� �ֹ� �迭���� ������ �ֹ� �� ��
						for(int j=idx1+2; j>=idx1; j--) {	// ������ �ֹ��� �ɵ� ����
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) { // ������ �ֹ��� �ɹٱ��� �ֹ��� ���� ��
							order.get(i).setIdx1(order.get(i).getPidx1()-3); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx1(order.get(i).getPidx1()); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
					}
					else if(i == 0) { // ������ �ֹ��� �ֹ� �迭���� ù �ֹ� �� ��
						for(int k=1; k<=order.size()-1; k++) { // ù �ֹ� ���� ��� �ֹ��� ù��° �� �ε���-3
							order.get(k).setPidx1(order.get(k).getPidx1()-3);	
						}
						for(int j=idx1+2; j>=idx1; j--) {	// ù �ֹ��� �ɵ� ����
							order.get(i).getList1().remove(idx1);
						}
						if(order.get(order.size()-1).getColor1() != null) { // ������ �ֹ��� �ɹٱ��� �ֹ��� ���� ��
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()+3); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx1(order.get(order.size()-1).getPidx1()); // list1���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
					}
				}
				if(order.get(i).getColor2() != null){ // �ɴٹ� �ٱ��� �÷��� ���� ��(�ɴٹ߸� �ֹ��� �ִٴ� �ǹ�)
					if(order.size()-i != 1 && i!=0) { // ������ �ֹ��� �ֹ� �迭���� ù �ֹ��� ������ �ֹ��� �ƴ� ��
						for(int k=i+1; k<=order.size()-i; k++) { // �ش� �ֹ� ���� ��� �ֹ��� ù��° �� �ε���-3
							order.get(k).setPidx2(order.get(k).getPidx2()-3);	
						}
						for(int j=idx2+2; j>=idx2; j--) {	// �ش� �ֹ��� �ɵ� ����
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // ������ �ֹ��� �ɴٹ� �ֹ��� ���� ��
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()+3); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
					}
					else if(order.size()-i == 1) { // ������ �ֹ��� �ֹ� �迭���� ������ �ֹ� �� ��
						for(int j=idx2+2; j>=idx2; j--) {	// ������ �ֹ��� �ɵ� ����
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // ������ �ֹ��� �ɴٹ� �ֹ��� ���� ��
							order.get(i).setIdx2(order.get(i).getPidx2()-3); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx2(order.get(i).getPidx2()); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}	
					}
					else if(i == 0) { // ������ �ֹ��� �ֹ� �迭���� ù �ֹ� �� ��
						for(int k=1; k<=order.size()-1; k++) { // ù �ֹ� ���� ��� �ֹ��� ù��° �� �ε���-3
							order.get(k).setPidx2(order.get(k).getPidx2()-3);
						}
						for(int j=idx2+2; j>=idx2; j--) {	// ù �ֹ��� �ɵ� ����
							order.get(i).getList2().remove(idx2);
						}
						if(order.get(order.size()-1).getColor2() != null) { // ������ �ֹ��� �ɴٹ� �ֹ��� ���� ��
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()+3); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
						else {
							order.get(i).setIdx2(order.get(order.size()-1).getPidx2()); // list2���� ���� �ֹ��� ���� ���� �� �ε��� ����
						}
					}
				}
				order.remove(i); // �ش� �ֹ� ����
				found = true; //�ֹ��� �̸��Ͽ� �ش��ϴ� �ֹ��� ����
			}
		}
		if(found == true) System.out.println("�����Ǿ����ϴ�.");	// �ֹ��� �̸��Ͽ� �ش��ϴ� �ֹ��� ���� ��
		else if(!found) System.out.println("�ش��ϴ� �̸����� �����ϴ�."); // �ֹ��� �̸��Ͽ� �ش��ϴ� �ֹ��� ���� ��
	}
	
	public void osave() {	// �ֹ� ��� ���Ͽ� �����ϴ� �Լ�
		String orderlist = "orderlist.txt";
		int idx;
		try {
			FileWriter writer = new FileWriter(orderlist);
			BufferedWriter buf = new BufferedWriter(writer);
			for(OrderList o : order) {
				if(o.getColor1() != null) {	// �ɹٱ��� �ٱ��� �÷��� ���� ��(�ɹٱ��ϸ� �ֹ��ߴٴ� �ǹ�)
					idx = o.getPidx1();
					buf.write("1/");	// Ÿ�� 1(�ɹٱ���)
					buf.write(o.getEmail()+"/");
					buf.write(o.getColor1()+"/");
					buf.write(o.getPrice1()+"/");
					for(int i=0; i<3; i++) {	// �ش� ��ǰ���� �ֹ��� �ɵ� ����
						buf.write(o.getList1().get(idx).getFname()+"/");
						buf.write(o.getList1().get(idx).getFcolor()+"/");
						if(i==2) buf.write(String.valueOf(o.getList1().get(idx).getFqty())); // ������ 3��° ���� ������ �ڿ� / ���� ����
						else buf.write(o.getList1().get(idx++).getFqty()+"/");
					}
					buf.newLine();
				}
				if(o.getColor2() != null) { // �ɴٹ� �ٱ��� �÷��� ���� ��(�ɴٹ��� �ֹ��ߴٴ� �ǹ�)
					idx = o.getPidx2();
					buf.write("2/");	// Ÿ�� 2(�ɴٹ�)
					buf.write(o.getEmail()+"/");
					buf.write(o.getColor2()+"/");
					buf.write(o.getPrice2()+"/");
					for(int i=0; i<3; i++) { // �ش� ��ǰ���� �ֹ��� �ɵ� ����
						buf.write(o.getList2().get(idx).getFname()+"/");
						buf.write(o.getList2().get(idx).getFcolor()+"/");
						if(i==2) buf.write(String.valueOf(o.getList2().get(idx).getFqty())); // ������ 3��° ���� ������ �ڿ� / ���� ����
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
		
	public void oload() {	// ��������鿡�� �ֹ��� �ֹ� ��� ���� ���� �ε��ϴ� �Լ�
		if(!e) {	// �ѹ��� �ε����� �ʾ��� ���
			String orderlist = "orderlist.txt";
			OrderList list = null;
			CartFlower f;
			int idx=0;	// �ֹ� �迭 �ε����� ������ �Լ�
			try {
				FileReader reader = new FileReader(orderlist);
				BufferedReader buf = new BufferedReader(reader);
				String line;
				while((line = buf.readLine()) != null) {
					boolean found = false;	// �ֹ� �迭�� ������ �̸����� �ִ��� ���� Ȯ�� ����
					StringTokenizer token = new StringTokenizer(line, "/");
					int type = Integer.parseInt(token.nextToken());
					String email = token.nextToken();
					String color = token.nextToken();
					int price = Integer.parseInt(token.nextToken());
					for(int i=0; i<order.size(); i++) {
						if(order.get(i).getEmail().equals(email)) {	// �ֹ� �迭�� ������ �̸����� �ִ� ���
							idx=i;	// ���� �̸��� �ֹ��� �ε����� ����
							found = true;
						}
					}
					if(!found) {	// �ֹ� �迭�� ���� �̸����� ���� ���
						list = new OrderList(email);
						order.add(list);	// �ֹ� ���� ����
					}
					if(type == 1) {	// �ɹٱ��� �ֹ��� ���
						int id = order.get(idx).getPidx1();
						for(int i=0; i<3; i++) { // �ֹ��� �ɵ� ����
							String fname = token.nextToken();
							String fcolor = token.nextToken();
							int fqty = Integer.parseInt(token.nextToken());
							f = new CartFlower(fname, fqty,fcolor);
							order.get(idx).getList1().add(f);	// �ֹ��� �� �ϳ� ����
							id++;
							order.get(idx).setIdx1(id);
						}
						order.get(idx).setColor1(color);	// �ٱ��� ���� ����
						order.get(idx).setPrice1(price);	// �ɹٱ��� ���� ����
					}
					else if(type == 2) {	// �ɴٹ��� ���
						int id = order.get(idx).getPidx2();
						for(int i=0; i<3; i++) { // �ֹ��� �ɵ� ����
							String fname = token.nextToken();
							String fcolor = token.nextToken();
							int fqty = Integer.parseInt(token.nextToken());
							f = new CartFlower(fname, fqty,fcolor);
							order.get(idx).getList2().add(f); // �ֹ��� �� �ϳ� ����
							id++;
							order.get(idx).setIdx2(id);
						}
						order.get(idx).setColor2(color); // ������ ���� ����
						order.get(idx).setPrice2(price); // �ɴٹ� ���� ����
					}
					idx++;	// �ֹ� �迭 �ε��� ����
				}
				buf.close();
				e = true;	// �ε� ����
			} catch (FileNotFoundException e) {
				System.out.println("File open error : "+orderlist+" ������ ã�� �� �����ϴ�.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
