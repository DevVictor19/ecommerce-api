package com.devvictor.ecommerce_api.user.domain.entities;

import com.devvictor.ecommerce_api.user.domain.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;

    @Indexed(unique = true)
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