package com.e_commerce.e_commerce.converter;

import com.e_commerce.e_commerce.dto.response.*;
import com.e_commerce.e_commerce.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    // ROLES -> RoleResponse Dönüştürücü
    public static List<RoleResponse> findRoles(List<Role> roles) {
        List<RoleResponse> responses = new ArrayList<>();
        for (Role role : roles) {
            responses.add(new RoleResponse(role.getId(), role.getAuthority()));
        }
        return responses;
    }

    // USERS -> UserResponse Dönüştürücü
    public static UserResponse findUser(User user) {
        String role = (user.getRole() != null) ? user.getRole().getAuthority() : "Unknown Role";
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), role);
    }

    public static List<UserResponse> findUsers(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole().getAuthority()));
        }
        return userResponses;
    }

    // CATEGORIES -> CategoryResponse Dönüştürücü
    public static List<CategoryResponse> findCategories(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(new CategoryResponse(category.getId(), category.getCode(),
                    category.getTitle(), category.getImg(), category.getRating(), category.getGender()));
        }
        return categoryResponses;
    }

    public static CategoryResponse findCategory(Category category) {
        return new CategoryResponse(category.getId(), category.getCode(),
                category.getTitle(), category.getImg(), category.getRating(), category.getGender());
    }

    // PRODUCTS -> ProductResponse Dönüştürücü
    public static List<ProductResponse> findProducts(List<Products> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Products product : products) {
            productResponses.add(new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getCategoryId(),
                    product.getRating(),
                    product.getSellCount(),
                    product.getImage()
            ));
        }
        return productResponses;
    }

    public static ProductResponse findProduct(Products product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategoryId(),
                product.getRating(),
                product.getSellCount(),
                product.getImage()
        );
    }

    // ADDRESS -> AddressResponse Dönüştürücü
    public static AddressResponse findAddress(Address address) {
        return new AddressResponse(address.getId(), address.getName(), address.getSurname(), address.getPhone(),
                address.getCity(), address.getDistrict(), address.getNeighborhood(), address.getAddress());
    }
}
