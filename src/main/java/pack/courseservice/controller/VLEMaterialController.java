package pack.courseservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.courseservice.dto.VLEMaterialCreateUpdateDTO;
import pack.courseservice.dto.VLEMaterialDTO;
import pack.courseservice.entity.MaterialStatus;
import pack.courseservice.service.VLEMaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vle-materials")
public class VLEMaterialController {

    private static final Logger log = LoggerFactory.getLogger(VLEMaterialController.class);
    private final VLEMaterialService vleMaterialService;

    public VLEMaterialController(VLEMaterialService vleMaterialService) {
        this.vleMaterialService = vleMaterialService;
    }

    @PostMapping
    public ResponseEntity<VLEMaterialDTO> createVLEMaterial(@RequestBody VLEMaterialCreateUpdateDTO materialDTO) {
        log.info("Creating VLE material: {}", materialDTO.getTitle());
        VLEMaterialDTO createdMaterial = vleMaterialService.createVLEMaterial(materialDTO);
        return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<VLEMaterialDTO> getVLEMaterialById(@PathVariable Long materialId) {
        log.info("Fetching VLE material with id: {}", materialId);
        VLEMaterialDTO material = vleMaterialService.getVLEMaterialById(materialId);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<VLEMaterialDTO>> getVLEMaterialsByCourseId(@PathVariable String courseId) {
        log.info("Fetching VLE materials for course: {}", courseId);
        List<VLEMaterialDTO> materials = vleMaterialService.getVLEMaterialsByCourseId(courseId);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/course/{courseId}/status/{status}")
    public ResponseEntity<List<VLEMaterialDTO>> getVLEMaterialsByCourseIdAndStatus(
            @PathVariable String courseId,
            @PathVariable MaterialStatus status) {
        log.info("Fetching VLE materials for course: {} with status: {}", courseId, status);
        List<VLEMaterialDTO> materials = vleMaterialService.getVLEMaterialsByCourseIdAndStatus(courseId, status);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/course/{courseId}/week/{weekNumber}")
    public ResponseEntity<List<VLEMaterialDTO>> getVLEMaterialsByCourseIdAndWeekNumber(
            @PathVariable String courseId,
            @PathVariable Integer weekNumber) {
        log.info("Fetching VLE materials for course: {} with week number: {}", courseId, weekNumber);
        List<VLEMaterialDTO> materials = vleMaterialService.getVLEMaterialsByCourseIdAndWeekNumber(courseId, weekNumber);
        return ResponseEntity.ok(materials);
    }

    @GetMapping
    public ResponseEntity<List<VLEMaterialDTO>> getAllVLEMaterials() {
        log.info("Fetching all VLE materials");
        List<VLEMaterialDTO> materials = vleMaterialService.getAllVLEMaterials();
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VLEMaterialDTO>> getVLEMaterialsByStatus(@PathVariable MaterialStatus status) {
        log.info("Fetching VLE materials with status: {}", status);
        List<VLEMaterialDTO> materials = vleMaterialService.getVLEMaterialsByStatus(status);
        return ResponseEntity.ok(materials);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<VLEMaterialDTO> updateVLEMaterial(
            @PathVariable Long materialId,
            @RequestBody VLEMaterialCreateUpdateDTO materialDTO) {
        log.info("Updating VLE material with id: {}", materialId);
        VLEMaterialDTO updatedMaterial = vleMaterialService.updateVLEMaterial(materialId, materialDTO);
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deleteVLEMaterial(@PathVariable Long materialId) {
        log.info("Deleting VLE material with id: {}", materialId);
        vleMaterialService.deleteVLEMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}
