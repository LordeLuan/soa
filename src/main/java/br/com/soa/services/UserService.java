package br.com.soa.services;

import br.com.soa.domain.User;
import br.com.soa.dto.UserDTO;
import br.com.soa.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public List<UserDTO> listAll(){
        return repository.findAll().stream().map((user)  -> convertToDTO(user)).collect(Collectors.toList());
    }
    @Transactional
    public UserDTO saveUser(UserDTO userDTO){
        validaUsuario(userDTO.getUsername());
        userDTO.setId(null);
        var aluno = convertToEntity(userDTO);
        repository.persist(aluno);
        return convertToDTO(aluno);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO){
        validaUsuario(userDTO.getUsername());
        userDTO.setId(null);
        var aluno = convertToEntity(userDTO);
        repository.persist(aluno);
        return convertToDTO(aluno);
    }



    public void validaUsuario(String usuario){
       var result = repository.findByUsuario(usuario);
       if(result.isPresent()){
           new Exception("Usuário já cadastrado!");
       }
    }
    public UserDTO convertToDTO(User user){
        UserDTO objDto = new UserDTO();
        objDto.setId(user.getId());
        objDto.setName(user.getName());
        objDto.setUsername(user.getUsername());
        objDto.setPassword(user.getPassword());
        objDto.setAge(user.getAge());
        objDto.setWeight(user.getWeight());
        objDto.setHeight(user.getHeight());
        objDto.setProfile(user.getProfile());
        objDto.setUpdateDate(user.getUpdateDate());
        return objDto;
    }
    public User convertToEntity(UserDTO objDto){
        User obj = new User();
        obj.setId(objDto.getId());
        obj.setName(objDto.getName());
        obj.setUsername(objDto.getUsername());
        obj.setPassword(objDto.getPassword());
        obj.setAge(objDto.getAge());
        obj.setWeight(objDto.getWeight());
        obj.setHeight(objDto.getHeight());
        obj.setProfile(objDto.getProfile());
        return obj;
    }
}
