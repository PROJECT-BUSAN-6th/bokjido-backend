package com.projectbusan.bokjido.auth;

import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String userid) throws UsernameNotFoundException {
        User findUser = userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with this id. -> " + userid));

        if(findUser != null){
            UserDetailsImpl userDetails = new UserDetailsImpl(findUser);
            return  userDetails;
        }

        return null;
    }
}
