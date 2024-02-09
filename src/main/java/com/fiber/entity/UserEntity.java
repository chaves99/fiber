package com.fiber.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "fiber_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private Double weight;

    private Double height;

    @Enumerated(EnumType.STRING)
    private UnitMeasurementWeight weightUnit;

    @Enumerated(EnumType.STRING)
    private UnitMeasurementHeight heightUnit;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<DietSeasonEntity> dietSeason;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getUsername() {
        return this.getName();
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

    public void weightUnit(String value) {
        setWeightUnit(UnitMeasurementWeight.getByName(value));
    }

    public void heightUnit(String value) {
        setHeightUnit(UnitMeasurementHeight.getByName(value));
    }
}
