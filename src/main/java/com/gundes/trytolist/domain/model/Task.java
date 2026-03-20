package com.gundes.trytolist.domain.model;

import com.gundes.trytolist.domain.enums.Priorities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Column(name = "user_id")
    private User userId;

    private String title;
    private String content;
    private Priorities priority;

    @Column(name = "is_concluded")
    private Boolean isConcluded;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "finish_at")
    private Date finishAt;

}
