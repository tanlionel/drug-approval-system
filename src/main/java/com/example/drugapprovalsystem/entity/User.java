package com.example.drugapprovalsystem.entity;

import com.example.drugapprovalsystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname", length = 150)
    private String fullname;

    @Column(name = "day_of_birth")
    private LocalDate dayOfBirth;

    @Column(name = "avatar", length = 45)
    private String avatar;

    @Column(name = "gender")
    private Integer gender;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_active", length = 45)
    private String isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(UserRole.valueOf(role.getName())::name);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}