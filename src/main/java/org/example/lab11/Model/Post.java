package org.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "category id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer category_id;
    @NotEmpty(message = "title cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotEmpty(message = "title cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;
    @NotNull(message = "user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer user_id;
    private LocalDate publish_date;
}
