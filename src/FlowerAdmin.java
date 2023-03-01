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
	private static ArrayList<Flower> flowers;	// 상품(꽃) 담는 배열
	private String flowerlist;	// 이전실행들에서 등록해 놓은 꽃 목록 담은 파일
	private static boolean e = false;	// 꽃 목록 담은 파일 로드여부 확인 변수
	
	public FlowerAdmin(String flowerlist) {
		flowers = new ArrayList<>();
		this.flowerlist = flowerlist;
	}
	
	public static ArrayList<Flower> getFlowers() {
		return flowers;
	}

	public void fadd(String name, int price, String color) {	// 꽃 등록 함수
		Flower f;
		f = new Flower(name,price,color);
		flowers.add(f);	// 꽃 추가
	}
	
	public void fsave() {	// 꽃 목록 파일에 저장하는 함수
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
	
	public void fshow() {	// 꽃 목록 출력하는 함수
		fload();	// 이전 실행들에서 등록한 꽃 목록 담은 파일 로드
		int cnt = flowers.size();	// 등록된 꽃 개수
		System.out.println("** 꽃 이름 : 가격 : 색상 **");
		for(int i=0; i<cnt; i++) {
			System.out.println("[" + (i+1) + "] " + flowers.get(i).getName() + " : " + flowers.get(i).getPrice() + " : " + flowers.get(i).getColor());
		}
		System.out.println("  * 가격은 1송이 기준");
	}
	
	public void fload() {	// 이전실행들에서 등록해 놓은 꽃 목록 담은 파일 로드하는 함수
		if(!e) {	// 한번도 로드하지 않았을 경우
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
				e = true; // 파일 로드 했음
			} catch (FileNotFoundException e) {
				System.out.println("File open error : "+flowerlist+" 파일을 찾을 수 없습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fdel() {	// 등록된 꽃 삭제하는 함수
		String name, color;	// 꽃 이름, 꽃 색상
		boolean found = false;	// 입력한 꽃의 존재 여부 확인 변수
		int cnt = flowers.size();	// 등록된 꽃 개수
		System.out.print("삭제할 꽃 이름, 색상 : "); name=in.next(); color=in.next();
		for(int i=0; i<cnt; i++) {
			if(flowers.get(i).getName().equals(name) && flowers.get(i).getColor().equals(color)) {	// 등록된 꽃들 중 입력한 꽃 이름, 꽃 색상과 일치하는 꽃이 있을 경우
				flowers.remove(i);	// 해당 꽃 삭제
				System.out.println("삭제되었습니다.");	
				found = true;	// 꽃 존재					
				break;								
			}
		}
		if(!found) System.out.println("해당하는 꽃이 없습니다.");	// 입력한 꽃이 없을 경우
	}
}
