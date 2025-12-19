-- ============================================
-- Gym Management System Database
-- ============================================

-- Drop existing tables if they exist (for a fresh setup)
DROP TABLE IF EXISTS memberships CASCADE;
DROP TABLE IF EXISTS workout_classes CASCADE;
DROP TABLE IF EXISTS gym_merch CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ============================================
-- Users table
-- ============================================
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Memberships table
-- ============================================
CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    membership_description TEXT,
    membership_cost NUMERIC(8,2) NOT NULL,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Workout Classes table
-- ============================================
CREATE TABLE workout_classes (
    class_id SERIAL PRIMARY KEY,
    class_type VARCHAR(50) NOT NULL,
    class_description TEXT,
    trainer_id INT REFERENCES users(user_id) ON DELETE SET NULL,
    schedule_time TIMESTAMP NOT NULL,
    capacity INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Gym Merchandise table
-- ============================================
CREATE TABLE gym_merch (
    merch_id SERIAL PRIMARY KEY,
    merch_name VARCHAR(100) NOT NULL,
    merch_type VARCHAR(50),
    merch_price NUMERIC(8,2) NOT NULL,
    quantity_in_stock INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Sample Data
-- ============================================

-- Sample users
INSERT INTO users (username, password_hash, email, phone, address, role)
VALUES 
('admin1', 'hashedpassword1', 'admin1@gym.com', '1234567890', '123 Gym St', 'Admin'),
('trainer1', 'hashedpassword2', 'trainer1@gym.com', '2345678901', '456 Fitness Ave', 'Trainer'),
('member1', 'hashedpassword3', 'member1@gym.com', '3456789012', '789 Workout Rd', 'Member');

-- Sample workout classes
INSERT INTO workout_classes (class_type, class_description, trainer_id, schedule_time, capacity)
VALUES
('Yoga', 'Morning yoga session', 2, '2025-12-20 08:00:00', 20),
('Spin', 'High intensity cycling', 2, '2025-12-20 09:00:00', 15);

-- Sample gym merchandise
INSERT INTO gym_merch (merch_name, merch_type, merch_price, quantity_in_stock)
VALUES
('T-shirt', 'Clothing', 19.99, 50),
('Water Bottle', 'Accessory', 9.99, 100);

-- ============================================
-- Indexes for faster queries
-- ============================================
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_memberships_user_id ON memberships(user_id);
CREATE INDEX idx_workout_classes_trainer_id ON workout_classes(trainer_id);
