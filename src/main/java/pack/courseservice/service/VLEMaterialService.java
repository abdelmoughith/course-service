package pack.courseservice.service;

import pack.courseservice.dto.VLEMaterialCreateUpdateDTO;
import pack.courseservice.dto.VLEMaterialDTO;
import pack.courseservice.entity.MaterialStatus;

import java.util.List;

public interface VLEMaterialService {

    VLEMaterialDTO createVLEMaterial(VLEMaterialCreateUpdateDTO materialDTO);

    VLEMaterialDTO getVLEMaterialById(Long materialId);

    List<VLEMaterialDTO> getVLEMaterialsByCourseId(String courseId);

    List<VLEMaterialDTO> getVLEMaterialsByCourseIdAndStatus(String courseId, MaterialStatus status);

    List<VLEMaterialDTO> getVLEMaterialsByCourseIdAndWeekNumber(String courseId, Integer weekNumber);

    List<VLEMaterialDTO> getAllVLEMaterials();

    List<VLEMaterialDTO> getVLEMaterialsByStatus(MaterialStatus status);

    VLEMaterialDTO updateVLEMaterial(Long materialId, VLEMaterialCreateUpdateDTO materialDTO);

    void deleteVLEMaterial(Long materialId);
}

