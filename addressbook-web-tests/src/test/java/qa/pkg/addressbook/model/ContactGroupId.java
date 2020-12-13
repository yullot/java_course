package qa.pkg.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContactGroupId implements Serializable {

  @Column(name = "id")
  private int contactId;

  @Column(name = "group_id")
  private int groupId;


  private ContactGroupId() {
  }

  public ContactGroupId(int contactId, int groupId) {
    this.contactId = contactId;
    this.groupId = groupId;
  }


  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGroupId that = (ContactGroupId) o;
    return contactId == that.contactId &&
            groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, groupId);
  }

}