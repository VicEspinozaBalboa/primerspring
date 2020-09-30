INSERT INTO categories (id, name) VALUES(1, 'Category 1');
INSERT INTO categories (id, name) VALUES(2, 'Category 2');
INSERT INTO categories (id, name) VALUES(3, 'Category 3');

INSERT INTO products (id, name, description, stock, price, status, create_at, category_id)
VALUES(1, 'Product Vic', 'Description of product 1', 20, 12.50, 'Created', '2020-08-07', 1);
INSERT INTO products (id, name, description, stock, price, status, create_at, category_id)
VALUES(2, 'Product Mar', 'Description of product 2', 15, 10.50, 'Created', '2020-08-07', 1);
INSERT INTO products (id, name, description, stock, price, status, create_at, category_id)
VALUES(3, 'Product 3', 'Description of product 3', 5, 15.50, 'Created', '2020-08-07', 2);