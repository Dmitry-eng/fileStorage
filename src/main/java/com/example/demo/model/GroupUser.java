package com.example.demo.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group_user")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "\"group\"", nullable = false)
    private Group group;

    @ManyToOne()
    @JoinColumn(name = "\"user\"", nullable = false)
    private User user;


    public GroupUser() {
    }

    public GroupUser(User user, Group group) {
        this.user = user;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupUser groupUser = (GroupUser) o;
        return id == groupUser.id &&
                Objects.equals(group, groupUser.group) &&
                Objects.equals(user, groupUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, user);
    }

    @Override
    public String toString() {
        return "GroupUser{" +
                "id=" + id +
                ", group=" + group +
                ", user=" + user +
                '}';
    }
}
