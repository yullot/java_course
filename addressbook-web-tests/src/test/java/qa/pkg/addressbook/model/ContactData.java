package qa.pkg.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int contactId;
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String homePhone;
  private final String address;
  private String group;

  public ContactData(int contactId, String firstname,  String lastname, String email, String homePhone, String address, String group) {
    this.contactId=contactId;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.homePhone = homePhone;
    this.address = address;
    this.group = group;
  }

  public ContactData( String firstname,  String lastname, String email, String homePhone, String address, String group) {
    this.contactId=Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.homePhone = homePhone;
    this.address = address;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }

  public int getContactId(){
          return contactId;
    }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + contactId +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  }
