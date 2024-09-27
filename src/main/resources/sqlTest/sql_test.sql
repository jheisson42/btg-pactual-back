SELECT c.nombre AS nombre_cliente
FROM clientes c
JOIN inscripciones i ON c.id_cliente = i.id_cliente
JOIN productos p ON i.id_producto = p.id_producto
JOIN visitas v ON c.id_cliente = v.id_cliente
WHERE p.id_sucursal = v.id_sucursal
GROUP BY c.nombre
HAVING COUNT(DISTINCT p.id_sucursal) = COUNT(DISTINCT v.id_sucursal);
