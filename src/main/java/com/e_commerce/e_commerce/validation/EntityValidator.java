package com.e_commerce.e_commerce.validation;

import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import org.springframework.http.HttpStatus;

public class EntityValidator {

    public static final String MUST_NOT_BE_NULL_EMPTY = "must not be null or empty!";
    public static final String MUST_BE_BIGGER_THAN_0 = "must be bigger than 0!";
    public static final String ENTER_VALID_CATEGORYID = " must be a valid!";
    public static final String IS_EMAIL_PRESENT = "Email already taken!";
    public static final String IS_USER_PRESENT = "User not found!";
    public static final String IS_USER_VALID = "User not valid!";
    public static final String IS_PRODUCT_PRESENT = "There is a product with the given name!";
    public static final String IS_NOT_PRODUCT_PRESENT = "The product is not found!";
    public static final String IS_NOT_PRESENT_ADDRESS = "The address is not found!";

    // Boş veya null kontrolü
    public static void checkEmptyOrNull(String value, String field) {
        if (value == null || value.isEmpty()) {
            throw new CommerceException(field + MUST_NOT_BE_NULL_EMPTY, HttpStatus.BAD_REQUEST);
        }
    }

    // Pozitif değer kontrolü
    public static void isValid(double value, String field) {
        if (value < 0) {
            throw new CommerceException(field + MUST_BE_BIGGER_THAN_0, HttpStatus.BAD_REQUEST);
        }
    }

    // Geçerli kategori ID kontrolü
    public static void isCategoryIdValid(String field, long id) {
        if (id < 0 || id > 14) {
            throw new CommerceException(field + ENTER_VALID_CATEGORYID + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void IsUserPresent(User user){
        if(user == null){
            throw new CommerceException(EntityValidator.IS_USER_PRESENT, HttpStatus.NOT_FOUND);
        }
    }

    // Ürün mevcut mu kontrolü
    public static void isProductPresent(boolean isPresent) {
        if (isPresent) {
            throw new CommerceException(IS_PRODUCT_PRESENT, HttpStatus.BAD_REQUEST);
        }
    }

    // Adres mevcut mu kontrolü
    public static void isAddressPresent(boolean isPresent) {
        if (!isPresent) {
            throw new CommerceException(IS_NOT_PRESENT_ADDRESS, HttpStatus.NOT_FOUND);
        }
    }
}
