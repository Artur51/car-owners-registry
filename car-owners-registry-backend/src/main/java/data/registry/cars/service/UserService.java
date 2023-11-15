package data.registry.cars.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import data.registry.cars.model.User;
import data.registry.cars.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public Page<User> page(
            PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public User read(
            int id) {
        return userRepository.getReferenceById(id);
    }

    public Page<User> findByName(
            String name,
            PageRequest pageRequest) {
        return userRepository.findByNameContains(name, pageRequest);
    }
}
