import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FlowerAdmin {
	private Scanner in = new Scanner(System.in);
	private static ArrayList<Flower> flowers;	// ��ǰ(��) ��� �迭
	private String flowerlist;	// ��������鿡�� ����� ���� �� ��� ���� ����
	private static boolean e = false;	// �� ��� ���� ���� �ε忩�� Ȯ�� ����
	
	public FlowerAdmin(String flowerlist) {
		flowers = new ArrayList<>();
		this.flowerlist = flowerlist;
	}
	
	public static ArrayList<Flower> getFlowers() {
		return flowers;
	}

	public void fadd(String name, int price, String color) {	// �� ��� �Լ�
		Flower f;
		f = new Flower(name,price,color);
		flowers.add(f);	// �� �߰�
	}
	
	public void fsave() {	// �� ��� ���Ͽ� �����ϴ� �Լ�
		try {
			FileWriter writer = new FileWriter(flowerlist);
			BufferedWriter buf = new BufferedWriter(writer);
			for(Flower flower : flowers) {	
				buf.write(flower.getName()+"/");
				buf.write(flower.getPrice()+"/");
				buf.write(flower.getColor()); 
				buf.newLine();
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fshow() {	// �� ��� ����ϴ� �Լ�
		fload();	// ���� ����鿡�� ����� �� ��� ���� ���� �ε�
		int cnt = flowers.size();	// ��ϵ� �� ����
		System.out.println("** �� �̸� : ���� : ���� **");
		for(int i=0; i<cnt; i++) {
			System.out.println("[" + (i+1) + "] " + flowers.get(i).getName() + " : " + flowers.get(i).getPrice() + " : " + flowers.get(i).getColor());
		}
		System.out.println("  * ������ 1���� ����");
	}
	
	public void fload() {	// ��������鿡�� ����� ���� �� ��� ���� ���� �ε��ϴ� �Լ�
		if(!e) {	// �ѹ��� �ε����� �ʾ��� ���
			try {
				FileReader reader = new FileReader(flowerlist);
				BufferedReader buf = new BufferedReader(reader);
				String line;
				while((line = buf.readLine()) != null) {
					StringTokenizer token = new StringTokenizer(line, "/");
					String name = token.nextToken();
					int price = Integer.parseInt(token.nextToken());
					String color = token.nextToken();
					fadd(name, price, color);
				}
				buf.close();
				e = true; // ���� �ε� ����
			} catch (FileNotFoundException e) {
				System.out.println("File open error : "+flowerlist+" ������ ã�� �� �����ϴ�.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fdel() {	// ��ϵ� �� �����ϴ� �Լ�
		String name, color;	// �� �̸�, �� ����
		boolean found = false;	// �Է��� ���� ���� ���� Ȯ�� ����
		int cnt = flowers.size();	// ��ϵ� �� ����
		System.out.print("������ �� �̸�, ���� : "); name=in.next(); color=in.next();
		for(int i=0; i<cnt; i++) {
			if(flowers.get(i).getName().equals(name) && flowers.get(i).getColor().equals(color)) {	// ��ϵ� �ɵ� �� �Է��� �� �̸�, �� ����� ��ġ�ϴ� ���� ���� ���
				flowers.remove(i);	// �ش� �� ����
				System.out.println("�����Ǿ����ϴ�.");	
				found = true;	// �� ����					
				break;								
			}
		}
		if(!found) System.out.println("�ش��ϴ� ���� �����ϴ�.");	// �Է��� ���� ���� ���
	}
}
