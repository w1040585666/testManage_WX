package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class User  implements Serializable{

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name ;
    @Getter
    @Setter
    private Date date ;
}
