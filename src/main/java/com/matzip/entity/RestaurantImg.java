package com.matzip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="res_img")
@Getter @Setter
public class RestaurantImg extends BaseEntity{

    @Id
    @Column(name="res_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long res_img_id;

    private String imgName; //이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; //이미지 조회 경로

    private String repimgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    public void updateRestaurantImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

}