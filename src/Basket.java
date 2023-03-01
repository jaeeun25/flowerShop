import java.util.ArrayList;

public class Basket extends CartList implements Delivery{
	private static ArrayList<CartFlower> bktf = new ArrayList<>();	// ������ �� ��� �迭
	
	public Basket(String color, int type) {
		super(color, type);
	}
	
	@Override
	public ArrayList<CartFlower> getFlower() {	// ������ �� ���� �迭 ��ȯ
			return bktf;
	}

	@Override
	public void show() {	// �ɹٱ��� ��ǰ ���� ���
		System.out.println("�ٱ��� ���� : " + color);
		for(CartFlower f : bktf) System.out.println(f.getFname() + " : " + f.getFcolor() + " : " + f.getFqty() + "����");
		System.out.println("���� : " + price);
	}

	@Override
	public void dprice() {	// ��ۺ� �߰� �Լ�
		if(price < 100000) price += dp;	// ��ǰ�� �� ���� 10���� �����̸� ��ۺ� 2500�� �߰�
	}
}
