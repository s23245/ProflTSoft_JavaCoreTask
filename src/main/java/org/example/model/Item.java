package org.example.model;

/**
 * The Item class is a record class in Java
 * It is used to model immutable data. This class represents an item with a value and a count.
 * The value is a String and the count is an integer.
 */
public record Item(String value, int count) {
}