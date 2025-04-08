package com.hobro11.query.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = { "brn" }))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사업자등록번호
    private Long brn;

    @Setter
    private String name;

    @Setter
    private String phone;

    @Setter
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Setter
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Member(Long brn, String name, String phone, MemberStatus status, MemberRole role) {
        this.brn = brn;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
