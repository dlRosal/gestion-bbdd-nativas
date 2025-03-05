# 📚 Sistema de Gestión de Biblioteca con BaseX

🚀 **Proyecto desarrollado para gestionar una biblioteca utilizando una base de datos nativa XML con BaseX y consultas XQuery/XPath.**

## 📖 Descripción

Este sistema permite **almacenar, consultar, modificar y eliminar libros** organizados en colecciones dentro de BaseX. Se implementa una arquitectura basada en **Java**, separando la lógica de negocio en `LibroService` y el control de la interfaz en `LibroControlador`.

## 🎯 Características

✅ **CRUD completo de libros:** Agregar, listar, modificar y eliminar libros.  
✅ **Gestión de colecciones:** Crear, eliminar y administrar categorías de libros.  
✅ **Conexión con BaseX desde Java:** Uso de `ClientSession` para ejecutar consultas XQuery.  
✅ **Consultas optimizadas:** Búsquedas avanzadas por título, autor y género.  
✅ **Base de datos nativa XML:** Estructura flexible y eficiente para almacenar documentos XML.  

---

## 🛠️ Tecnologías Utilizadas

🔹 **Lenguaje:** Java  
🔹 **Base de datos:** BaseX (XML)  
🔹 **Consultas:** XQuery, XPath  
🔹 **IDE recomendado:** IntelliJ IDEA / Eclipse  
🔹 **Gestión de dependencias:** Maven  

---

## ⚙️ Instalación y Configuración

### **📌 Requisitos previos**
- Java 8+ instalado en tu sistema.
- BaseX instalado y en ejecución.
- Un IDE como IntelliJ IDEA o Eclipse.

### **📌 Clonar el repositorio**
```sh
git clone https://github.com/tu-usuario/nombre-del-repositorio.git
cd nombre-del-repositorio
