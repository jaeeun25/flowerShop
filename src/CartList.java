import java.util.ArrayList;

public abstract class CartList{
	protected String color;	// �ٱ��� or ������ ����
	protected int type, price;	// �ɹٱ��Ͽ� �ɴٹ� �������� ����, ��ǰ�� �� ����(��ۺ�����)
	
	public CartList(String color, int type) {
		this.color = color;
		this.type = type;
	}

	public abstract void show();
	public abstract ArrayList<CartFlower> getFlower();
}
