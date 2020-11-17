package qa.pkg.addressbook.model;

import java.util.Objects;

public class GroupData {
  public void setId(int id) {
    this.id = id;
  }

  private int id;
  private final String groupName;
  private final String header;
  private final String footer;

  public GroupData(int id, String groupName, String header, String footer) {
    this.id = id;
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName);
  }

  public GroupData(String groupName, String header, String footer) {
    this.id=Integer.MAX_VALUE;
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
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

}
