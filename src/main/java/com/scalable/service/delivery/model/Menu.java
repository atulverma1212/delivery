package com.scalable.service.delivery.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menus")
public class Menu {
    @Id
    private String id;

    @NotNull(message = "Items are mandatory")
    @DBRef
    private List<@Valid MenuItem> items;

    @NotBlank(message = "Restaurant ID is mandatory")
    private String restaurantId;
}