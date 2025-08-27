package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.UserRepository;
import fon.e_dnevnik.dto.UserDTO;
import fon.e_dnevnik.entity.User;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImplementation implements ServiceInterface<UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserImplementation(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for(User user: users){

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public UserDTO findById(Object id) throws Exception {
        Optional<User> user=userRepository.findById((String) id);
        UserDTO userDTO;
        if(user.isPresent()) {
            userDTO = modelMapper.map(user.get(), UserDTO.class);
            return userDTO;
        }
        else{
            throw new Exception("Ne postoji user");
        }
    }

    @Override
    public UserDTO save(UserDTO userDTO) throws Exception {
        return null;
    }
}
