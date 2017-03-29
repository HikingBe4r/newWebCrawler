/**
 * å���� class
 * DB�� ������ ����ϱ� ���� ����.
 * DB�� Column ���� �����;���.
 * ID(���ڵ� = ISBN), ����, ����, ���ǻ�, ������, ī�װ�, �帣1, �帣2
 * �� �ʿ��Ҽ��� �ִµ� �̰� ���߿�.. 
 * @author kodica0307
 *
 */
public class BookInfo {
	private String isbn;		// 13�ڸ� ����
	private String subject;
	private String author;
	private String publisher;
	private String publishDate;	// YYYY/MM/DD
	private String category;	// ��������, �ܱ�����
	private String genre1;		// �Ҽ�, ��/������, ����
	private String genre2;		// �ѱ��Ҽ�, �Ϻ��Ҽ�, ���̼Ҽ�, �������Ҽ�
	
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
