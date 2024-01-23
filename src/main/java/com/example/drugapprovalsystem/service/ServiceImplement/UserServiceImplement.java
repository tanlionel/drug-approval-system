package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Role;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.*;
import com.example.drugapprovalsystem.model.DTO.RegisterRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateUserRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.UserMapper;
import com.example.drugapprovalsystem.repository.RoleRepository;
import com.example.drugapprovalsystem.repository.UserRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private static final String DEACTIVATE = "Deactivate";
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
    public User login(String email, String password) throws UserDoesNotExistException, InvalidateException, AccountSuspendedException {
        User loginUser = userRepository.findByEmail(email);
        if (loginUser == null) throw new UserDoesNotExistException();
        if (!loginUser.getIsActive().equals(ACTIVE)) throw new AccountSuspendedException();
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
    public Page<UserResponseDTO> getUserPageable(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String roleName, String status, Integer gender) {
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
            userPageable = userRepository.findByRoleNameContainingAndIsActiveContaining(roleName, status, pageable);
        } else {
            userPageable = userRepository.findByRoleNameContainingAndIsActiveContainingAndGender(roleName, status, gender, pageable);
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
}
