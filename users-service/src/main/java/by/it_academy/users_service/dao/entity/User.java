package by.it_academy.users_service.dao.entity;


import by.it_academy.users_service.dao.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "user", name = "users")
public class User implements UserDetails {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String nick;
    private String password;
    private String mail;
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "user", name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_uuid", referencedColumnName = "uuid")},
            inverseJoinColumns = {@JoinColumn(name = "user_role", referencedColumnName = "name")})
    private Set<Role> role;

    public User() {
    }

    @Id
    @Column(name = "uuid")
    public UUID getUuid() {
        return uuid;
    }

    @Column(name = "dt_create")
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Version
    @Column(name = "dt_update")
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    @Column(name = "nick")
    public String getNick() {
        return nick;
    }

    @Column(unique = true, name = "mail")
    public String getMail() {
        return mail;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}