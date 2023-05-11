package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Request {
    @Id
    private long id;
    private String name;
    private int priority;

    @Column(name = "active_status")
    private boolean activeStatus;


}
