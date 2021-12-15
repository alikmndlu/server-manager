package com.alikmndlu.servermanager.model;

import com.alikmndlu.servermanager.model.enumeration.ServerStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "IP Address cannot be empty or null!")
    private String ipAddress;

    private String name;

    private String memoryStorage;

    private String type;

    private String imageUrl;

    private ServerStatus serverStatus;
}
