package com.ds.opencv

import android.graphics.Bitmap
import com.blankj.utilcode.util.ImageUtils
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY
import org.opencv.imgproc.Imgproc.MORPH_CLOSE
import org.opencv.imgproc.Imgproc.MORPH_RECT

object OpenCV {

    fun init() {
        if (OpenCVLoader.initLocal()) {
        } else {
            return
        }
    }

//    # 读取图像
//    image = cv2.imread('input_image.jpg')
//
//    # 转换为灰度图像
//    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
//
//    # 使用自适应阈值处理来获取二值图像
//    _, binary = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)
//
//    # 对二值图像进行膨胀操作，增强文字的边缘
//    kernel = np.ones((3, 3), np.uint8)
//    dilated = cv2.dilate(binary, kernel, iterations=1)
//
//    # 将膨胀后的图像与原始图像叠加
//    result = cv2.bitwise_and(image, image, mask=dilated)
//
//    # 显示结果
//    cv2.imshow('Result', result)
//    cv2.waitKey(0)
//    cv2.destroyAllWindows()

    fun brightening(inputImagePath: String): Bitmap {
        val bitmap = ImageUtils.getBitmap(inputImagePath)
        val originalMat = Mat()
        Utils.bitmapToMat(bitmap, originalMat)

        // 进一步增加亮度
        val brightness = 20 // 增加的亮度值，您可以根据需要调整
        val increasedBrightnessMat = Mat()
        Core.add(originalMat, Scalar.all(brightness.toDouble()), increasedBrightnessMat)

        // 将增加亮度后的图像规范化到指定范围
        val normalizedMat = Mat()
        Core.normalize(increasedBrightnessMat, normalizedMat, 0.0, 255.0, Core.NORM_MINMAX)

        // 将结果转换为 Bitmap
        val resultBitmap = Bitmap.createBitmap(normalizedMat.cols(), normalizedMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(normalizedMat, resultBitmap)
        return resultBitmap
    }

    fun removeShadow(inputImagePath: String): Bitmap {
        val bitmap = ImageUtils.getBitmap(inputImagePath)
        val originalMat = Mat()
        Utils.bitmapToMat(bitmap, originalMat)

        // 将图像转换为灰度图像
        val grayMat = Mat()
        Imgproc.cvtColor(originalMat, grayMat, COLOR_BGR2GRAY)

        // 定义腐蚀和膨胀的结构化元素和迭代次数
        val element = Imgproc.getStructuringElement(MORPH_RECT, Size(3.0, 3.0))
        val iteration = 9
        // 将灰度图进行闭运算操作
        val closeMat = Mat()
        Imgproc.morphologyEx(grayMat, closeMat, MORPH_CLOSE, element, Point(-1.0, -1.0), iteration)

        // 闭运算后的图减去原灰度图
        val result = Mat()
        Core.subtract(closeMat, grayMat, result)

        // 再进行取反操作
        val result2 = Mat()
        Core.bitwise_not(result, result2)

        // 进一步增加亮度
        val brightness = 20 // 增加的亮度值，您可以根据需要调整
        val increasedBrightnessMat = Mat()
        Core.add(result2, Scalar.all(brightness.toDouble()), increasedBrightnessMat)

        // 将增加亮度后的图像规范化到指定范围
        val normalizedMat = Mat()
        Core.normalize(increasedBrightnessMat, normalizedMat, 0.0, 255.0, Core.NORM_MINMAX)

        // 将结果转换为 Bitmap
        val resultBitmap = Bitmap.createBitmap(normalizedMat.cols(), normalizedMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(normalizedMat, resultBitmap)

        return resultBitmap
    }

//    fun bold(img2: Bitmap): Bitmap {
//
//        Utils.bitmapToMat(bitmap, originalMat)
//// 定义膨胀操作的结构元素和迭代次数
//        val kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(5.0, 5.0))
//        val iterations = 1
//    }
}