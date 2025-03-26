package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.MedicoDto;
import med.voll.api.entity.MedicoEntity;

@Mapper(componentModel = ComponentModel.SPRING, uses = EnderecoEntityMapper.class)
public interface MedicoEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", constant = "true")
    MedicoEntity toMedicoEntity(CadastroMedicoDto cadastroMedicoDto);

    MedicoDto toMedicoDto(MedicoEntity medicoEntity);

}
