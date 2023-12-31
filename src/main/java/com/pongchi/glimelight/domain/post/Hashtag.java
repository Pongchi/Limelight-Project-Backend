package com.pongchi.glimelight.domain.post;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private long id;

    @Column(nullable = false, unique = true)
    private String tag;

    @OneToMany(mappedBy = "hashtag")
    private List<PostHashtag> postHashTag;

    public Hashtag(String tag) {
        this.tag = tag;
    }
    
}
