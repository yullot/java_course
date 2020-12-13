package qa.pkg.addressbook.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class ContactGroup {
  @EmbeddedId
  private ContactGroupId contactGroupId;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("contactId")
  @JoinColumn(name = "id")
  private ContactData contact;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("id")
  @JoinColumn(name = "group_id")
  private GroupData group;

  @Column(name = "deprecated")
  private Date deprecated = new Date();

  @Column(name = "domain_id")
  private Integer domainId = 0;

  private ContactGroup() {
  }

  public ContactGroup(ContactData contact, GroupData group) {
    this.contact = contact;
    this.group = group;
    this.contactGroupId = new ContactGroupId(contact.getContactId(), group.getId());
  }

  public ContactData getContact() {
    return contact;
  }

  public void setContact(ContactData contact) {
    this.contact = contact;
  }

  public GroupData getGroup() {
    return group;
  }

  public void setGroup(GroupData group) {
    this.group = group;
  }

  public Date getDeprecated() {
    return deprecated != null ? deprecated : new Date();
  }

  public void setDeprecated(Date deprecated) {
    this.deprecated = deprecated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGroup that = (ContactGroup) o;
    return Objects.equals(contact, that.contact) &&
            Objects.equals(group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contact, group);
  }
}
