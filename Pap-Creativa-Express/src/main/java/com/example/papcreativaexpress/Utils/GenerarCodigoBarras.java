package com.example.papcreativaexpress.Utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerarCodigoBarras {

    public static Image generarCodigoDeBarras(String id) {
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(id, barcodeFormat, 283, 100, hints);

            // Convertir BitMatrix directamente a Image de JavaFX
            Image image = SwingFXUtils.toFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);

            // Guardar la imagen si es necesario
            guardarImagen(id, bitMatrix);

            return image;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void guardarImagen(String id, BitMatrix bitMatrix) {
        String outputPath = "Pap-Creativa-Express/src/main/resources/Imagenes_Productos";
        String filePath = outputPath + "/" + id + ".png";
        try {
            File outputFile = new File(filePath);
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", outputFile);
            System.out.println("Código de barras generado con éxito en '" + filePath + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

