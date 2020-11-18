package qa.pkg.addressbook;

public class TestString {
  public static void main(String[] args) {
    String s="Select (IvaEdit Thrump)";
    String substr[]=s.split(" ");
    String firstNme=substr[1].substring(1);
    String lastName=substr[2].substring(0,substr[2].length()-1);
    System.out.println(firstNme);
    System.out.println(lastName);

      }
}
