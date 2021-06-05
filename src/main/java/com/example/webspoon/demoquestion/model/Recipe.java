package com.example.webspoon.demoquestion.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe {

    String name;
    Date expiresAt;
    Date createdAt;
    Integer likes = 0;

    String snippet;

    public void increaseExpiresTimeBy(Integer addedSeconds){
        this.setExpiresAt(new Date( this.getExpiresAt().getTime() + 1000L*addedSeconds));
    }
    public void like(){
        this.setLikes(this.likes+=1);
    }
}
