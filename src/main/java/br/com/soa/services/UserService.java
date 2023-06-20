package br.com.soa.services;

import br.com.soa.domain.User;
import br.com.soa.dto.UserDTO;
import br.com.soa.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class);  @Inject
    UserRepository repository;

    public List<UserDTO> listAll(){
    	LOGGER.info("Listagem de usuários acionada!");
        return repository.findAll().stream().map((user)  -> convertToDTO(user)).collect(Collectors.toList());
    }
    @Transactional
    public UserDTO saveUser(UserDTO userDTO){
        System.out.println(userDTO.getProfile());

        validaUsuario(userDTO.getUsername());
        userDTO.setId(null);
        var aluno = convertToEntity(userDTO);
        repository.persist(aluno);
        return convertToDTO(aluno);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO, Integer id){
        validaUsuario(userDTO.getUsername());
        repository.findById(id).orElseThrow(()-> new NotFoundException("User not found!"));
        var user = convertToEntity(userDTO);
        repository.persist(user);
        return convertToDTO(user);
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
        obj.setUpdateDate(objDto.getUpdateDate());
        return obj;
    }

}
