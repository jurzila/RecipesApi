package com.sapheworkshop.RecipesApi.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Recipe")
@Data
@NoArgsConstructor
public class Recipe {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private  String category;
    private LocalDateTime date;
    @NotBlank
    private String description;
    @ElementCollection
    @NotEmpty
    private List<String> ingredients;
    @ElementCollection
    @NotEmpty
    private List<String> directions;

    @PreUpdate
    @PrePersist
    public void saveDate() {
        this.date = LocalDateTime.now();
    }
}
