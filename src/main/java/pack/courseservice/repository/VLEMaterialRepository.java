package pack.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.courseservice.entity.VLEMaterial;
import pack.courseservice.entity.MaterialStatus;

import java.util.List;

@Repository
public interface VLEMaterialRepository extends JpaRepository<VLEMaterial, Long> {

    List<VLEMaterial> findByCourseId(String courseId);

    List<VLEMaterial> findByCourseIdAndStatus(String courseId, MaterialStatus status);

    List<VLEMaterial> findByCourseIdAndWeekNumber(String courseId, Integer weekNumber);

    List<VLEMaterial> findByStatus(MaterialStatus status);
}

