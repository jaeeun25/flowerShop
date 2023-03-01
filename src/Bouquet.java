import java.util.ArrayList;

public class Bouquet extends CartList implements Delivery{
	private static ArrayList<CartFlower> bqtf = new ArrayList<>(); // ������ �� ��� �迭
	
	public Bouquet(String color, int type) {
		super(color, type);
	}
	
	@Override
	public ArrayList<CartFlower> getFlower() { // ������ �� ���� �迭 ��ȯ
		return bqtf;
	}
	
	@Override
	public void show() { // �ɴٹ� ��ǰ ���� ���
		System.out.println("������ ���� : " + color);
		for(CartFlower f : bqtf) System.out.println(f.getFname() + " : " + f.getFcolor() + " : " + f.getFqty() + "����");
		System.out.println("���� : " + price);
	}
	
	@Override
	public void dprice() { // ��ۺ� �߰� �Լ�
		if(price < 100000) price += dp; // ��ǰ�� �� ���� 10���� �����̸� ��ۺ� 2500�� �߰�
	}
}
