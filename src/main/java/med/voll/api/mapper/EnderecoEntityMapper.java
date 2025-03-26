package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.dto.EnderecoDto;
import med.voll.api.entity.EnderecoEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface EnderecoEntityMapper {

    EnderecoEntity toEndereco(EnderecoDto enderecoDto);

}
