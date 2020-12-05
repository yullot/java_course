package qa.pkg.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity //for work with hibernate
@Table(name="addressbook")
public class ContactData {
  @Id
  @Column(name="id")
  private int contactId = Integer.MAX_VALUE;
 // @Expose
  @Column(name="firstname")
  private String firstname;
  @Expose
  @Column(name="lastname")
  private String lastname;
 //@Expose
  @Column(name="home")
  @Type(type="text")
  private String homePhone;
  @Column(name="mobile")
  @Type(type="text")
  private String mobilePhone;
  //@Expose
  @Column(name="work")
  @Type(type="text")
  private String workPhone;
  //@Expose
  @Column(name="address")
  @Type(type="text")
  private String address;
  @Transient
  private String group;
  @Transient
  private String allPhones;
  //@Expose
  @Column(name="email")
  @Type(type="text")
  private String email;
  //@Expose
  @Column(name="email2")
  @Type(type="text")
  private String email2;
  @Column(name="email3")
  @Type(type="text")
  private String email3;
  @Transient
  private String allEmails;
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  public ContactData withPhoto( File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withContactId(int contactId) {
    this.contactId = contactId;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
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

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }

  public int getContactId() {
    return contactId;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return new File(photo);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId=" + contactId +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return contactId == that.contactId &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, firstname, lastname);
  }
}
