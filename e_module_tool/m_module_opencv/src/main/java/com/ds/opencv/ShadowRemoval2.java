package com.ds.opencv;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ShadowRemoval2 {

    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    public Mat removeShadows(Mat inputImage) {
        // Convert image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(inputImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Apply Gaussian blur to reduce noise
        Mat blurredImage = new Mat();
        Imgproc.GaussianBlur(grayImage, blurredImage, new Size(5, 5), 0);

        // Threshold the image to obtain binary mask
        Mat mask = new Mat();
        Imgproc.threshold(blurredImage, mask, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        // Fill holes in the mask
        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_CLOSE, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)));

        // Inpaint using the mask
        Mat resultImage = new Mat();
//        Imgproc.inpaint(inputImage, mask, resultImage, 5, Imgproc.INPAINT_TELEA);

        return resultImage;
    }
}
