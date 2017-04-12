import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * WebCrawler���� �̾Ƴ� ������ OracleDB�� �����Ű��.
 * 
 * @author kodica0307
 *
 */

public class JdbcTest1 {
	
	public static void main(String[] args) {
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String user = "java";
			String password = "java1234";
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
			
			System.out.println("Ŀ�ؼ� ����");
			
			Statement stmt = con.createStatement();
			
			//stmt.executeUpdate("insert into bookinfo values(35,'���ÿ�')");  
			//int result=stmt.executeUpdate("update bookinfo set author='����' where isbn=35");  
			
			int result=stmt.executeUpdate("delete from bookinfo where isbn=33");  
			System.out.println(result+" records affected");  
			
			
			// Statement.executeQuery(String) : select���� ���ؼ��� ���.
			// 			.executeUpdate(String) : update, delete, insert
			ResultSet rs = stmt.executeQuery("select * from bookinfo");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1)+ " " +rs.getString(2));
			}
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
