package qa.pkg.mantis.model;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity  //for work with hibernate
@Table(name = "mantis_user_table")
public class UserData {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name="username")
  private String username;
  @Column(name="email")
  private  String email;

  public UserData(List<UserData> result) {
  }

  public UserData() {
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(username, userData.username) &&
            Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email);
  }

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }


}
