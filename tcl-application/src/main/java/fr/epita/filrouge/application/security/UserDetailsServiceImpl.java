package fr.epita.filrouge.application.security;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private AppUserRepository appUserRepository;

    // ici je transforme mes objets du domain en objets internes de Spring Security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final AppUser appUser = appUserRepository.findbyEmail(email);
        logger.debug("AppUser found with email {}",email);

        return new User(appUser.getEmail(), appUser.getPassword(),getAuthorities(appUser));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(final AppUser appUser) {
        //si on a une liste des Roles pour User
        //final String[] userRoles = appUser.getRoles().stream().map((role) -> role.name()).toArray(String[]::new);
        //ici on gère un seul Role pour User
        final String userRoles = appUser.getRole().toString();
        logger.debug("With User Roles {}", userRoles);
        final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
