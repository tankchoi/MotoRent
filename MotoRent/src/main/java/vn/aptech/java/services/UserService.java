package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.aptech.java.dtos.*;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.UserRepository;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private FileStorageService fileStorageService;
    public boolean loginCustomer(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || user.getRole() != User.Role.CUSTOMER) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void registerCustomer(RegisterCustomerDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        if (userRepository.findByPhone(userDto.getPhone()) != null) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            user.setIdentityCard(fileStorageService.saveFile(userDto.getIdentityCard()));
            user.setDriverLicense(fileStorageService.saveFile(userDto.getDriverLicense()));
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }

        user.setRole(User.Role.CUSTOMER);
        userRepository.save(user);
    }
    public void updateCustomer(UpdateCusomerDTO cusomerDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByEmail(currentUsername);
        User existingEmailUser = userRepository.findByEmail(cusomerDTO.getEmail());
        if (existingEmailUser != null && !existingEmailUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        User existingPhoneUser = userRepository.findByPhone(cusomerDTO.getPhone());
        if (existingPhoneUser != null && !existingPhoneUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }

        user.setFullName(cusomerDTO.getFullName());
        user.setEmail(cusomerDTO.getEmail());
        user.setPhone(cusomerDTO.getPhone());
        try {
            if (cusomerDTO.getIdentityCard() != null) {
                fileStorageService.deleteFile(user.getIdentityCard());
                user.setIdentityCard(fileStorageService.saveFile(cusomerDTO.getIdentityCard()));
            }
            if (cusomerDTO.getDriverLicense() != null) {
                fileStorageService.deleteFile(user.getDriverLicense());
                user.setDriverLicense(fileStorageService.saveFile(cusomerDTO.getDriverLicense()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
        userRepository.save(user);
    }
    public void updateStaff(UpdateStaffDTO staffDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByEmail(currentUsername);
        User existingEmailUser = userRepository.findByEmail(staffDTO.getEmail());
        if (existingEmailUser != null && !existingEmailUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        User existingPhoneUser = userRepository.findByPhone(staffDTO.getPhone());
        if (existingPhoneUser != null && !existingPhoneUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }
        user.setFullName(staffDTO.getFullName());
        user.setEmail(staffDTO.getEmail());
        user.setPhone(staffDTO.getPhone());
        userRepository.save(user);
    }
    public List<CustomerDTO> getAllCustomers() {
        List<User> users = userRepository.findAllByRole(User.Role.CUSTOMER);
        return users.stream().map(CustomerDTO::new).toList();
    }
    public List<StaffDTO> getAllStaffs() {
        List<User> users = userRepository.findAllByRoleNot(User.Role.CUSTOMER);
        return users.stream().map(StaffDTO::new).toList();
    }
    public void createStaff(CreateStaffDTO staffDTO) {
        if (userRepository.findByEmail(staffDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        if (userRepository.findByPhone(staffDTO.getPhone()) != null) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }
        User user = new User();
        user.setFullName(staffDTO.getFullName());
        user.setEmail(staffDTO.getEmail());
        user.setPhone(staffDTO.getPhone());
        user.setPassword(passwordEncoder.encode(staffDTO.getPassword()));
        user.setRole(staffDTO.getRole());
        userRepository.save(user);
    }
    public void updateStaffByAdmin(UpdateStaffByAdminDTO staffDTO) {
        User user = userRepository.findById(staffDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));

        User existingEmailUser = userRepository.findByEmail(staffDTO.getEmail());
        if (existingEmailUser != null && !existingEmailUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        User existingPhoneUser = userRepository.findByPhone(staffDTO.getPhone());
        if (existingPhoneUser != null && !existingPhoneUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }
        user.setEmail(staffDTO.getEmail());
        user.setFullName(staffDTO.getFullName());
        user.setPhone(staffDTO.getPhone());
        user.setRole(staffDTO.getRole());

        if (staffDTO.getPassword() != null && !staffDTO.getPassword().isBlank()) {
            if (staffDTO.getPassword().length() < 6) {
                throw new IllegalArgumentException("Mật khẩu phải có ít nhất 6 ký tự");
            }
            user.setPassword(passwordEncoder.encode(staffDTO.getPassword()));
        }

        userRepository.save(user);
    }
    public void deleteStaff(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));

        if (user.getRole() == User.Role.CUSTOMER) {
            throw new IllegalArgumentException("Không thể xóa người dùng có vai trò CUSTOMER");
        }

        userRepository.delete(user);
    }




}
