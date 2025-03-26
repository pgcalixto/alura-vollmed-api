package med.voll.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import med.voll.api.dto.ListagemMedicoDto;
import med.voll.api.entity.MedicoEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ListagemMedicoDtoMapper {

    ListagemMedicoDto toListagemMedicoDto(MedicoEntity medico);

}
