package com.example.papcreativaexpress.Utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenerarCodigoBarras {

    public static void generarCodigoDeBarras(String id) {
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        String outputPath = "Pap-Creativa-Express/src/main/resources/Imagenes_Productos";

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(id, barcodeFormat, 283, 100, hints);

            String filePath = outputPath + File.separator + id + ".png";

            File outputFile = new File(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "png", outputFile.toPath());
            System.out.println("Código de barras generado con éxito en '" + filePath + "'.");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
