package vn.aptech.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra nếu chưa có admin trong DB thì thêm vào
        if (userRepository.findByEmail("admin@example.com") == null) {
            User admin = new User();
            admin.setFullName("Admin");
            admin.setEmail("admin@example.com");
            admin.setPhone("0123456789");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
            System.out.println("✅ Admin user created!");
        }

        // Kiểm tra nếu chưa có staff thì thêm vào
        if (userRepository.findByEmail("staff@example.com") == null) {
            User staff = new User();
            staff.setFullName("Staff");
            staff.setEmail("staff@example.com");
            staff.setPhone("0112233445");
            staff.setPassword(passwordEncoder.encode("staff123"));
            staff.setRole(User.Role.STAFF);
            userRepository.save(staff);
            System.out.println("✅ Staff user created!");
        }

        // Kiểm tra nếu chưa có customer thì thêm vào
        if (userRepository.findByEmail("customer@example.com") == null) {
            User customer = new User();
            customer.setFullName("Customer");
            customer.setEmail("customer@example.com");
            customer.setPhone("0987654321");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setIdentityCard("uploads/img.png");
            customer.setDriverLicense("uploads/img.png");
            customer.setRole(User.Role.CUSTOMER);
            userRepository.save(customer);
            System.out.println("✅ Customer account created!");
        }
    }
}
