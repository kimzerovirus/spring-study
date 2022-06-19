package me.kzv.ex.persistence;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

//항상 NoArgsConstructor가 위에
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
