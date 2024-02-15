package com.smp.coremodule.service;

import com.smp.coremodule.dto.UserDto;
import com.smp.coremodule.exceptions.ObjectNotFoundException;
import com.smp.coremodule.model.User;
import com.smp.coremodule.model.UserRedis;
import com.smp.coremodule.repo.UserRedisRepo;
import com.smp.coremodule.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final KafkaService kafkaService;
    private final UserRedisRepo userRedisRepo;
    private final UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteById( Long id ) {
        userRedisRepo.delete( id );
        User user = entityManager.getReference( User.class, id );
        if( user == null ) {
            logger.error( "User not found" );
            throw new ObjectNotFoundException(" User not found ");
        }
        userRepo.delete( user );
        logger.info(user.getName()+ " this user successfully deleted");
        try {
            kafkaService.userInfoNotify(user.getName() + " this user successfully deleted");
        } catch ( Exception ignored ){

        }
    }

    @Transactional( readOnly = true )
    public List<UserDto> getAllUser() {

        return userRedisRepo.findAll().stream().map( obj -> {
            UserDto userDto = new UserDto();
            getItem( obj, userDto );
            return userDto;
        }).collect(Collectors.toList());
    }

    @Transactional( readOnly = true )
    public UserDto getById( Long id ) {

        UserDto userDto = new UserDto();

        UserRedis userRedis = userRedisRepo.findById( id );
        if( userRedis == null ) {
            Optional<User> user = userRepo.findById( id );
            if( user.isEmpty() ) {
                logger.error( "User not found" );
                throw new ObjectNotFoundException(" User not found ");
            }
            else {
                getItem( user, userDto );
                userRedis = new UserRedis();
                getItem( user, userRedis );
                userRedisRepo.update(userRedis);
            }
        }
        else
            getItem( userRedis, userDto );

        return userDto;
    }

    @Transactional
    public UserDto update( Long id, UserDto userDto ) {

        UserRedis user = userRedisRepo.findById( id );
        if( user == null ) {
            logger.error( "User not found" );
            throw new ObjectNotFoundException(" User not found ");
        }
        userDto.setId( id );

        userAdd( userDto );

        logger.info( "User info successfully updated" );
        try {
            kafkaService.userInfoNotify("User info successfully updated");
        } catch ( Exception ignored ){

        }
        return userDto;
    }

    private User userAdd( UserDto userDto ){
        User user = new User();
        getItem( userDto, user );
        userRepo.save( user );
        UserRedis userRedis = new UserRedis();
        getItem( user, userRedis );
        userRedisRepo.update(userRedis);
        return user;
    }

    @Transactional
    public UserDto crate(UserDto userDto) {
        User user = userAdd( userDto );
        userDto.setId( user.getId() );
        logger.info( "User info successfully added" );
        try {
            kafkaService.userInfoNotify("User info successfully added");
        } catch ( Exception ignored ){

        }
        return userDto;
    }

    private <Req, Res> void getItem ( Req dto, Res res  ) {
        BeanUtils.copyProperties(dto, res);
    }
}
