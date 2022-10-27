package org.example.introspection.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Note {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Введите дату")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotBlank(message = "Это поле должно быть заполнено")
    @Length(max = 2048, message = "message too long")
    private String good;

    @NotEmpty(message = "Это поле должно быть заполнено")
    private String bad;

    @NotEmpty(message = "Это поле должно быть заполнено")
    private String another;

    @NotEmpty(message = "Это поле должно быть заполнено")
    private String targetApproach;

    @NotEmpty(message = "Это поле должно быть заполнено")
    private String together;


    public Note() {
    }

    public Note(
            Date date,
            String good, String bad, String another, String targetApproach, String together, User author) {
        this.date = date;
        this.good = good;
        this.bad = bad;
        this.another = another;
        this.targetApproach = targetApproach;
        this.together = together;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }

    public String getTargetApproach() {
        return targetApproach;
    }

    public void setTargetApproach(String targetApproach) {
        this.targetApproach = targetApproach;
    }

    public String getTogether() {
        return together;
    }

    public void setTogether(String together) {
        this.together = together;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}
