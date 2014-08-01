package com.stevescodingblog.androidapps.flashlight;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    static Camera _camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();

        // Check that we have a camera flash
        boolean hasFlash = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        Button toggleButton = (Button)this.findViewById(R.id.toggleButton);

        // If we don't, show a message and hide the button
        if(!hasFlash){
            Toast.makeText(context, "Your device does not have a camera flash!", Toast.LENGTH_LONG).show();
            toggleButton.setVisibility(View.GONE);
        }

        toggleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {

                    if(_camera == null) {

                        _camera = Camera.open();
                        _camera.setPreviewTexture(new SurfaceTexture(0));

                        Camera.Parameters parameters = _camera.getParameters();

                        // If we support AutoFlash, then turn it on
                        List<String> flashModes = parameters.getSupportedFlashModes();

                        if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        }

                        _camera.setParameters(parameters);
                        _camera.startPreview();
                    }
                    else {
                        _camera.stopPreview();
                        _camera.release();
                        _camera = null;
                    }
                }
                catch(Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
     }
}
