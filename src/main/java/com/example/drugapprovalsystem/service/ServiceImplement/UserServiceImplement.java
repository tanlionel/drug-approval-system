package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Role;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.*;
import com.example.drugapprovalsystem.model.DTO.RegisterRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.UserMapper;
import com.example.drugapprovalsystem.repository.RoleRepository;
import com.example.drugapprovalsystem.repository.UserRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final String DEFAULT_PASSWORD = "123456";
    private static final String ACTIVE = "Active";
    private static final String SORT_ASC = "asc";

    @Override
    public User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException {
        User user = userRepository.findByEmail(Email);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return user;
    }

    @Override
    public User login(String email, String password) throws UserDoesNotExistException, InvalidateException {
        User loginUser = userRepository.findByEmail(email);
        if (loginUser == null) throw new UserDoesNotExistException();
        if (!passwordEncoder.matches(password, loginUser.getPassword())) {
            throw new InvalidateException();
        }

        return loginUser;
    }

    @Override
    public User registerUser(RegisterRequestDTO registerUser) throws RoleDoesNotExistException, UserAlreadyExistsException {

        User existUser = userRepository.findByEmail(registerUser.getEmail());
        Optional<Role> existRole = roleRepository.findById(registerUser.getRoleID());
        if (!existRole.isPresent()) throw new RoleDoesNotExistException();
        Role role = existRole.get();
        if (existUser != null) throw new UserAlreadyExistsException();

        User user = User.builder()
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .role(role)
                .gender(registerUser.getGender())
                .dayOfBirth(registerUser.getDob())
                .fullname(registerUser.getFullName())
                .isActive(ACTIVE)
                .username(registerUser.getUsername())
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public Page<UserResponseDTO> getUserPageable(Integer pageNo, Integer pageSize,String sortField,String sortOrder) {
        Pageable pageable;
        if (sortOrder.equals(SORT_ASC)){
            pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortField).ascending());
        }else {
            pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortField).descending());
        }
        Page<User> usersPage = userRepository.findByIsActive(ACTIVE,pageable);
        return usersPage.map(UserMapper::mapToUserResponseDTO);
    }
}
