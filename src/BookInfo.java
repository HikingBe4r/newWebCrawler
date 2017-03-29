/**
 * 책정보 class
 * DB와 연동해 사용하기 위해 생성.
 * DB의 Column 값을 가져와야함.
 * ID(바코드 = ISBN), 제목, 저자, 출판사, 출판일, 카테고리, 장르1, 장르2
 * 더 필요할수도 있는데 이건 나중에.. 
 * @author kodica0307
 *
 */
public class BookInfo {
	private String isbn;		// 13자리 숫자
	private String subject;
	private String author;
	private String publisher;
	private String publishDate;	// YYYY/MM/DD
	private String category;	// 국내도서, 외국도서
	private String genre1;		// 소설, 시/에세이, 육아
	private String genre2;		// 한국소설, 일본소설, 영미소설, 프랑스소설
	
	public BookInfo(String isbn, String subject, String author, String publisher, String publishDate,
			String category, String genre1, String genre2) {
		this.isbn = isbn;
		this.subject = subject;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.category = category;
		this.genre1 = genre1;
		this.genre2 = genre2;
	}
	
	/**
	 * get: isbn, subject, author, publisher, publishDate, category, genre1, genre2
	 */
	
	public String getIsbn() {
		return this.isbn;
	}
	public String getSubject() {
		return this.subject;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getPublisher() {
		return this.publisher;
	}
	public String getPublishDate() {
		return this.publishDate;
	}
	public String getCategory() {
		return this.category;
	}
	public String getGenre1() {
		return this.genre1;
	}
	public String getGenre2() {
		return this.genre2;
	}
	
	/**
	 * set: ??
	 */
	
}
