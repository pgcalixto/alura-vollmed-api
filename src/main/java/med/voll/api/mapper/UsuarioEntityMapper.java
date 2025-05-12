package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.domain.Usuario;
import med.voll.api.entity.UsuarioEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UsuarioEntityMapper {

    Usuario toUsuario(UsuarioEntity usuarioEntity);

}
