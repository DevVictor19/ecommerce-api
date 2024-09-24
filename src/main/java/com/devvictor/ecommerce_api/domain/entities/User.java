package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String email;
    private String password;
    private List<Role> roles;

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;


    public boolean isAdmin() {
        return this.roles.contains(Role.ADMIN);
    }

    public boolean isClient() {
        return this.roles.contains(Role.CLIENT);
    }
}