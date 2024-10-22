package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Role;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.*;
import com.example.drugapprovalsystem.model.DTO.ChangePasswordRequestDTO;
import com.example.drugapprovalsystem.model.DTO.RegisterRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateUserRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.UserMapper;
import com.example.drugapprovalsystem.repository.RoleRepository;
import com.example.drugapprovalsystem.repository.UserRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.EmailService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;
    private static final String DEFAULT_PASSWORD = "123456";
    private static final String ACTIVE = "Active";
    private static final String DEACTIVATE = "Deactivate";
    private static final String SORT_ASC = "asc";
    private static final int DEFAULT_PASSWORD_LENGTH = 7;

    //BACKGROUND SEND NOTIFICATION
    @Async
    void sendNotification(String email, String password) {
        emailService.sendMail(email,
                Common.REGISTER_NOTIFICATION_CONTENT,
                String.format(Common.REGISTER_NOTIFICATION_TES_TEXT, email, password));
    }

    @Override
    public User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException {
        User user = userRepository.findByEmail(Email);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return user;
    }

    @Override
    public User login(String email, String password) throws UserDoesNotExistException, InvalidateException, AccountSuspendedException {
        User loginUser = userRepository.findByEmail(email);
        if (loginUser == null) throw new UserDoesNotExistException();
        if (!loginUser.getIsActive().equals(ACTIVE)) throw new AccountSuspendedException();
        if (!passwordEncoder.matches(password, loginUser.getPassword())) {
            throw new InvalidateException();
        }

        return loginUser;
    }
    private String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
    @Override
    public User registerUser(RegisterRequestDTO registerUser) throws RoleDoesNotExistException, UserAlreadyExistsException {
//        User existUser = userRepository.findByEmail(registerUser.getEmail());
//
        boolean isExistUser = userRepository.findByEmail(registerUser.getEmail()) != null;
//        ||userRepository.findByUsername(registerUser.getUsername()) != null;

        Optional<Role> existRole = roleRepository.findById(registerUser.getRoleID());
        if (existRole.isEmpty()) throw new RoleDoesNotExistException();
        Role role = existRole.get();
        if (isExistUser) throw new UserAlreadyExistsException();

        String passwordGenerated = generatePassword(DEFAULT_PASSWORD_LENGTH);

        User user = User.builder()
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(passwordGenerated))
                .role(role)
                .gender(registerUser.getGender())
                .dayOfBirth(registerUser.getDob())
                .fullname(registerUser.getFullName())
                .isActive(ACTIVE)
                .username(registerUser.getUsername())
                .build();

        User result = userRepository.save(user);

        sendNotification(result.getEmail(), passwordGenerated);

        System.out.println("Register user module: " + "Send mail successfully!");

        return user;
    }

    @Override
    public Page<UserResponseDTO> getUserPageable(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String roleName, String status, Integer gender,String search) {
        Pageable pageable;
        if (sortOrder.equals(SORT_ASC)) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
        }
        Page<User> userPageable;
        if (roleName == null || roleName.isEmpty()) roleName = "";
        if (status == null || status.isEmpty()) status = "";
        if (gender == null) {
            userPageable = userRepository.findByRoleNameContainingAndIsActiveContainingAndFullnameContaining(roleName, status,search, pageable);
        } else {
            userPageable = userRepository.findByRoleNameContainingAndIsActiveContainingAndGenderAndFullnameContaining(roleName, status, gender,search, pageable);
        }
        return userPageable.map(UserMapper::mapToUserResponseDTO);
    }

    @Override
    public User activateUser(String email) throws UserDoesNotExistException, UserAlreadyActiveException {
        User user=userRepository.findByEmail(email);
        if (user==null) throw new UserDoesNotExistException();
        if (user.getIsActive().equals(ACTIVE)) throw new UserAlreadyActiveException();
        user.setIsActive(ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User deactivateUser(String email) throws UserDoesNotExistException, UserAlreadyDeactivateException {
        User user=userRepository.findByEmail(email);
        if (user==null) throw new UserDoesNotExistException();
        if (user.getIsActive().equals(DEACTIVATE)) throw new UserAlreadyDeactivateException();
        user.setIsActive(DEACTIVATE);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserRequestDTO updateUserRequestDTO,String email) throws UserDoesNotExistException {
        User user=userRepository.findByEmail(email);
        if (user==null) throw new UserDoesNotExistException();
        if (updateUserRequestDTO.getGender()!=null) user.setGender(updateUserRequestDTO.getGender());
        if (updateUserRequestDTO.getFullName()!=null ) user.setFullname(updateUserRequestDTO.getFullName());
        if (updateUserRequestDTO.getDayOfBirth()!=null) user.setDayOfBirth(updateUserRequestDTO.getDayOfBirth());
        return userRepository.save(user);
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            return userRepository.findByEmail(username);
        }

        return null;
    }

    @Override
    public UserResponseDTO uploadAvatar(String userEmail, String userImage) throws UserDoesNotExistException {
        User user = userRepository.findByEmail(userEmail);
        if (user==null) throw new UserDoesNotExistException();
        user.setAvatar(userImage);
        return UserMapper.mapToUserResponseDTO(userRepository.save(user));
    }

    @Override
    public User changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if (user == null) {
            throw new UserDoesNotExistException();
        }

        String currentPassword = changePasswordRequestDTO.getCurrentPassword();
        String newPassword = changePasswordRequestDTO.getNewPassword();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidateException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    public List<UserResponseDTO> getAdmin(String email) {
        if (email==null){
            return userRepository.findUserByRoleNameAndIsActive(Common.ADMIN,ACTIVE).stream().map(UserMapper::mapToUserResponseDTO).toList();
        }
        return userRepository.findUserByRoleNameAndIsActiveAndEmailContaining(Common.ADMIN,ACTIVE,email).stream().map(UserMapper::mapToUserResponseDTO).toList();
    }

    @Override
    public List<UserResponseDTO> getSecretary(String email) {
        if (email==null){
            return userRepository.findUserByRoleNameAndIsActive(Common.SECRETARY,ACTIVE).stream().map(UserMapper::mapToUserResponseDTO).toList();
        }
        return userRepository.findUserByRoleNameAndIsActiveAndEmailContaining(Common.SECRETARY,ACTIVE,email).stream().map(UserMapper::mapToUserResponseDTO).toList();
    }

    @Override
    public List<UserResponseDTO> getUser(String email) {
        if (email==null){
            return userRepository.findUserByIsActive(ACTIVE).stream().map(UserMapper::mapToUserResponseDTO).toList();
        }
        return userRepository.findUserByIsActiveAndEmailContaining(ACTIVE,email).stream().map(UserMapper::mapToUserResponseDTO).toList();
    }

}
