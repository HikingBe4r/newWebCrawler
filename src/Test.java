/**
 * ID, 제목, 저자, 출판사, 출판일, 쪽수 획득을 위한 웹크롤러
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
					+ div1	// 대 구분
					+ div2	// 소 구분
					+ "&mallGb=KOR&orderClick=JAR";
			
			Document doc = Jsoup.connect(url).get();
			
			
			/**
			 * 1. 제목 뽑아내기
			 *       select(#mb itn b a)	itn: in the news b: bold, a:hyperlink
			 * select(div ul li a strong) <div> 거르고, <ul> 거르고, li, a 거르고 strong 거른 차례에서 나온거.
			 */
			Elements eSubject = doc.select("div ul li a strong");	
			
			List<String> subject = new ArrayList<String>();	// String arrayList에 담기.
			
			for(int i = 0; i < eSubject.size(); i+=2) {
				//System.out.println("text: "+eSubject.get(i).text());
				subject.add(eSubject.get(i).text());
			}
			
			// 제대로 뽑았는지 확인
			for(String a: subject) {
				//System.out.println("제목: "+ a);
			}
			
			/**
			 * 	출판사, 작가, 출판일
			 */
			
			Elements elements = doc.select("li div div div span");
			
			for(int i = 0; i < elements.size(); i+=2) {
				System.out.println("text: "+elements.get(i).text());
				//subject.add(eSubject.get(i).text());
			}
			
			File file = new File("BookInfo"
					+ "1"	// 책번호
					+ ".txt");	// 파일 확장자
			FileWriter fw = new FileWriter(file);
			fw.write(doc.toString());
			
			
			System.out.println("쓰기완료: "+file.getName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
