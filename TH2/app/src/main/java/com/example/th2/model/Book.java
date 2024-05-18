package com.example.th2.model;

public class Book {
    private int id, rating;
    private String name, author, scope;
    private boolean cntt, vt, dt;

    private Book(Book.builder builder) {
        this.id = builder.id;
        this.rating = builder.rating;
        this.name = builder.name;
        this.author = builder.author;
        this.scope = builder.scope;
        this.cntt = builder.cntt;
        this.vt = builder.vt;
        this.dt = builder.dt;
    }

    public static class builder{
        public int id, rating;
        public String name, author, scope;
        public boolean cntt, vt, dt;

        public Book build(){
            return new Book(this);
        }

        public builder id(int id) {
            this.id = id;
            return this;
        }

        public builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public builder name(String name) {
            this.name = name;
            return this;
        }

        public builder author(String author) {
            this.author = author;
            return this;
        }

        public builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public builder cntt(boolean cntt) {
            this.cntt = cntt;
            return this;
        }

        public builder vt(boolean vt) {
            this.vt = vt;
            return this;
        }

        public builder dt(boolean dt) {
            this.dt = dt;
            return this;
        }
    }
}