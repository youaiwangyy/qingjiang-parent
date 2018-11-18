package com.qingjiang.common.domain;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private Long id;
    private String name;
    private String desc;


}
