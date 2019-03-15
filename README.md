# Tp7 Tienda

Es un proyecto realizado en Java para la clase de programación de Desarrollo de Aplicaciones Web.  
La finalidad del proyecto es realizar la compra de uno o varios productos por un cliente.
Intenta mostrar la realidad de una tienda normal, la compra de varios artículos por sus clientes.
Para el funcionamiento de una tienda es necesario conocer los artículos de los que disponemos, los datos de los clientes y la información de sus compras.
En un futuro nos gustaría, con más tiempo, ser capaces de actualizar el stock de la base de datos de la tienda y así acercarnos aún más a la realidad de una tienda.

## Comenzando 🚀

_Use el comando git_

```
$ git clone https://github.com/lbullon/Tp7.git
```

```
$ git clone https://github.com/CristianBY/Tp7.git
```
_Checkout con svn use la URL:_

```
https://github.com/lbullon/Tp7.git
```

```
https://github.com/CristianBY/encuestador.git
```

## Requisitos 📋

Tener instalado jdk1.8.0_191.
Un IDE Eclipse, Netbeans.
sqlite3.
[sqlite-jdbc-3.25.2.jar](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.25.2)

## Ejecutando las pruebas ⚙️

Estas indicaciones son para ejecutar en un terminal:
Dentro del directorio donde se encuentren los .java.

_Para crear la base de datos:_
```
CREATE TABLE cliente
(Nombre TEXT,
Dni TEXT,
Direccion TEXT,
CONSTRAINT PK_cliente PRIMARY KEY (Dni)
);
```

```
CREATE TABLE albaran
(Sku NUMBER,
Name TEXT,
Precio NUMBER,
Cantidad NUMBER,
CONSTRAINT PK_albaran PRIMARY KEY (Sku)
);
```

```
CREATE TABLE compra
(Factura NUMBER,
Sku NUMBER,
Dni TEXT,
Fecha TEXT,
Unidades NUMBER
CONSTRAINT PK_compra PRIMARY KEY (Factura,Sku,Dni),
CONSTRAINT FK_albaran_compra FOREIGN KEY (Sku)
REFERENCES albaran (Sku),
CONSTRAINT FK_cliente_compra FOREIGN KEY (Dni)
REFERENCES cliente (Dni),
);
```

_Win_
```
javac -cp <direccion del jar>;. AppTiendaBD.java
java -cp <direccion del jar>;. AppTiendaBD
```
_Linux_
```
$ javac -cp <direccion del jar>:. AppTiendaBD.java
$ java -cp <direccion del jar>:. AppTiendaBD
```
Una vez cerrada la tienda los datos serán almacenados en la DB tienda.db .


## Autores ✒️

* **Luis**-[lbullon](https://github.com/lbullon)

* **Cristian B.**-[CristianBY](https://github.com/CristianBY)

## Licencia 📄

* [LICENSE](LICENSE.md)

---
Por [CristianBY](https://github.com/CristianBY) ⌨
Por [lbullon](https://github.com/lbullon) ⌨



