package com.ds.opencv;

import static org.opencv.core.Core.NORM_MINMAX;
import static org.opencv.core.Core.normalize;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.MORPH_CLOSE;
import static org.opencv.imgproc.Imgproc.MORPH_RECT;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.getStructuringElement;
import static org.opencv.imgproc.Imgproc.morphologyEx;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
public class Remove {

    public static void remove(){
//        Mat src = imread("D:/opencv练习图片/去阴影.png");
////        imshow("原图", src);
//        //1.将图像转为灰度图
//        Mat gray = new Mat();
//        cvtColor(src, gray, COLOR_BGR2GRAY);
//        double w = 3;
//        //定义腐蚀和膨胀的结构化元素和迭代次数
//        Mat element = getStructuringElement(MORPH_RECT, Size(w, w));
//        int iteration = 9;
//        //2.将灰度图进行闭运算操作
//        Mat closeMat= new Mat();
//        morphologyEx(gray, closeMat, MORPH_CLOSE, element, Point(-1, -1), iteration);
////        imshow("闭运算", closeMat);
//        //4.闭运算后的图减去原灰度图再进行取反操作
//        Mat calcMat = ~(closeMat - gray);
////        imshow("calc", calcMat);
//        //5.使用规一化将原来背景白色的改了和原来灰度图差不多的灰色
//        Mat removeShadowMat;
//        normalize(calcMat, removeShadowMat, 0, 200, NORM_MINMAX);
//        imshow("dst", removeShadowMat);
    }
}
