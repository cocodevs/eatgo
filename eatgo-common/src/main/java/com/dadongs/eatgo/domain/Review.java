package com.dadongs.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Setter
    private Long restaurantId;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Min(0)
    @Max(5)
    private Integer score;

    @NotEmpty
    private String description;
}
