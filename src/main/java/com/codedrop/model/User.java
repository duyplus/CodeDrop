package com.codedrop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_code", nullable = false, length = 100)
    private String userCode;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Nationalized
    @Column(name = "fullname")
    private String fullname;

    @Size(max = 20)
    @Nationalized
    @Column(name = "phone", length = 20)
    private String phone;

    @Nationalized
    @ColumnDefault("'user.png'")
    @Lob
    @Column(name = "photo")
    private String photo;

    @Nationalized
    @Lob
    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ColumnDefault("0")
    @Column(name = "is_delete")
    private Boolean isDelete;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bank_id", nullable = false)
    private BankAccount bank;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Authority> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<BankAmount> bankAmounts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<BankHistory> bankHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    List<SourceCode> sourceCodes;

    @PostPersist
    public void generateUserCode() {
        if (this.userCode == null) {
            this.userCode = String.format("cod%06d", this.id);
        }
    }
}