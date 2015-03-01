package com.codepath.apps.simpletwitterclient.models;

import com.orm.SugarRecord;

public class Author extends SugarRecord<Author> {
    String fullName;
    int age;

    public Author() {
    }

    public Author(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }
}