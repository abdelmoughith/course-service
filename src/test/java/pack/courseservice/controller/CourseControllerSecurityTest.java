package pack.courseservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import pack.courseservice.entity.Follow;
import pack.courseservice.repository.FollowRepository;
import pack.courseservice.service.CourseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseControllerSecurityTest {

    @Mock
    private CourseService courseService;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void follow_ShouldSaveFollow_WhenUserIsStudent() {
        // Arrange
        String courseId = "course-123";
        String userId = "1001";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userId);
        doReturn(List.of(new SimpleGrantedAuthority("ROLE_STUDENT")))
                .when(authentication).getAuthorities();

        when(followRepository.save(any(Follow.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Follow result = courseController.follow(courseId);

        // Assert
        assertNotNull(result);
        assertEquals(courseId, result.getCourseId());
        assertEquals(Long.valueOf(userId), result.getStudentId());
        verify(followRepository).save(any(Follow.class));
    }

    @Test
    void follow_ShouldThrowForbidden_WhenUserIsNotStudent() {
        // Arrange
        String courseId = "course-123";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        doReturn(List.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
                .when(authentication).getAuthorities();

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            courseController.follow(courseId);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(followRepository, never()).save(any());
    }
}

