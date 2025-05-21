package cl.duocuc.rodrcruz.Perfulandia.repository;

import cl.duocuc.rodrcruz.Perfulandia.model.Seller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SellerRepository {
    private final List<Seller> sellers=new ArrayList<>();
    public SellerRepository() {
        sellers.add(new Seller(1, "Carlos", "Ramírez", 35, "Av. Central 123", "555-1234", "carlos.ramirez@email.com", "Administrador"));
        sellers.add(new Seller(2, "María", "López", 28, "Calle Fénix 45", "555-5678", "maria.lopez@email.com", "Vendedor"));
        sellers.add(new Seller(3, "Luis", "Torres", 30, "Av. del Sol 89", "555-2468", "luis.torres@email.com", "Vendedor"));
        sellers.add(new Seller(4, "Ana", "Martínez", 27, "Calle Luna 321", "555-1357", "ana.martinez@email.com", "Vendedor"));
        sellers.add(new Seller(5, "Jorge", "Pérez", 32, "Av. Aurora 12", "555-7890", "jorge.perez@email.com", "Vendedor"));
        sellers.add(new Seller(6, "Lucía", "Gómez", 25, "Calle Brisa 56", "555-1010", "lucia.gomez@email.com", "Vendedor"));
        sellers.add(new Seller(7, "Daniel", "Fernández", 29, "Av. Norte 200", "555-2020", "daniel.fernandez@email.com", "Vendedor"));
        sellers.add(new Seller(8, "Sofía", "Morales", 31, "Calle Sur 78", "555-3030", "sofia.morales@email.com", "Vendedor"));
        sellers.add(new Seller(9, "Ricardo", "Salinas", 33, "Av. Libertad 9", "555-4040", "ricardo.salinas@email.com", "Vendedor"));
        sellers.add(new Seller(10, "Elena", "Vargas", 26, "Calle Jardín 101", "555-5050", "elena.vargas@email.com", "Vendedor"));

    }
    public List<Seller> getSellers() {
        return sellers;
    }
}
