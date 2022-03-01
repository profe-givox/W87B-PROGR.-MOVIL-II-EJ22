package net.ivanvega.miubicacionymapas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validarPermisos();

        solicitarPermiso();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void validarPermisos() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
        &&
                ContextCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED
        ) {
            // You can use the API that requires the permission.
            solicitarPermiso();
        }else{
            ultimaUbicacionConcedida();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void solicitarPermiso() {
        ActivityResultLauncher<String[]> locationPermissionRequest =

                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(),

                        result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                                ultimaUbicacionConcedida();
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                                ultimaUbicacionConcedida();
                            } else {
                                // No location access granted.
                                Toast.makeText(this, "Sin permiso de localizaiocion concedido",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );

        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

    }

    private void ultimaUbicacionConcedida() {
    }
}