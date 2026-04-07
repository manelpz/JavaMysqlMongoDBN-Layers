package com.emmanuel.model;
/**
 * Customer.java
 * * Modelo de datos para representar un cliente.
 * Contiene atributos básicos y métodos getters/setters.
 * * Autor: Clase POO
 * Propósito: Proporcionar la estructura de un cliente para el CRUD.
 */

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    // NUEVOS CAMPOS
    //private int idSuscripcion; 
    //private String typeName;

    // 1. Constructor de 5 argumentos (Original)
    public Customer(int id, String firstName, String lastName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
    
    // 2. Constructor de 6 argumentos (Para INSERTAR/CREAR) - Resuelve el error de duplicado.
    /*public Customer(int id, String firstName, String lastName, int age, String email, int idSuscripcion) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.idSuscripcion = idSuscripcion;
    }*/
    
    // 3. Constructor de 7 argumentos (Para LEER/READ con JOIN) - Resuelve el error de constructor no encontrado.
    /*public Customer(int id, String firstName, String lastName, int age, String email, int idSuscripcion, String typeName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.idSuscripcion = idSuscripcion;
        this.typeName = typeName;
    }*/

    // getters setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Getters para los nuevos campos
    /*public int getIdSuscripcion() { 
        return idSuscripcion;
    }*/
    
    /*public String getTypeName() {
        return typeName;
    }*/
    
    // toStringpersonalizado metodo
    public String toStringPersonalizado() {
        String result = "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email;
        /*if (typeName != null) {
            result += ", Subscription Type=" + typeName;
        }*/
        result += "]";
        return result;
    }

}
