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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer user_id;
    @NotNull(message = "post id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer post_id;
    @NotEmpty(message = "the content cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;
    @Column(columnDefinition = "date not null")
    private LocalDate comment_date;
}
