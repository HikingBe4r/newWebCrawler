/**
 * ID(���ڵ� = ISBN), ����, ����, ���ǻ�, ������, ī�װ�, �帣1, �帣2 ȹ���� ���� ��ũ�ѷ�
 */


import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) {
		List<BookInfo> bookList = new ArrayList<BookInfo>();
		/*
		bookList.add(new BookInfo(eBarcode.get(0).attr(value),
				eSubject.get(i).text(),
				eAuthor.get(i).text(),
				ePublish.get(i).text(),	// 
				));
		*/
				
		try {
			/**
			 * url ����.
			 * for���� div1_num�� div2_num�� ���ؼ� ������ (2������)
			 * 10���� ������ div1+div2�� 2�ڸ��� ���߱����ؼ� �տ� 0�� �ٿ��ش�.
			 * 
			 * ����ó��: div1+div2�� subject.size�� 0�̸� break;	// �ش�å�� �������.
			 * 
			 * ���ڵ�
			 * div1: ��/������, �Ҽ�, �ι�, �������� ��� �帣1
			 * div2: �帣2
			 */
			
			Integer div1_num = new Integer(1), div2_num = new Integer(3);
			List<String> subject = new ArrayList<String>();
			for(Integer num1 = div1_num; num1 < 5; num1+=2) {
				for(Integer num2 = div2_num; num2 < 5; num2+=2) {
				
					String div1=num1.toString(), div2 = num2.toString();
					if(num1 < 10) {
						div1 = 0+num1.toString();
					}
					if(num2 < 10) {
						div2 = 0+num2.toString();
					}
					
					String url = "http://www.kyobobook.co.kr/categoryRenewal/categoryMain.laf?linkClass="
							+ div1	// �� ����
							+ div2	// �� ����
							+ "&mallGb=KOR&orderClick=JAR";
					
					Document doc = Jsoup.connect(url).get();
					
					
					/**
					 * å���� �̾Ƴ���
					 * doc.select("li div div div.title a strong") 
					 * 			<li> <div> <div class = 'title'> <a> <strong>
					 */
					
					// 0. ���ڵ� (isbn��ȣ)
					//<input type="hidden" name="barcode" value="9788937473135" /> �̷��� ����ſ��� �̾Ƴ����Ѵ�.
					
					Elements eBarcode = doc.select("input[name=barcode][value]");
					List<String> strList = new ArrayList<String>();
					for(int i = 0; i < eBarcode.size(); i++) {
						strList.add(eBarcode.get(i).attr("value"));
					}

					// Ȯ�ο�.
					for(String str: strList) {
						System.out.println("���ڵ�: "+str);	
					}
					
					// 1. ����
					Elements eSubject = doc.select("li div div div.title a strong");			
					//subject = new ArrayList<String>();	// String arrayList�� ���.
					for(int i = 0; i < eSubject.size(); i++) {
						if(i %2 == 0)
							subject.add(eSubject.get(i).text());
					}
					
					// 2. �۰�
					Elements eAuthor = doc.select("li div div div span.author"); // �۰��̸�
					List<String> author = new ArrayList<String>();	// String arrayList�� ���.
					for(int i = 0; i < eAuthor.size(); i++) {
						if(i %2 == 0)
							author.add(eAuthor.get(i).text());
					}
					
					// 3. ���ǻ�, ������  �±� class�� publication���� ����.
					Elements ePublish = doc.select("li div div div span.publication"); // ���ǻ�, ������
					List<String> publisher = new ArrayList<String>();	// ���ǻ�
					List<String> publishDate = new ArrayList<String>();	// ������
					for(int i = 0; i < ePublish.size(); i++) {
						if(i % 3 == 0) {
							// ���ǻ� add
							publisher.add(ePublish.get(i).text());
						}
						else if(i % 3 == 1) {
							// ������ add
							publishDate.add(ePublish.get(i).text());
						}
						// i%3 == 2�ΰ� ����.
					}
					
					// 4. �帣1
					// 0: ��������, 1: �Ҽ�, 2: ���̼Ҽ�
					Elements eGenre = doc.select("div p span a");
					for(int i = 0; i < eGenre.size(); i++) {
						if(i%3 == 0) {
							// 1��ī�װ� e.g)��������
							
						}
						else if(i%3 == 1) {
							// 1���帣 e.g) �Ҽ�
						}
						else if(i%3 == 2) {
							// 2���帣 e.g) ���̼Ҽ� ���
						}
					}
					
					
					
					
//					System.out.println("��: "+eBarcode);
	//				System.out.println("size: "+eBarcode.size());
//					for(int i = 0; i < eBarcode.size(); i++) {
//						System.out.println("����: "+ eBarcode.get(i).text());
//					}
					// ����� �̾Ҵ��� Ȯ��
					/*
					for(int i = 0; i < subject.size(); i++) {
						System.out.println("����: "+ subject.get(i));
						System.out.println("�۰�: "+ author.get(i));
						System.out.println("���ǻ�: "+ publisher.get(i));
						System.out.println("������: "+ publishDate.get(i));
						System.out.println();
					}
					*/
					
					
					
					
				
				
				// ���Ϸ� ����.? �̰� ���� ������
				/*
				File file = new File("BookInfo"
						+ "1"	// å��ȣ
						+ ".html");	// ���� Ȯ����
				FileWriter fw = new FileWriter(file);
				fw.write(doc.toString());
				
				
				System.out.println("����Ϸ�: "+file.getName());
				*/
					System.out.println((div1+div2)+" �Ϸ�");
					System.out.printf("�Ѱ���: %d\n", subject.size());
				} // for(num2)
			} // for(num1)
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
