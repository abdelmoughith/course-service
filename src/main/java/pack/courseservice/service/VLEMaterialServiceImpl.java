package pack.courseservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pack.courseservice.dto.VLEMaterialCreateUpdateDTO;
import pack.courseservice.dto.VLEMaterialDTO;
import pack.courseservice.entity.Course;
import pack.courseservice.entity.MaterialStatus;
import pack.courseservice.entity.VLEMaterial;
import pack.courseservice.exception.ResourceNotFoundException;
import pack.courseservice.mapper.VLEMaterialMapper;
import pack.courseservice.repository.CourseRepository;
import pack.courseservice.repository.VLEMaterialRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VLEMaterialServiceImpl implements VLEMaterialService {

    private static final Logger log = LoggerFactory.getLogger(VLEMaterialServiceImpl.class);
    private final VLEMaterialRepository vleMaterialRepository;
    private final CourseRepository courseRepository;
    private final VLEMaterialMapper materialMapper;

    public VLEMaterialServiceImpl(VLEMaterialRepository vleMaterialRepository, CourseRepository courseRepository, VLEMaterialMapper materialMapper) {
        this.vleMaterialRepository = vleMaterialRepository;
        this.courseRepository = courseRepository;
        this.materialMapper = materialMapper;
    }

    @Override
    public VLEMaterialDTO createVLEMaterial(VLEMaterialCreateUpdateDTO materialDTO) {
        log.info("Creating new VLE material for course: {}", materialDTO.getCourseId());

        Course course = courseRepository.findById(materialDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + materialDTO.getCourseId()));

        VLEMaterial material = materialMapper.toEntity(materialDTO);
        material.setCourse(course);
        VLEMaterial savedMaterial = vleMaterialRepository.save(material);

        log.info("VLE material created successfully with id: {}", savedMaterial.getId());
        return materialMapper.toDTO(savedMaterial);
    }

    @Override
    @Transactional(readOnly = true)
    public VLEMaterialDTO getVLEMaterialById(Long materialId) {
        log.info("Fetching VLE material with id: {}", materialId);
        VLEMaterial material = vleMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("VLE material not found with id: " + materialId));
        return materialMapper.toDTO(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VLEMaterialDTO> getVLEMaterialsByCourseId(String courseId) {
        log.info("Fetching VLE materials for course: {}", courseId);
        return vleMaterialRepository.findByCourseId(courseId)
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VLEMaterialDTO> getVLEMaterialsByCourseIdAndStatus(String courseId, MaterialStatus status) {
        log.info("Fetching VLE materials for course: {} with status: {}", courseId, status);
        return vleMaterialRepository.findByCourseIdAndStatus(courseId, status)
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VLEMaterialDTO> getVLEMaterialsByCourseIdAndWeekNumber(String courseId, Integer weekNumber) {
        log.info("Fetching VLE materials for course: {} with week number: {}", courseId, weekNumber);
        return vleMaterialRepository.findByCourseIdAndWeekNumber(courseId, weekNumber)
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VLEMaterialDTO> getAllVLEMaterials() {
        log.info("Fetching all VLE materials");
        return vleMaterialRepository.findAll()
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VLEMaterialDTO> getVLEMaterialsByStatus(MaterialStatus status) {
        log.info("Fetching VLE materials with status: {}", status);
        return vleMaterialRepository.findByStatus(status)
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VLEMaterialDTO updateVLEMaterial(Long materialId, VLEMaterialCreateUpdateDTO materialDTO) {
        log.info("Updating VLE material with id: {}", materialId);

        VLEMaterial material = vleMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("VLE material not found with id: " + materialId));

        if (materialDTO.getCourseId() != null && !materialDTO.getCourseId().equals(material.getCourse().getId())) {
            Course course = courseRepository.findById(materialDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + materialDTO.getCourseId()));
            material.setCourse(course);
        }

        materialMapper.updateEntityFromDTO(materialDTO, material);
        VLEMaterial updatedMaterial = vleMaterialRepository.save(material);

        log.info("VLE material updated successfully with id: {}", updatedMaterial.getId());
        return materialMapper.toDTO(updatedMaterial);
    }

    @Override
    public void deleteVLEMaterial(Long materialId) {
        log.info("Deleting VLE material with id: {}", materialId);

        if (!vleMaterialRepository.existsById(materialId)) {
            throw new ResourceNotFoundException("VLE material not found with id: " + materialId);
        }

        vleMaterialRepository.deleteById(materialId);
        log.info("VLE material deleted successfully with id: {}", materialId);
    }
}
