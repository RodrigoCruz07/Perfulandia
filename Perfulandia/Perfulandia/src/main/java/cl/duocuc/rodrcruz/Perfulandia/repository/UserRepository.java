package cl.duocuc.rodrcruz.Perfulandia.repository;

import cl.duocuc.rodrcruz.Perfulandia.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users=new ArrayList<>();
    public UserRepository() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2025, Calendar.MARCH, 15);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2025, Calendar.JANUARY, 10);

        Calendar cal3 = Calendar.getInstance();
        cal3.set(2024, Calendar.DECEMBER, 1);

        Calendar cal4 = Calendar.getInstance();
        cal4.set(2024, Calendar.JULY, 25);

        Calendar cal5 = Calendar.getInstance();
        cal5.set(2025, Calendar.APRIL, 3);


        users.add(new User(1, "Pedro", "Alvarez", 29, "pedro.alvarez@email.com", "555-1111", cal1.getTime(),new ArrayList<>()));
        users.add(new User(2, "Laura", "García", 34, "laura.garcia@email.com", "555-2222", cal2.getTime(),new ArrayList<>()));
        users.add(new User(3, "Andrés", "Mendoza", 26, "andres.mendoza@email.com", "555-3333", cal3.getTime(),new ArrayList<>()));
        users.add(new User(4, "Claudia", "Ríos", 31, "claudia.rios@email.com", "555-4444", cal4.getTime(),new ArrayList<>()));
        users.add(new User(5, "Felipe", "Torres", 37, "felipe.torres@email.com", "555-5555", cal5.getTime(),new ArrayList<>()));
    }
    public List<User> getUsers() {
        return users;

    }
}
