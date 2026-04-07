package com.emmanuel.repository.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.emmanuel.model.Customer;

/**
 * MySqlCRUD.java
 * * Clase que implementa operaciones CRUD sobre la tabla customers en MySQL.
 * Conexión mediante JDBC.
 * * Autor: Clase POO
 * Propósito: Gestionar clientes en la base de datos mediante Java.
 */

public class MySqlCRUD
{


	// Configuración de la conexión a la base de datos
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3308/testdb?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "MiOtraPassword123";//"MiPasswordSegura1234";

	/**
     * Inserta un cliente en la base de datos si no existe previamente.
     *
     * @param customers Objeto Customer a insertar
     */
    public void insertCustomer(Customer customers)
    {
        if (customerExists(customers.getId()))
	    {
		System.out.println(" usuario con ID " + customers.getId() + " ya existe. salta insercion");
		return;
	    }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		// Sentencia SQL corregida para 6 valores
		String sql = "INSERT INTO customers (id, first_name, last_name, age, email) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql))
		    {
			stmt.setInt(1, customers.getId());
			stmt.setString(2, customers.getFirstName());
			stmt.setString(3, customers.getLastName());
			stmt.setInt(4, customers.getAge());
			stmt.setString(5, customers.getEmail());
			//stmt.setInt(6, customers.getIdSuscripcion()); // Usa el nuevo getter
			stmt.executeUpdate();
			
			System.out.println(" usuario " + customers.getFirstName() + " insertado exitosamente.");
		    }
	    }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
    }
	
	/**
     * Lee y muestra todos los clientes almacenados en la base de datos.
     */
    public void readCustomer()
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		// Sentencia SQL con JOIN para obtener todos los datos, incluyendo el type_name
		String sql = "SELECT cus.*  FROM customers cus;";
		try (PreparedStatement stmt = connection.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery())
		    {
			System.out.println("\n Usuarios en MySQL:");
			while (rs.next())
			    {
				// El constructor de 7 argumentos ahora será encontrado por el compilador
				System.out.println(new Customer(rs.getInt("id"),
								rs.getString("first_name"),
								rs.getString("last_name"),
								rs.getInt("age"),
								rs.getString("email")
								).toStringPersonalizado());
			    }
			
		    }
        }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
    }
    
    /**
     * Actualiza el nombre de un cliente según su ID.
     *
     * @param id          ID del cliente a actualizar
     * @param newFirstName Nuevo nombre del cliente
     */
    public void updateCustomer(int id, String newFirstName)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		String sql = "UPDATE customers SET first_name = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql))
		    {
			stmt.setString(1, newFirstName);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			System.out.println(" Customer ID " + id + " updated to " + newFirstName + ".");
		    }
	    }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
    }
    
	/**
     * Elimina un cliente según su ID.
     *
     * @param id ID del cliente a eliminar
     */
    public void deleteCustomer(int id)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		String sql = "DELETE FROM customers WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql))
		    {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println(" Customer ID " + id + " deleted.");
		    }
	    }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
    }
    
    /**
     * Verifica si un cliente con el ID proporcionado existe en la base de datos.
     *
     * @param id ID del cliente
     * @return true si existe, false en caso contrario
     */
    private boolean customerExists(int id)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		String sql = "SELECT COUNT(*) FROM customers WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql))
		    {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery())
			    {
				return rs.next() && rs.getInt(1) > 0;
			    }
		    }
	    }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	
        return false;
    }


    /**
     * Actualiza la suscripción de un cliente según su ID.
     *
     * @param id ID del cliente a actualizar
     * @param id_suscripcion Nuevo ID de suscripción
     */
    public void updateCustomerSuscription(int id, int id_suscripcion)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
	    {
		// Sentencia SQL correcta para UPDATE
		String sql = "UPDATE customers SET id_suscripcion = ? WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql))
		    {
			stmt.setInt(1, id_suscripcion);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			
			System.out.println(" Customer ID " + id + " actualizado a la suscripción ID: " + id_suscripcion + ".");
		    }
	    }
	catch (SQLException e)
	    {
		e.printStackTrace();
	    }
    }



}
