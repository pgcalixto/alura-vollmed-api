package med.voll.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.mapper.UsuarioEntityMapper;
import med.voll.api.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioEntityMapper usuarioEntityMapper;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        final var usuarioEntity = usuarioRepository.findByLogin(login);

        return usuarioEntityMapper.toUsuario(usuarioEntity);
    }

}
