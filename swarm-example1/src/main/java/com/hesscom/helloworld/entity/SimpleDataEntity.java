package com.hesscom.helloworld.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jhess on 08.05.17.
 */
@Entity
public class SimpleDataEntity implements Serializable {
    @Id
    private Long id;

    @Column
    private String data;

    public SimpleDataEntity(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
