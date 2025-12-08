-- Course Service Database Schema and Sample Data

-- Create Database
CREATE DATABASE IF NOT EXISTS courseservicedb;
USE courseservicedb;

-- Course Table
CREATE TABLE IF NOT EXISTS courses (
    id VARCHAR(36) PRIMARY KEY,
    course_code VARCHAR(50) NOT NULL UNIQUE,
    module_code VARCHAR(50) NOT NULL UNIQUE,
    presentation_length VARCHAR(10) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_course_code (course_code),
    INDEX idx_module_code (module_code),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- VLE Materials Table
CREATE TABLE IF NOT EXISTS vle_materials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id VARCHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    material_type VARCHAR(20) NOT NULL,
    content_url VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    week_number INT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    INDEX idx_course_id (course_id),
    INDEX idx_status (status),
    INDEX idx_week (week_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Assessments Table
CREATE TABLE IF NOT EXISTS assessments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id VARCHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    assessment_type VARCHAR(20) NOT NULL,
    max_marks DOUBLE,
    duration_minutes INT,
    status VARCHAR(20) NOT NULL DEFAULT 'INACTIVE',
    week_number INT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    INDEX idx_course_id (course_id),
    INDEX idx_status (status),
    INDEX idx_week (week_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Student Assessments Table
CREATE TABLE IF NOT EXISTS student_assessments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assessment_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    marks_obtained DOUBLE,
    submission_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    submitted_at TIMESTAMP NULL,
    graded_at TIMESTAMP NULL,
    feedback VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (assessment_id) REFERENCES assessments(id) ON DELETE CASCADE,
    INDEX idx_assessment_id (assessment_id),
    INDEX idx_student_id (student_id),
    INDEX idx_status (submission_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==============================================================================
-- Sample Data
-- ==============================================================================

-- Insert Sample Courses
INSERT INTO courses (id, course_code, module_code, presentation_length, title, description, status) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'CS101', 'MOD001', '24W', 'Introduction to Computer Science', 'Fundamentals of programming and computer architecture', 'ACTIVE'),
('550e8400-e29b-41d4-a716-446655440002', 'CS102', 'MOD002', '24W', 'Data Structures and Algorithms', 'Advanced programming concepts and problem solving', 'ACTIVE'),
('550e8400-e29b-41d4-a716-446655440003', 'CS103', 'MOD003', '12W', 'Web Development Basics', 'HTML, CSS, and JavaScript fundamentals', 'ACTIVE'),
('550e8400-e29b-41d4-a716-446655440004', 'MATH101', 'MOD004', '24W', 'Calculus I', 'Single variable calculus and analysis', 'ACTIVE');

-- Insert Sample VLE Materials
INSERT INTO vle_materials (course_id, title, description, material_type, content_url, status, week_number) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Lecture 1: Introduction to CS', 'Basic concepts and history', 'LECTURE', 'https://example.com/cs101/lecture1', 'PUBLISHED', 1),
('550e8400-e29b-41d4-a716-446655440001', 'Tutorial: Your First Program', 'Writing and running your first program', 'TUTORIAL', 'https://example.com/cs101/tutorial1', 'PUBLISHED', 1),
('550e8400-e29b-41d4-a716-446655440001', 'Practice: Loops and Conditionals', 'Practice problems on basic programming', 'PRACTICE', 'https://example.com/cs101/practice1', 'PUBLISHED', 2),
('550e8400-e29b-41d4-a716-446655440002', 'Lecture: Arrays and Lists', 'Understanding linear data structures', 'LECTURE', 'https://example.com/cs102/lecture1', 'PUBLISHED', 1),
('550e8400-e29b-41d4-a716-446655440003', 'Video: HTML Basics', 'Introduction to HTML markup', 'VIDEO', 'https://example.com/cs103/video1', 'PUBLISHED', 1),
('550e8400-e29b-41d4-a716-446655440003', 'Resource: CSS Cheat Sheet', 'Quick reference for CSS properties', 'RESOURCE', 'https://example.com/cs103/css_cheatsheet', 'PUBLISHED', 2);

-- Insert Sample Assessments
INSERT INTO assessments (course_id, title, description, assessment_type, max_marks, duration_minutes, status, week_number) VALUES
(1, 'Quiz 1: Basic Concepts', 'Assessment on week 1 concepts', 'QUIZ', 10, 30, 'ACTIVE', 1),
(1, 'Assignment 1: First Program', 'Write a simple program with loops', 'ASSIGNMENT', 20, 120, 'ACTIVE', 2),
(1, 'Midterm Exam', 'Comprehensive exam on first half', 'EXAM', 50, 120, 'ACTIVE', 6),
(2, 'Quiz 1: Data Structures', 'Quick assessment on arrays and lists', 'QUIZ', 10, 30, 'ACTIVE', 1),
(3, 'Project 1: Simple Website', 'Build a personal website', 'PROJECT', 30, 180, 'ACTIVE', 3);

-- NOTE: Course IDs in assessments table need to use actual IDs
-- Update the assessments table with correct course_id references
UPDATE assessments SET course_id = '550e8400-e29b-41d4-a716-446655440001' WHERE assessment_type = 'QUIZ' AND title LIKE 'Quiz 1: Basic%';
UPDATE assessments SET course_id = '550e8400-e29b-41d4-a716-446655440001' WHERE title LIKE 'Assignment 1%';
UPDATE assessments SET course_id = '550e8400-e29b-41d4-a716-446655440001' WHERE title LIKE 'Midterm%';
UPDATE assessments SET course_id = '550e8400-e29b-41d4-a716-446655440002' WHERE title LIKE 'Quiz 1: Data%';
UPDATE assessments SET course_id = '550e8400-e29b-41d4-a716-446655440003' WHERE title LIKE 'Project 1%';

-- Insert Sample Student Assessments
INSERT INTO student_assessments (assessment_id, student_id, marks_obtained, submission_status, submitted_at, graded_at, feedback) VALUES
(1, 1001, 9, 'GRADED', '2025-01-10 14:30:00', '2025-01-10 16:00:00', 'Good attempt, watch timing'),
(1, 1002, 7, 'GRADED', '2025-01-10 14:45:00', '2025-01-10 16:00:00', 'Fair, review fundamentals'),
(1, 1003, 10, 'GRADED', '2025-01-10 14:15:00', '2025-01-10 16:00:00', 'Perfect!'),
(2, 1001, 18, 'GRADED', '2025-01-15 23:59:00', '2025-01-16 10:00:00', 'Well structured code'),
(2, 1002, 15, 'SUBMITTED', '2025-01-15 19:30:00', NULL, NULL),
(3, 1001, NULL, 'PENDING', NULL, NULL, NULL);

-- ==============================================================================
-- Verification Queries
-- ==============================================================================

-- Count records
SELECT 'Courses' as table_name, COUNT(*) as count FROM courses
UNION ALL
SELECT 'VLE Materials', COUNT(*) FROM vle_materials
UNION ALL
SELECT 'Assessments', COUNT(*) FROM assessments
UNION ALL
SELECT 'Student Assessments', COUNT(*) FROM student_assessments;

-- View Courses with related data
SELECT
    c.course_code,
    c.title,
    c.status,
    COUNT(DISTINCT vm.id) as material_count,
    COUNT(DISTINCT a.id) as assessment_count
FROM courses c
LEFT JOIN vle_materials vm ON c.id = vm.course_id
LEFT JOIN assessments a ON c.id = a.course_id
GROUP BY c.id, c.course_code, c.title, c.status;

