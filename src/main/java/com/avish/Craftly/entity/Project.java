package com.avish.Craftly.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects",
    indexes = {
        @Index(name = "idx_project_updated_at_dexc", columnList = "updated_at DESC, deleted_at"),
            @Index(name = "idx_project_deleted_at_updated_at_desc", columnList = "deleted_at DESC, updated_at"),
        @Index(name = "idx_project_deleted_at_dexc", columnList = "deleted_at DESC")
    }
)
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

//    @ManyToOne
//    @JoinColumn(name = "owner_id", nullable = false)
//    User owner;

    Boolean isPublic = false;
    @CreationTimestamp
    Instant createdAt;

    @CreationTimestamp
    Instant updatedAt;
    Instant deletedAt;

}
