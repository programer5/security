package com.sp.fc.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sp_authority")
@Entity
@IdClass(Authority.class)
public class Authority implements GrantedAuthority {

    public static final String ROLE_TEACHER = "ROLE_TEACHER";
    public static final String ROLE_STUDENT = "ROLE_STUDENT";

    @Id
    private Long userId;

    @Id
    private String authority;
}