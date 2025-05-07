package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.dto.CadastroPacienteDto;
import med.voll.api.dto.PacienteDto;
import med.voll.api.entity.PacienteEntity;

@Mapper(componentModel = ComponentModel.SPRING, uses = EnderecoEntityMapper.class)
public interface PacienteEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", constant = "true")
    PacienteEntity toPacienteEntity(CadastroPacienteDto cadastroPacienteDto);

    PacienteDto toPacienteDto(PacienteEntity pacienteEntity);

}
