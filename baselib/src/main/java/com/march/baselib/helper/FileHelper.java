package com.march.baselib.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.march.baselib.DevelopLib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Project  : CommonLib <p>
 * Package  : com.march.baselib <p>
 * CreateAt : 16/8/15 <p> <p>
 * Describe : 文件相关操作 <p>
 *
 * @author chendong <p>
 */
public class FileHelper {

    /**
     * 复制文件到
     * @param srcFile 原文件
     * @param destFile 目标文件
     * @return 复制成功
     */
    public static boolean copyTo(File srcFile, File destFile) {
        if (!srcFile.exists()) {
            return false;
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            destFile.getParentFile().mkdirs();
            destFile.createNewFile();
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            int size;
            byte[] temp = new byte[1024];
            while ((size = bis.read(temp, 0, temp.length)) != -1) {
                bos.write(temp, 0, size);
            }
            bos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CommonHelper.closeStream(bis, bos);
        }
        return false;
    }


    /**
     * 通知文件刷新，刷新后相册会出现这张照片，
     * @param picFile 图片文件
     */
    public static void scanFile(File picFile) {
        if (picFile != null) {
            try {
                MediaStore.Images.Media.insertImage(DevelopLib.getCtx().getContentResolver(), picFile.getAbsolutePath(), picFile.getName(), null);
                DevelopLib.getCtx().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(picFile)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}