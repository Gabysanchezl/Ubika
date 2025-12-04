package com.gaby.ubika.ui.screens

 import android.net.Uri
import android.widget.Toast
import coil.compose.AsyncImage
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
  import androidx.compose.foundation.background
 import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
 import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
 import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gaby.ubika.R
import com.gaby.ubika.ui.components.PasswordChangeDialog
import com.gaby.ubika.ui.components.CardCarreersSeating
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Lock
 import androidx.compose.ui.res.painterResource
import com.gaby.ubika.ui.components.InfoCardSeating
import com.gaby.ubika.ui.components.ColorbyCarreer
 import com.gaby.ubika.viewmodels.AdminViewModel
import com.gaby.ubika.viewmodels.SeatingViewModel
import com.gaby.ubika.viewmodels.StudentsViewModel
import com.google.firebase.auth.FirebaseAuth



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProfileScreen(navController: NavController) {

    val admivm: AdminViewModel = viewModel()
    val studentsvm: StudentsViewModel = viewModel()
    val seatingvm: SeatingViewModel = viewModel()
    val context = LocalContext.current
    var tempUri by remember { mutableStateOf<Uri?>(null) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    val totalDisponibles = seatingvm.listadoSeatings.count { it.idEstudiante == null }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            tempUri = it
            showConfirmDialog = true
        }
    }
    // Diálogo de confirmación
    if (showConfirmDialog && tempUri != null) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Confirmar imagen") },
            text = {
                AsyncImage(
                    model = tempUri,
                    contentDescription = "Preview",
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    admivm.subirFotoPerfil(tempUri!!) {
                        Toast.makeText(context, "Foto actualizada", Toast.LENGTH_SHORT).show()
                    }
                    showConfirmDialog = false
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    var editNombre by remember { mutableStateOf(false) }
    var editUni by remember { mutableStateOf(false) }

    // Buffers de edición que se inicializan con el valor actual del VM
    var nombreBuffer by remember(admivm.nombreAdmin) { mutableStateOf(admivm.nombreAdmin) }
    var universidadBuffer by remember(admivm.nombreUniversidad) { mutableStateOf(admivm.nombreUniversidad) }

    val asientos = seatingvm.listadoSeatings
    val asignados = asientos.filter { it.idEstudiante != null }
    val asignadosPorCarrera: Map<String, Int> = asignados
        .filter { !it.carrera.isNullOrBlank() }
        .groupingBy { it.carrera!! }
        .eachCount()
    val totalAsignadosFiltrados = asignadosPorCarrera.values.sum()
    var mostrarDialogoCerrarSesion by remember { mutableStateOf(false) }

    val carreras: List<String> = asignadosPorCarrera.keys.filterNot { it.isBlank() }
    var showDialog by remember { mutableStateOf(false) }


    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
             item {
                 Box(
                     modifier = Modifier
                         .fillMaxWidth()
                         .background(Color(0xFF795548))
                         .padding(vertical = 24.dp, horizontal = 20.dp)
                 ) {
                     Row(
                         modifier = Modifier
                             .fillMaxWidth(),
                          verticalAlignment = Alignment.CenterVertically
                     ) {
                        // Foto de perfil
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White.copy(alpha = 0.8f), CircleShape)
                                .clickable { launcher.launch("image/*") },
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                admivm.isUploading -> CircularProgressIndicator(
                                    modifier = Modifier.size(
                                        48.dp
                                    )
                                )

                                admivm.fotoPerfilUrl != null -> AsyncImage(
                                    model = admivm.fotoPerfilUrl,
                                    contentDescription = "Foto de perfil",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                else -> Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Agregar foto",
                                    tint = Color.White.copy(alpha = 0.8f),
                                    modifier = Modifier.size(64.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f).padding(start = 20.dp, end = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Administrador",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (!editNombre) {
                                    Text(
                                        text = admivm.nombreAdmin,
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = {
                                        nombreBuffer = admivm.nombreAdmin
                                        editNombre = true
                                    }) {
                                        Icon(
                                            Icons.Default.Edit,
                                            contentDescription = "Editar nombre",
                                            tint = Color.White.copy(alpha = 0.5f)
                                        )
                                    }
                                } else {
                                    OutlinedTextField(
                                        value = nombreBuffer,
                                        onValueChange = { nombreBuffer = it },
                                        singleLine = true,
                                        label = { Text("Nombre") },
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = {
                                        admivm.actualizarConfiguracionAdmin(
                                            nombreBuffer,
                                            admivm.nombreUniversidad,
                                            admivm.fotoPerfilUrl
                                        )
                                        editNombre = false
                                        Toast.makeText(context, "Nombre actualizado", Toast.LENGTH_SHORT).show()
                                    }) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = "Guardar",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = admivm.nombreUniversidad,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(onClick = { editUni = true }) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Editar universidad",
                                        tint = Color.White.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }


                            if (editUni) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    OutlinedTextField(
                                        value = admivm.nombreUniversidad,
                                        onValueChange = { admivm.nombreUniversidad = it },
                                        singleLine = true,
                                        label = { Text("Universidad") },
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = {
                                        admivm.actualizarConfiguracionAdmin(
                                            admivm.nombreAdmin,
                                            universidadBuffer,
                                            admivm.fotoPerfilUrl
                                        )
                                        editUni = false
                                        Toast.makeText(
                                            context,
                                            "Universidad actualizada",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = "Guardar",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }


                    //  Asientos disponibles | Asientos asignados
                    item {
                        InfoCardSeating(
                            tituloSuperior = "Asientos",
                            tituloPrincipal = "Disponibles",
                            valor = totalDisponibles,
                            colorTexto = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }

                    item {
                        InfoCardSeating(
                            tituloSuperior = "Asientos",
                            tituloPrincipal = "Asignados",
                            valor = totalAsignadosFiltrados,
                            colorTexto = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }


                    // Sección: Asientos asignados por carrera
                    item {
                        Text(
                            text = "Resumen de Asignación",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)

                        )
                    }

                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(carreras) { carrera ->
                                val asignadosCarrera = asignadosPorCarrera[carrera] ?: 0
                                val listadoStudents = studentsvm.listadoStudents
                                val totalEstudiantesCarrera = listadoStudents
                                    .filter { it.carrera.isNotBlank() }
                                    .count { it.carrera == carrera }
                                val faltan =
                                    (totalEstudiantesCarrera - asignadosCarrera).coerceAtLeast(0)

                                CardCarreersSeating(
                                    carrera = carrera,
                                    asignados = asignadosCarrera,
                                    faltanPorAsignar = faltan,
                                    colorCarrera = ColorbyCarreer(carrera)
                                )
                            }
                        }
                    }


                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp) // 🔹 aire lateral
                        ) {
                            ChangePasswordCard(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { showDialog = true }
                            )

                            PasswordChangeDialog(
                                showDialog = showDialog,
                                onDismiss = { showDialog = false }
                            )
                        }
                    }

                    // Cerrar sesión
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clickable { mostrarDialogoCerrarSesion = true },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "Cerrar sesión",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = stringResource(R.string.cerrar_sesion),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = MaterialTheme.colorScheme.error,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        if (mostrarDialogoCerrarSesion) {
                            AlertDialog(
                                onDismissRequest = { mostrarDialogoCerrarSesion = false },
                                title = {
                                    Text(
                                        text = "Cerrar Sesión",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                                    )
                                },
                                text = {
                                    Text(
                                        text = "¿Estás seguro de que deseas cerrar sesión?",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                confirmButton = {
                                    TextButton(onClick = {
                                        FirebaseAuth.getInstance().signOut()
                                        navController.navigate("admin_login") {
                                            popUpTo("admin_home") { inclusive = true }
                                        }
                                        mostrarDialogoCerrarSesion = false
                                    }) {
                                        Text("Sí, cerrar sesión")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { mostrarDialogoCerrarSesion = false }) {
                                        Text("Cancelar")
                                    }
                                },
                                shape = RoundedCornerShape(16.dp),
                                tonalElevation = 6.dp,
                                properties = DialogProperties(dismissOnClickOutside = true)
                            )
                        }
                    }
                }
            }
        }


@Composable
fun ChangePasswordCard(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF795548)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Cambiar contraseña",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Cambiar contraseña",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}




