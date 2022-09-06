package com.rvtech.productservice.entity;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "modifiedBy")
    private String modifiedBy;
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;
}
