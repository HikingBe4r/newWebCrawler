/**
 * 교보문고에서 뽑아옴. --> 국립중앙도서관에서 뽑아올수있을까?
 * ID(바코드 = ISBN), 제목, 저자, 출판사, 출판일, 카테고리, 장르1, 장르2 획득을 위한 웹크롤러
 */

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

public class WebCrawler {
	public static void main(String[] args) {
		List<BookInfo> bookList = new ArrayList<BookInfo>(); // DB저장전.
		try {
			
			/**
			 * url 설정. for문을 div1_num과 div2_num에 대해서 돌린다 (2씩증가) 10보다 작으면
			 * div1+div2를 2자리로 맞추기위해서 앞에 0을 붙여준다.
			 * 
			 * 예외처리: div1+div2의 subject.size가 0이면 break; // 해당책이 없을경우.
			 * 
			 * 바코드 div1: 시/에세이, 소설, 인문, 가정육아 등등 장르1 div2: 장르2
			 */

			Integer div1_num = new Integer(1), div2_num = new Integer(1);
			
			for (Integer num1 = div1_num; num1 < 5; num1 ++) {
				for (Integer num2 = div2_num; num2 < 10; num2 ++) {

					String div1 = num1.toString(), div2 = num2.toString();
					if (num1 < 10) {
						div1 = 0 + num1.toString();
					}
					if (num2 < 10) {
						div2 = 0 + num2.toString();
					}

					String url = "http://www.kyobobook.co.kr/categoryRenewal/categoryMain.laf?linkClass=" 
							+ div1 // 대 구분
							+ div2 // 소 구분
							+ "&mallGb=KOR&orderClick=JAR";

					Document doc = Jsoup.connect(url).get();

					/**
					 * 책정보 뽑아내기 doc.select("li div div div.title a strong")
					 * <li><div> <div class = 'title'> <a> <strong>
					 */

					// 0. 바코드 (isbn번호)
					// <input type="hidden" name="barcode" value="9788937473135"> 이렇게 생긴거에서 뽑아내야한다.
					// 

					Elements eBarcode = doc.select("input[name=barcode][value]");
					List<String> isbn = new ArrayList<String>();
					for (int i = 0; i < eBarcode.size(); i++) {
						isbn.add(eBarcode.get(i).attr("value"));
					}

					// 1. 제목
					Elements eSubject = doc.select("li div div div.title a strong");
					List<String> subject = new ArrayList<String>();
					// subject = new ArrayList<String>(); 
					// String arrayList에 담기.
					for (int i = 0; i < eSubject.size(); i++) {
						if (i % 2 == 0)
							subject.add(eSubject.get(i).text());
					}

					// 2. 작가
					Elements eAuthor = doc.select("li div div div span.author"); // 작가이름
					List<String> author = new ArrayList<String>(); // String arrayList에 담기.
					for (int i = 0; i < eAuthor.size(); i++) {
						if (i % 2 == 0) {
							author.add(eAuthor.get(i).text());
							
						}
					}

					// 3. 출판사, 출판일 태그 class가 publication으로 같음.
					Elements ePublish = doc.select("li div div div span.publication"); 
					
					List<String> publisher = new ArrayList<String>(); // 출판사
					List<String> publishDate = new ArrayList<String>(); // 출판일
					for (int i = 0; i < ePublish.size(); i++) {
						if (i % 3 == 0) {
							// 출판사 add
							publisher.add(ePublish.get(i).text());
						} else if (i % 3 == 1) {
							// 출판일 add
							publishDate.add(ePublish.get(i).text());
						}
						// i%3 == 2인건 무시.
					}
					
					

					// 4. 장르1
					// 0: 국내도서, 1: 소설, 2: 영미소설
					// 장르1만 필요하다고함.
					Elements eGenre = doc.select("div p span a");
					//List<String> genre1 = new ArrayList<String>();	// 어차피 1개라서 리스트에 넣을필요가없음.
					String genre1=null;
					for (int i = 0; i < eGenre.size(); i++) {
						if (i % 3 == 0) {
							// 1번카테고리 e.g)국내도서

						} else if (i % 3 == 1) {
							// 1번장르 e.g) 소설
							genre1 = eGenre.get(i).text();
						} else if (i % 3 == 2) {
							// 2번장르 e.g) 영미소설 등등
						}
					}
					
					
					// bookList에 넣기.
					// String isbn, String subject, String author, String publisher, String publishDate, String category,String genre1, String genre2
					for (int i = 0 ; i < subject.size(); i++) {
						bookList.add(new BookInfo(isbn.get(i), subject.get(i), author.get(i), publisher.get(i),
								publishDate.get(i), genre1));
					}
					
					
					// 파일로 저장.? 이게 쓸모가 있을까
					/*
					  File file = new File("BookInfo" + "1" // 책번호 + ".html");
					  // 파일 확장자 FileWriter fw = new FileWriter(file);
					  fw.write(doc.toString());
					  
					  
					  System.out.println("쓰기완료: "+file.getName());
					*/ 
					  
					System.out.println((div1 + div2) + " 완료");
					
				} // for(num2)
			} // for(num1)
			
			
			// 전체 조회.
			for(BookInfo b: bookList) {
				System.out.println(b);
			}
			
			System.out.println("총 책수: " + bookList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
