package com.emmanuel.service;

import com.emmanuel.repository.mongodb.MongoCRUD;
import com.emmanuel.repository.mysql.MySqlCRUD;
import com.emmanuel.model.Customer;

public class CustomerService {
    private MySqlCRUD mysql;
    private MongoCRUD mongo;

    public CustomerService(MySqlCRUD mysql, MongoCRUD mongo) {
        this.mysql = mysql;
        this.mongo = mongo;
    }
    
    public void run(Customer c1, Customer c2) {

        System.out.println("Iniciando CustomerService...");

        // MySQL CRUD
        //MySqlCRUD conn = new MySqlCRUD();

        System.out.println("La conexión está lista para ser usada.");

        System.out.println("\n===== insertar cliente =====");
        mysql.insertCustomer(c1);
        mysql.insertCustomer(c2);

        System.out.println("\n===== leer clientes =====");
        mysql.readCustomer();

        System.out.println("\n===== actualizar cliente =====");
        mysql.updateCustomer(5, "Leonardo");
        mysql.updateCustomer(6, "Carlos");


        System.out.println("\n===== MongoDB INSERT Customers =====");
        mongo.insertCustomer(c1);
        mongo.insertCustomer(c2);

        System.out.println("\n===== MongoDB READ Customers =====");
        mongo.readCustomers();

        System.out.println("\n===== MongoDB UPDATE Customer =====");
        mongo.updateCustomer("juanito", "abichuelas");
        mongo.readCustomers();

        System.out.println("\n===== MongoDB DELETE Customer =====");
        mongo.deleteCustomer("Ana");
        mongo.readCustomers();
}

}
