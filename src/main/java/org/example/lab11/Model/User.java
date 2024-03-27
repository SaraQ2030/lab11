package org.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
    @NotEmpty(message = "the password cannot be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;
    @Email
    @NotEmpty(message = "email cannot be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @Column(columnDefinition = "date not null")
    private LocalDate registration_date;
}
