/**
 * ID, ����, ����, ���ǻ�, ������, �ʼ� ȹ���� ���� ��ũ�ѷ�
 */

import java.io.*;
import java.util.*;

import org.apache.http.client.methods.HttpPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) {
		Integer div1_num = new Integer(1), div2_num = new Integer(3);
		
		String div1=null, div2 = null;
		if(div1_num < 10) {
			div1 = 0+div1_num.toString();
		}
		if(div2_num < 10) {
			div2 = 0+div2_num.toString();
		}
				
		try {
			
			String url = "http://www.kyobobook.co.kr/categoryRenewal/categoryMain.laf?linkClass="
					+ div1	// �� ����
					+ div2	// �� ����
					+ "&mallGb=KOR&orderClick=JAR";
			
			Document doc = Jsoup.connect(url).get();
			
			
			/**
			 * 1. ���� �̾Ƴ���
			 *       select(#mb itn b a)	itn: in the news b: bold, a:hyperlink
			 * select(div ul li a strong) <div> �Ÿ���, <ul> �Ÿ���, li, a �Ÿ��� strong �Ÿ� ���ʿ��� ���°�.
			 */
			Elements eSubject = doc.select("div ul li a strong");	
			
			List<String> subject = new ArrayList<String>();	// String arrayList�� ���.
			
			for(int i = 0; i < eSubject.size(); i+=2) {
				//System.out.println("text: "+eSubject.get(i).text());
				subject.add(eSubject.get(i).text());
			}
			
			// ����� �̾Ҵ��� Ȯ��
			for(String a: subject) {
				//System.out.println("����: "+ a);
			}
			
			/**
			 * 	���ǻ�, �۰�, ������
			 */
			
			Elements elements = doc.select("li div div div span");
			
			for(int i = 0; i < elements.size(); i+=2) {
				System.out.println("text: "+elements.get(i).text());
				//subject.add(eSubject.get(i).text());
			}
			
			File file = new File("BookInfo"
					+ "1"	// å��ȣ
					+ ".txt");	// ���� Ȯ����
			FileWriter fw = new FileWriter(file);
			fw.write(doc.toString());
			
			
			System.out.println("����Ϸ�: "+file.getName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
