package com.anvl.service;

import com.anvl.entities.Product;
import com.anvl.entities.User;
import com.anvl.model.UserVo;
import com.anvl.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(Optional<Integer> page){
        log.debug("getUsers started");
        PageRequest pageRequest = PageRequest.of(page.orElseThrow(IllegalAccessError::new), 8);
        Page<User> users = null;
        try {
            users = userRepository.findAll(pageRequest);
        } catch (Exception e) {
            log.error("getUsers exception :: {} ", e);
            return Page.empty(pageRequest);
        }
        log.debug("getUsers ended");
        return users;
    }

    public Long getUserCount() {return userRepository.count();
    }
}
