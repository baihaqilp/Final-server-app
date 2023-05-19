package id.co.metrodata.serverApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.metrodata.serverApp.models.AppUserDetail;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByUsernameOrEmployee_Email(username, username).orElseThrow(
                () -> new UsernameNotFoundException("Username and Email not found"));
        return new AppUserDetail(user);
    }

}
