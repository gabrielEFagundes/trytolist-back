package com.gundes.trytolist.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gundes.trytolist.domain.enums.Priorities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String title;
    private String content;
    private Priorities priority;

    @Column(name = "is_concluded")
    private Boolean isConcluded;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "finish_at")
    private Date finishAt;

    public Task(String title, String content, Priorities priority, Boolean isConcluded) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.isConcluded = isConcluded;
        this.createdAt = Date.valueOf(LocalDate.now());
    }
}
