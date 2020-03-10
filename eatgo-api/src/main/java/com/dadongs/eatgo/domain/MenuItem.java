package com.dadongs.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue
    private long id;

    @Setter
    private long restaurantId;

    private String name;

    @Transient  //DB에 넣지 않을 값
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
}
