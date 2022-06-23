package com.example.PPE1.Services;

import com.example.PPE1.DAO.UserRepository;
import com.example.PPE1.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {
	@Autowired
	UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = ur.findByUsernameOrEmail(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }


	public List<User> getAll() {
		return ur.findAll();
	}

	public void save(User u) {
		ur.save(u);
	}

	public void removeById(Long id) {
		ur.deleteById(id);
	}

	public Optional<User> getById(Long id) {
		return ur.findById(id);
	}
	
}
