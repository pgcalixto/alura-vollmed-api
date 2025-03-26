package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.dto.ListagemPacienteDto;
import med.voll.api.entity.PacienteEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ListagemPacienteDtoMapper {

    ListagemPacienteDto toListagemPacienteDto(PacienteEntity paciente);

}
