-- Drop existing users table if it exists
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20) NOT NULL
);

INSERT INTO users (username, password, email, phone, address, role)
VALUES ('admin', 'admin123', 'admin@gym.com', '1234567890', '123 Gym Street', 'Admin');
