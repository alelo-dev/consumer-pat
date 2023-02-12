package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.User;
import br.com.alelo.consumer.consumerpat.exception.ServiceException;
import br.com.alelo.consumer.consumerpat.exception.ServiceWarningException;
import br.com.alelo.consumer.consumerpat.repository.UserRepository;
import br.com.alelo.consumer.consumerpat.utils.AESUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = ServiceException.class)
    public void saveUser(User user) throws ServiceWarningException {

        userRepository.findByName(user.getName())
            .ifPresent(s -> {
                throw new ServiceWarningException("Username already exists");
            });

        try {
            String cipherText = AESUtil.encryptText(user.getPassword());
            user.setPassword(cipherText);

            userRepository.save(user);
        }catch (Exception e) {
            log.error("Error while saving user", e);
            throw new ServiceException("User could not be created");
        }

    }

}