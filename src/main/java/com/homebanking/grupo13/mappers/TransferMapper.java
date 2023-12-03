package com.homebanking.grupo13.mappers;


import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDTO;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TransferMapper {

    public static Transfer dtoToTransfer(TransferDTO dto) {
        Transfer transfer = new Transfer();
        transfer.setId(dto.getId());
        transfer.setAmount(dto.getAmount());

        /*  TODO: Implementar esto cuando Account este disponible
        transfer.setSource(dto.getSource());
        transfer.setDestine(dto.getDestine());
        */

        transfer.setCreateAt(dto.getCreateAt());
        return transfer;
    }

    public static TransferDTO transferToDto(Transfer transfer) {
        TransferDTO dto = new TransferDTO();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());

        /* TODO: Implementar esto cuando Account este disponible
        dto.setSource(transfer.getSource());
        dto.setDestine(transfer.getDestine());
        */

        dto.setCreateAt(transfer.getCreateAt());
        return dto;
    }
}
