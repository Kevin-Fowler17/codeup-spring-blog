package com.codeup.codeupspringblog.models;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(name="insert diff name here if want diff", nullable = false, length = 100)
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String body;

}
