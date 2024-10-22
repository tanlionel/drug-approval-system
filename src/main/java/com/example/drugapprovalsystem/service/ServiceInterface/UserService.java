package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.*;
import com.example.drugapprovalsystem.model.DTO.ChangePasswordRequestDTO;
import com.example.drugapprovalsystem.model.DTO.RegisterRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateUserRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {
    User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException;
    User login(String email, String password) throws UserDoesNotExistException, AccountSuspendedException, InvalidateException;
    User registerUser(RegisterRequestDTO registerUser) throws RoleDoesNotExistException, UserAlreadyExistsException;
    Page<UserResponseDTO> getUserPageable(Integer pageNo,Integer pageSize,String sortField,String sortOrder,String roleName,String status,Integer gender,String search);
    User activateUser(String email) throws UserDoesNotExistException, UserAlreadyActiveException;
    User deactivateUser(String email) throws UserDoesNotExistException, UserAlreadyDeactivateException;
    User updateUser(UpdateUserRequestDTO updateUserRequestDTO,String email) throws UserDoesNotExistException;
    User getLoginUser();
    UserResponseDTO uploadAvatar(String userEmail, String userImage) throws UserDoesNotExistException;
    User changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String email) throws Exception;
    List<UserResponseDTO> getAdmin(String email);
    List<UserResponseDTO> getSecretary(String email);
    List<UserResponseDTO> getUser(String email);
}
