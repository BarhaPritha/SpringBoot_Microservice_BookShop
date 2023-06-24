package com.bjit.SpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestModel {

    private String title;
    private String author;
    private String genre;
    private Long price;
    private Long inventory;

}

