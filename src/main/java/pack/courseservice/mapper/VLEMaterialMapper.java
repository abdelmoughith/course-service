package pack.courseservice.mapper;

import org.springframework.stereotype.Component;
import pack.courseservice.dto.VLEMaterialCreateUpdateDTO;
import pack.courseservice.dto.VLEMaterialDTO;
import pack.courseservice.entity.VLEMaterial;

@Component
public class VLEMaterialMapper {

    public VLEMaterialDTO toDTO(VLEMaterial material) {
        if (material == null) {
            return null;
        }

        VLEMaterialDTO dto = new VLEMaterialDTO();
        dto.setId(material.getId());
        dto.setTitle(material.getTitle());
        dto.setDescription(material.getDescription());
        dto.setMaterialType(material.getMaterialType());
        dto.setContentUrl(material.getContentUrl());
        dto.setStatus(material.getStatus());
        dto.setWeekNumber(material.getWeekNumber());
        dto.setCreatedAt(material.getCreatedAt());
        dto.setUpdatedAt(material.getUpdatedAt());
        dto.setCourseId(material.getCourse().getId());

        return dto;
    }

    public VLEMaterial toEntity(VLEMaterialCreateUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        VLEMaterial material = new VLEMaterial();
        updateEntityFromDTO(dto, material);

        return material;
    }

    public void updateEntityFromDTO(VLEMaterialCreateUpdateDTO dto, VLEMaterial material) {
        if (dto == null) {
            return;
        }

        if (dto.getTitle() != null) {
            material.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            material.setDescription(dto.getDescription());
        }
        if (dto.getMaterialType() != null) {
            material.setMaterialType(dto.getMaterialType());
        }
        if (dto.getContentUrl() != null) {
            material.setContentUrl(dto.getContentUrl());
        }
        if (dto.getStatus() != null) {
            material.setStatus(dto.getStatus());
        }
        if (dto.getWeekNumber() != null) {
            material.setWeekNumber(dto.getWeekNumber());
        }
    }
}

