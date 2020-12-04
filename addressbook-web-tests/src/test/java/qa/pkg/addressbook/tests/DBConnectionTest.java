package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.sql.*;


public class DBConnectionTest {
  @Test
  public void testDBConnection()  {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password="); //jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC
      Statement st= conn.createStatement();
      ResultSet rs=st.executeQuery("select group_id,group_name,group_footer,group_header from group_list");
      Groups groups=new Groups();
      while(rs.next()){
        groups.add(new GroupData().withId(rs.getInt("group_id")).withGroupName(rs.getString("group_name"))
        .withFooter(rs.getString("group_footer")).withHeader(rs.getString("group_header")));
      }
      conn.close();
      st.close();
      rs.close();
      System.out.println(groups);
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
