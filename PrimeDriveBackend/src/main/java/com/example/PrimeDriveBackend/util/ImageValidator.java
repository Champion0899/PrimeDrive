package com.example.PrimeDriveBackend.util;

public class ImageValidator {
    /**
     * Validates the image filename to allow only .jpg, .jpeg, and .png extensions.
     *
     * @param filename the filename of the image
     * @throws IllegalArgumentException if the file format is not supported
     */
    public static void validate(String filename) {
        if (filename == null || filename.isEmpty())
            return;
        String lower = filename.toLowerCase();
        if (!(lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png"))) {
            throw new IllegalArgumentException(
                    "Unsupported file format. Only .jpg, .jpeg, and .png are allowed.");
        }
    }
}
