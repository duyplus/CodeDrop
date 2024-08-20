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
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "source_code")
public class SourceCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "title", nullable = false)
    private String title;

    @Nationalized
    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @Size(max = 255)
    @Nationalized
    @Column(name = "slug")
    private String slug;

    @Nationalized
    @Lob
    @Column(name = "link_download")
    private String linkDownload;

    @Nationalized
    @Lob
    @Column(name = "link_demo")
    private String linkDemo;

    @Nationalized
    @Lob
    @Column(name = "commited")
    private String commited;

    @Size(max = 255)
    @Nationalized
    @Column(name = "seo_keyword")
    private String seoKeyword;

    @Size(max = 500)
    @Nationalized
    @Column(name = "short_desc", length = 500)
    private String shortDesc;

    @Nationalized
    @Lob
    @Column(name = "description")
    private String description;

    @Nationalized
    @Lob
    @Column(name = "tutorial")
    private String tutorial;

    @Column(name = "contract")
    private Boolean contract;

    @Column(name = "view_count")
    private Integer viewCount;

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
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @JsonIgnore
    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    List<CommentSource> commentSources;

    @JsonIgnore
    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    List<SourceType> sourceTypes;
}