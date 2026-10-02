-- Script de migración para crear la tabla de comentarios
-- Sistema de gestión de comentarios para productos

CREATE TABLE IF NOT EXISTS comments (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    product_id UUID NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Índice para búsquedas por producto
CREATE INDEX idx_comments_product_id ON comments(product_id);

-- Índice para búsquedas por usuario
CREATE INDEX idx_comments_user_id ON comments(user_id);

-- Índice para búsquedas por fecha de creación
CREATE INDEX idx_comments_created_at ON comments(created_at DESC);

-- Índice único para garantizar idempotencia (user_id + created_at)
CREATE UNIQUE INDEX idx_comments_user_created_unique ON comments(user_id, created_at);

-- Tabla de productos (referencia necesaria para la FK)
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de usuarios (referencia necesaria para la FK)
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Comentario en la tabla
COMMENT ON TABLE comments IS 'Almacena los comentarios de usuarios sobre productos';
COMMENT ON COLUMN comments.content IS 'Contenido del comentario';
COMMENT ON COLUMN comments.rating IS 'Puntuación del 1 al 5';
COMMENT ON COLUMN comments.product_id IS 'ID del producto comentado';
COMMENT ON COLUMN comments.user_id IS 'ID del usuario que realiza el comentario';