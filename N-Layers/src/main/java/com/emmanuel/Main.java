package com.emmanuel;
import com.emmanuel.model.Customer;
import com.emmanuel.repository.mongodb.MongoCRUD;
import com.emmanuel.repository.mysql.MySqlCRUD;
import com.emmanuel.service.CustomerService;

public class Main {
    public static void main(String[] args) {

        Customer c1 = new Customer(5, "Ruben", "Michan", 24, "ruben@gmail.com");
        Customer c2 = new Customer(6, "Andrés", "Ruiz", 25, "andres@example.com");


        MySqlCRUD mysql = new MySqlCRUD();
        MongoCRUD mongo = new MongoCRUD();

        CustomerService service = new CustomerService(mysql, mongo);   
        service.run(c1, c2);
        
        
    }
}
