package qa.pkg.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity//for work with hibernate
@Table(name = "group_list")

public class GroupData {

  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "group_name")
  private String groupName;
  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  private String header;
  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private String footer;

  @Column(name = "deprecated")
  private Date deprecated = new Date();

  public void setContacts(Contacts contacts) {
     contacts.forEach(contactData -> {
       ContactGroup cg = new ContactGroup(contactData, this);
       cg.setDeprecated(new Date());
       this.contacts.add(cg);
     });
  }



  //  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
  @OneToMany(mappedBy = "group",
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER,
          orphanRemoval = true)
  private Set<ContactGroup> contacts = new HashSet<>();

//  public Contacts getContacts() {
//    return new Contacts(contacts);
//  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(groupName, groupData.groupName) &&
            Objects.equals(header, groupData.header) &&
            Objects.equals(footer, groupData.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupName, header, footer);
  }

  public Set<ContactGroup> getContacts() {
    return contacts;
  }

  public Date getDeprecated() {
    return deprecated;
  }

  public void setDeprecated(Date deprecated) {
    this.deprecated = deprecated;
  }
}
