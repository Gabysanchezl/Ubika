 
# UBIKA 🎓

<p align="center">
  <img src="app/src/main/res/drawable/logoseatly.png" alt="Ubika Logo" width="125"/>
</p>

<p align="center">
  <strong>Tu asistente inteligente para organizar, asignar y visualizar los asientos de estudiantes en ceremonias de graduación universitarias.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue.svg" alt="Language">
  <img src="https://img.shields.io/badge/Architecture-MVVM-orange.svg" alt="Architecture">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-brightgreen.svg" alt="UI">
</p>


---

## 📘 Tabla de Contenidos

- [¿Qué es UBIKA?](#-qué-es-ubika)
- [¿Por qué usar UBIKA?](#-por-qué-usar-ubika)
- [Características Principales](#-características-principales)
- [Ventajas Técnicas](#-ventajas-técnicas)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
- [Cómo usar UBIKA](#-cómo-usar-ubika)
- [Tips y Mejoras Prácticas](#-tips-y-mejoras-prácticas)
- [Flujo de Datos](#-flujo-de-datos)
- [Estructura del Código](#-estructura-del-código)
- [Solución de Problemas](#-solución-de-problemas)
- [Conclusión](#-conclusión)
- [Futuras Mejora](#-futuras-mejoras)
- [Contribución](#-contribución)
- [Créditos](#-créditos)


---

## 🎓 ¿Qué es UBIKA?

**Ubika** es una aplicación móvil nativa para Android que sirve como asistente inteligente para la **planificación de asientos en ceremonias de graduación**.

Está pensada para:

- **Administradores universitarios** que necesitan organizar, asignar y visualizar los asientos de estudiantes de forma clara y eficiente.  
- **Estudiantes** que desean confirmar su asignación y recibir notificaciones importantes sobre el evento.

> En una frase: **UBIKA es tu tablero de control para organizar la graduación con precisión, elegancia y claridad.**

---

### ¿Qué resuelve exactamente?

- Reemplaza el caos de hojas de Excel, listas impresas y asignaciones manuales.  
- Te muestra en un solo lugar todos los asientos disponibles, asignados y filtrables por carrera.  
- Permite asignar estudiantes, editar perfiles, y gestionar el evento en tiempo real.  
- Ofrece una interfaz visual, editable y modular, ideal para revisión académica y enseñanza.

---

## 💡 ¿Por qué usar UBIKA?

### Para Administradores

1. **Organización visual y modular 🧠**  
   Cards, filtros y layouts que permiten ver el estado de cada asiento con claridad.

2. **Gestión en tiempo real 🔄**  
   Cambios reflejados instantáneamente en Firestore, sin necesidad de recargar.

3. **Edición de perfil 👤**  
   Actualiza nombre, universidad y foto del administrador desde la app.

4. **Filtros inteligentes 🔍**  
   Filtra estudiantes por carrera o muestra solo los que no tienen asignación.

5. **Notificaciones defensivas 🔔**  
   Configura recordatorios con permisos seguros para Android 13+.

### Para Estudiantes

1. **Confirmación de asignación ✅**  
   Verifica tu asiento y carrera asignada desde la app.

2. **Comunicación clara 📢**  
   Recibe notificaciones sobre cambios, horarios y ubicación.

3. **Experiencia visual elegante 🎨**  
   Interfaz moderna con Material Design 3 y colores accesibles.

---

## 📱 Características Principales

### 🔐 Autenticación y Gestión de Perfil

#### Métodos de Autenticación
- **Email y Contraseña**: Inicio de sesión seguro para administradores
- **Persistencia de sesión**: Mantén tu sesión activa hasta que decidas cerrar
- **Recuperación de contraseña**: Sistema seguro de recuperación en caso de olvido

#### Perfil del Administrador
- **Foto de perfil**:
  - Sube tu propia foto desde la galería
  - Actualización en tiempo real con Firebase Storage
  - Avatar por defecto si no tienes foto
- **Información personal**: Gestiona nombre y universidad
- **Modo edición**: Actualiza tu información directamente desde la pantalla de perfil
- **Confirmación visual**: Cambios reflejados en tiempo real en Firestore


### 🪑 Gestión de Asientos

#### Exploración Avanzada
- **Vista de asientos disponibles y asignados**: Cards claras y visuales
- **Búsqueda inteligente**: Filtra estudiantes por nombre o matrícula
- **Filtros dinámicos**:
  - Por carrera (Software, Educación, Administración, Cibersecurity)
  - Opción **“Todas”** para mostrar estudiantes sin carrera asignada

#### Interacción con Asientos
- 🎯 **Asignación rápida**: Selecciona estudiante y carrera en un solo paso
- 📝 **Actualización de asiento**: Cambia estudiante o carrera asignada fácilmente
- ❌ **Desasignar estudiante**: Libera un asiento manteniendo la carrera
- 🪣 **Vaciar asiento**: Elimina estudiante y carrera asignada
- 📊 **Confirmación visual**: Estado actualizado en tiempo real en Firestore


### 🔔 Notificaciones

- **Sistema defensivo de permisos**: Verificación con `canPostNotifications` para Android 13+  
- **Recordatorios automáticos**: Configuración con WorkManager  
- **Mensajes claros**: Avisos sobre cambios y eventos próximos  


### 🧭 Navegación Intuitiva

La aplicación cuenta con 4 secciones principales:

1. **🏠 Menú Principal**:  
   - Acceso rápido a gestión de asientos  
   - Acceso al perfil del administrador  
   - Botones claros y organizados  

2. **🪑 Gestión de Asientos**:  
   - Filtrado por carrera o estudiantes sin asignación  
   - Asignación, desasignación y vaciado de asientos  

3. **👤 Perfil del Administrador**:  
   - Información editable (nombre, universidad, foto)  
   - Configuración de cuenta  
   - Cierre de sesión seguro  

4. **🔔 Notificaciones**:  
   - Configuración de permisos  
   - Recordatorios automáticos  
   - Mensajes de confirmación  

---

## ⚙️ Ventajas Técnicas

- 🚀 **Rendimiento optimizado**: Carga rápida y navegación fluida.  
- 🔒 **Seguridad robusta**: Autenticación con Firebase y control de sesión.  
- 📱 **Diseño moderno**: UI intuitiva, accesible y didáctica.  
- 🔄 **Sincronización en tiempo real**: Cambios reflejados al instante.  
- 🧱 **Arquitectura limpia**: MVVM + Clean Architecture con separación clara de capas.  
- 🧠 **Código didáctico**: Ideal para revisión académica y enseñanza de buenas prácticas.

  ### 📁 Estructura del Código
```
| Carpeta           | Rol                                                          |
|-------------------|--------------------------------------------------------------|
| `viewmodels/`     | Controlan el estado de cada pantalla.                        |
| `data/firebase/`  | Servicios para Auth, Firestore y Storage.                    |
| `data/repository/`| `UbikaRepository` centraliza el acceso a datos.              |
| `domain/model/`   | Modelos de negocio (`Student`, `Seating`, etc).              |
| `ui/`             | Pantallas y componentes visuales.                            |
| `utils/`          | Funciones auxiliares (`canPostNotifications`, etc).          |
| `theme/`          | Colores, tipografías y estilos globales.                     |
```
---

## 🛠️ Tecnologías Utilizadas

- **Plataforma**: Android  
- **Lenguaje**: Kotlin  
- **Arquitectura**: MVVM + Clean Architecture  
- **UI**: Jetpack Compose + Material3  
- **Backend**: Firebase (Auth, Firestore, Storage)  
- **Notificaciones**: WorkManager + permisos defensivos  
- **Otros**: Coroutines, State Management, Toasts, Dialogs  

---

## 📦 Instalación y Ejecución

```bash
git clone https://github.com/tuusuario/ubika-app.git
cd ubika-app
./gradlew installDebug
 ```
---
## 🏗️ Arquitectura del Proyecto

UBIKA sigue una **Arquitectura Limpia (Clean Architecture)** con el patrón **MVVM (Model-View-ViewModel)** para asegurar:

- ✅ **Separación de responsabilidades**: Cada capa tiene una función específica  
- ✅ **Testabilidad**: Código fácil de probar de manera unitaria  
- ✅ **Escalabilidad**: Estructura modular que facilita el crecimiento  
- ✅ **Mantenibilidad**: Código limpio y organizado  
```plaintext
ViewModel ──> Repository ──> Firebase Services ──> Firestore/Auth/Storage
     ↑
     │
Composable UI
```
  
### 🧩 Estructura de Capas

```
app/
├── data/								 # Capa de Datos
│ ├── firebase/ 				# Servicios Firebase (Auth, Firestore, Storage)
│ └── repository/ 			# UbikaRepository centraliza el acceso a datos
│
├── domain/ 						# Capa de Dominio
│ ├── model/ 						# Modelos de negocio (Student, Seating, etc.)
│ └── use_case/ 				# Casos de uso (AsignarAsiento, FiltrarEstudiantes, etc.)
│
├── ui/ 								# Capa de Presentación
│ ├── components/ 			# Composables reutilizables (Cards, Popups, Dropdowns)
│ ├── screens/ 					# Pantallas principales (Login, Perfil, Asientos, Menú)
│ └── navigation/ 			# Gestión de navegación
│
├── theme/ 							# Tema visual (colores, tipografías, estilos)
│
├── utils/ 							# Funciones auxiliares (canPostNotifications, etc.)
│
└── viewmodels/					 # Lógica de presentación (AdminViewModel, SeatingViewModel, etc.)
```
---

## 🚀 Cómo usar UBIKA

### Para Administradores
---
#### 📲 Descarga e Instalación

1. **Descarga la aplicación**
   - Desde Google Play Store (próximamente)
   - O instala el APK desde [Releases]([https](https://github.com/Gabysanchezl/Ubika/releases)

2. **Primer inicio**
   - Abre UBIKA
   - Verás la pantalla de login para administradores
---
#### 🔑 Login

**Opción 1: Login con Email**
1. Ingresa tu correo institucional y contraseña
2. Toca "Iniciar Sesión"
3. Si es tu primera vez, el administrador debe estar registrado en Firebase Authentication
4. De ser asi podras colocar el correo y la contraseña sin dificultar alguna 

**Persistencia de sesión**
- Una vez logueado, tu sesión se mantiene activa hasta que decidas cerrar sesión manualmente

---

### 🪑 Gestión de Asientos

**Desde Menú Principal:**
1. Abre la app y estarás en la pantalla principal
2. Selecciona “Gestión de Asientos”
3. Filtra estudiantes:
   - Por carrera (Software, Educación, Administración, Cibersecurity)
   - Opción **“Todas”** para mostrar estudiantes sin carrera asignada
4. Selecciona un asiento y asigna estudiante

---
###**Acciones disponibles:**
- 🎯 **Asignar estudiante**: Selecciona estudiante y carrera en un solo paso  
- 📝 **Actualizar asiento**: Cambia estudiante o carrera asignada  
- ❌ **Desasignar estudiante**: Libera un asiento manteniendo la carrera  
- 🪣 **Vaciar asiento**: Elimina estudiante y carrera asignada  

---

### 👤 Perfil del Administrador

**Ver información:**
1. Desde el menú principal, abre “Perfil”
2. Verás tu foto, nombre y universidad

**Editar perfil:**
1. Toca el ícono de editar (lápiz)
2. Modifica la información que desees:
   - Nombre
   - Universidad
   - Foto de perfil
3. Toca "Guardar Cambios"

**Cerrar sesión:**
1. Ve a tu perfil
2. Desplázate hasta abajo
3. Toca "Cerrar Sesión"
4. Confirma la acción

---

### 🔔 Notificaciones

- **Sistema defensivo de permisos**: Verificación con `canPostNotifications` para Android 13+  
- **Recordatorios automáticos**: Configuración con WorkManager  
- **Mensajes claros**: Avisos sobre cambios y eventos próximos  

---

## 💡 Tips y Mejores Prácticas

✅ **Mantén tu perfil actualizado** para reflejar cambios en tiempo real  
✅ **Usa el filtro “Todas”** para ver estudiantes sin carrera asignada  
✅ **Vacía asientos** antes de reasignar para evitar inconsistencias  
✅ **Verifica permisos de notificaciones** en Android 13+  
✅ **Confirma cambios en Firestore** revisando la consola de Firebase  

---

### Para Desarrolladores

#### 🛠️ Configuración del Entorno de Desarrollo

**Prerrequisitos:**
- Android Studio Hedgehog (2023.1.1) o superior  
- JDK 17 o superior  
- SDK Android 24+ (Android 7.0 Nougat)  
- Cuenta de Firebase  
- Git instalado  

**Pasos de instalación:**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/ubika.git
   cd ubika
   ```
   
2. **Configurar Firebase**

- Crear proyecto en Firebase Console
- Agregar aplicación Android con package name: com.gaby.ubika
- Descargar google-services.json y colocarlo en app/google-services.json
- Habilitar servicios:
	- Authentication: Email/Password
	- Firestore Database: Crear base de datos
	- Storage: Activar para fotos de perfil

3. **Configurar reglas de Firestore (seguras para administradores autenticados)**
 ```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      // Solo usuarios autenticados pueden leer y escribir
      allow read, write: if request.auth != null;
    }
  }
}
```

4. **Sincronizar y compilar**
```bash
   # Desde terminal en el directorio del proyecto
   ./gradlew clean build
   ```
   O desde Android Studio: Build > Rebuild Project

5. **Ejecutar la aplicación**
   - Conecta un dispositivo físico o inicia un emulador
   - Clic en Run (▶️) en Android Studio
   - O desde terminal: `./gradlew installDebug`

-- 

## 🔄 Flujo de Datos en UBIKA

```plaintext
          👩‍💼 Administrador
                  │
                  ▼
          🎛️ ViewModel (MVVM)
                  │
                  ▼
        📦 Repository (UbikaRepository)
                  │
                  ▼
   🔥 Firebase Services (Auth / Firestore / Storage)
                  │
                  ▼
          🎨 UI (Jetpack Compose)
```

**Explicación del Flujo**
1. Administrador: Interactúa con la aplicación (login, asignación de asientos, edición de perfil).
2. ViewModel: Gestiona el estado de la pantalla y recibe las acciones del administrador.
3. Repository: Centraliza el acceso a datos y decide qué servicio de Firebase usar.
4. Firebase Services:
	- AuthService: Autenticación y control de sesión.
	- FirestoreService: Lectura/escritura de datos (estudiantes, asientos, perfil).
	- StorageService: Manejo de imágenes (foto de perfil).
5. UI (Jetpack Compose): Refleja los cambios en tiempo real y muestra la información de forma clara y didáctica.

---

## 📚 Estructura del Código

**Navegación por el proyecto:**

```
app/src/main/java/com/gaby/ubika/
│
├── MainActivity.kt                  # Activity principal
│
├── data/                            # Capa de acceso a datos
│   ├── firebase/                    # Servicios Firebase
│   │   ├── AuthService.kt           # Autenticación
│   │   ├── FirestoreService.kt      # Lectura/escritura en Firestore
│   │   └── StorageService.kt        # Subida de imágenes
│   └── repository/                  # Repositorio central
│       └── UbikaRepository.kt       # Orquestador de servicios
│
├── domain/                          # Lógica de negocio
│   ├── model/                       # Modelos de dominio
│   │   ├── Student.kt
│   │   ├── Seating.kt
│   │   ├── SeatingStatus.kt
│   │   └── BottomNavItem.kt
│   └── use_case/                    # Casos de uso
│       ├── AsignarAsientoUseCase.kt
│       ├── CheckUserSessionUseCase.kt
│       └── FiltrarEstudiantesUseCase.kt
│
├── ui/                              # Capa de presentación
│   ├── components/                  # Composables reutilizables
│   │   ├── AdminHeader.kt
│   │   ├── CardCarreersSeating.kt
│   │   ├── CareersColorLegend.kt
│   │   ├── ColorbyCarreer.kt
│   │   ├── Confirm_ClosingSesion_Dialog.kt
│   │   ├── ConfirmDeleteDialog.kt
│   │   ├── DropdownMenuCarrera.kt
│   │   ├── GraduationDateCard.kt
│   │   ├── InfoCardSeating.kt
│   │   ├── OcupationBar_Carriers.kt
│   │   ├── PasswordChangeDialog.kt
│   │   ├── PopupGestionSeating.kt
│   │   ├── StudentItem.kt
│   │   └── StudentPopUp.kt
│   ├── screens/                     # Pantallas principales
│   │   ├── AdminHomeScreen.kt
│   │   ├── AdminLoginScreen.kt
│   │   ├── AdminProfileScreen.kt
│   │   ├── BottomNavigationBar.kt
│   │   ├── ConsultingResult_Screen.kt
│   │   ├── ConsultingSeat_Screen.kt
│   │   ├── FormStudentEdit.kt
│   │   ├── MainMenuScreen.kt
│   │   ├── SeatingMapScreen.kt
│   │   ├── SplashScreen.kt
│   │   └── StudentListScreen.kt
│   └── navigation/                  # Sistema de navegación
│       └── Navigation.kt
│
├── theme/                           # Tema visual
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── utils/                           # Utilitarios
│   └── canPostNotifications.kt      # Verificación de permisos
│
└── viewmodels/                      # Lógica de presentación
    ├── AdminLoginViewModel.kt
    ├── AdminViewModel.kt
    ├── NotificationViewModel.kt
    ├── SeatingViewModel.kt
    ├── SplashViewModel.kt
    └── StudentsViewModel.kt
```
## 🛠️ Solución de Problemas

#### ❌ No puedo iniciar sesión

**Problema:** "Error al iniciar sesión"  
**Soluciones:**
1. Verifica tu conexión a internet  
2. Confirma que tu email y contraseña son correctos  
3. Asegúrate de estar registrado como administrador en Firebase Authentication  
4. Cierra y vuelve a abrir la app  

---

#### 🪑 No se muestran los asientos

**Problema:** Pantalla en blanco o sin datos  
**Soluciones:**
1. Verifica tu conexión a internet  
2. Asegúrate de que los datos estén correctamente cargados en Firestore  
3. Cierra y vuelve a abrir la app  
4. Revisa la consola de Firebase para confirmar que los documentos existen  

---

#### 🖼️ No puedo subir foto de perfil

**Problema:** Error al subir imagen  
**Soluciones:**
1. Verifica permisos de almacenamiento en ajustes del dispositivo  
2. Asegúrate de que la imagen no sea muy grande (máx 10MB)  
3. Intenta con otra imagen  
4. Reinicia la app  

---

#### 🔔 No se reciben notificaciones

**Problema:** No llegan recordatorios o avisos  
**Soluciones:**
1. Verifica que el permiso de notificaciones esté concedido (Android 13+)  
2. Asegúrate de que WorkManager esté configurado correctamente  
3. Revisa que el administrador esté autenticado  
4. Reinicia la app y vuelve a probar  

---


## 📌 Conclusión

UBIKA es más que una aplicación: es un ejemplo práctico de cómo aplicar **Clean Architecture** y **MVVM** en Android con Kotlin y Jetpack Compose.  
Su propósito es facilitar la planificación de asientos en ceremonias de graduación, pero también demostrar cómo construir proyectos **modulares, mantenibles y didácticos**.

Este proyecto refleja:
- La importancia de separar responsabilidades en capas claras
- El valor de la sincronización en tiempo real con Firebase
- El enfoque en accesibilidad y diseño moderno con Material3
- La robustez de un código defensivo y preparado para crecer

---

## 🌱 Futuras Mejoras

- Exportación de reportes de asientos en PDF/Excel  
- Integración con calendarios para recordatorios de eventos  
- Animaciones y transiciones visuales más atractivas  
- Panel de métricas para administradores (ocupación, carreras, estadísticas)  
- Funcionalidad offline para consulta sin conexión  

---

## 🤝 Contribución

Este proyecto fue creado con fines académicos y didácticos, pero está abierto a mejoras.  
Si deseas contribuir:
1. Haz un fork del repositorio
2. Crea una rama con tu mejora (`git checkout -b feature/nueva-funcionalidad`)
3. Haz commit de tus cambios (`git commit -m 'Agregada nueva funcionalidad'`)
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

## 👩‍💻 Créditos

Desarrollado por **Laura Gabriela Sanchez**, futura Ingeniera de Software.  
Este proyecto fue parte de su formación académica y busca servir como herramienta práctica y ejemplo de buenas prácticas en desarrollo móvil.

---
