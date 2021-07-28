package org.example;

public class Call {
    private String id;

    public Call(int id) {
        this.id = "Call-" + (id + 1);
    }

    @Override
    public String toString() {
        return "'" + id + "'";
    }
}
