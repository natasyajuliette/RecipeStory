package com.example.natasyajuliette.recipestory.Utils;

import com.example.natasyajuliette.recipestory.Manifest;

/**
 * Created by natasyajuliette on 08/04/18.
 */

public class Permissions {

    public static final String[] PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    public static final String[] CAMERA_PERMISSIONS = {
            android.Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_STORAGE_PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
}
