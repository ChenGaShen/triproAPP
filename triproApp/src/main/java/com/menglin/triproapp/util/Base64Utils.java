package com.menglin.triproapp.util;


import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;
import java.util.Random;

import com.itextpdf.xmp.impl.Base64;
public class Base64Utils {
	/** *//** 
     * 文件读取缓冲区大小 
     */  
    private static final int CACHE_SIZE = 1024;  
      
    /** *//** 
     * <p> 
     * BASE64字符串解码为二进制数据 
     * </p> 
     *  
     * @param base64 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decode(String base64) throws Exception {  
        return Base64.decode(base64.getBytes());  
    }  
      
    /** *//** 
     * <p> 
     * 二进制数据编码为BASE64字符串 
     * </p> 
     *  
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(byte[] bytes) throws Exception {  
        return new String(Base64.encode(bytes));  
    }  
      
    /** *//** 
     * <p> 
     * 将文件编码为BASE64字符串 
     * </p> 
     * <p> 
     * 大文件慎用，可能会导致内存溢出 
     * </p> 
     *  
     * @param filePath 文件绝对路径 
     * @return 
     * @throws Exception 
     */  
    public static String encodeFile(String filePath) throws Exception {  
        byte[] bytes = fileToByte(filePath);  
        return encode(bytes);  
    }  
      
    /** *//** 
     * <p> 
     * BASE64字符串转回文件 
     * </p> 
     *  
     * @param filePath 文件绝对路径 
     * @param base64 编码字符串 
     * @throws Exception 
     */  
    public static void decodeToFile(String filePath, String base64) throws Exception {  
        byte[] bytes = decode(base64);  
        byteArrayToFile(bytes, filePath);  
    }  
      
    /** *//** 
     * <p> 
     * 文件转换为二进制数组 
     * </p> 
     *  
     * @param filePath 文件路径 
     * @return 
     * @throws Exception 
     */  
    public static byte[] fileToByte(String filePath) throws Exception {  
        byte[] data = new byte[0];  
        File file = new File(filePath);  
        if (file.exists()) {  
            FileInputStream in = new FileInputStream(file);  
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);  
            byte[] cache = new byte[CACHE_SIZE];  
            int nRead = 0;  
            while ((nRead = in.read(cache)) != -1) {  
                out.write(cache, 0, nRead);  
                out.flush();  
            }  
            out.close();  
            in.close();  
            data = out.toByteArray();  
         }  
        return data;  
    }  
      
    /** *//** 
     * <p> 
     * 二进制数据写文件 
     * </p> 
     *  
     * @param bytes 二进制数据 
     * @param filePath 文件生成目录 
     */  
    public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {  
        InputStream in = new ByteArrayInputStream(bytes);     
        File destFile = new File(filePath);  
        if (!destFile.getParentFile().exists()) {  
            destFile.getParentFile().mkdirs();  
        }  
        destFile.createNewFile();  
        OutputStream out = new FileOutputStream(destFile);  
        byte[] cache = new byte[CACHE_SIZE];  
        int nRead = 0;  
        while ((nRead = in.read(cache)) != -1) {     
            out.write(cache, 0, nRead);  
            out.flush();  
        }  
        out.close();  
        in.close();
    }
    public static String replacePhoneStr(String phoneStr) { 
//    	String str="Cqtw0BO5KhL4ER78%2BeXdikxa4oi50DWnANJQJFZBzwkDfH0BnG7oNCEC9QkbEkSA6NuQenbGY%2FQNdRUgBRetkmmKISrHyUhtA0PeUMjP2l%2BILbF64Bp9SQLwbxuh%2FimotqjzKVElRZ2x9C40B63Zm1gZ2EepEV%2F90cb4MCGEC%2BM%3D";
		return phoneStr.replaceAll("%2B", "+").replaceAll("%2F", "/").replaceAll("%3D", "=");
		
    }
    
    
    /**  
     * 生成随机的不重复的8位数
     * 这是典型的随机洗牌算法。  
     * 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域）  
     * 算法时间复杂度O(n)  
     * @return 随机8为不重复数组  
     */    
    public static String generateNumber() {    
        String no="";    
        //初始化备选数组    
        int[] defaultNums = new int[10];    
        for (int i = 0; i < defaultNums.length; i++) {    
            defaultNums[i] = i;    
        }    
    
        Random random = new Random();    
        int[] nums = new int[LENGTH];    
        //默认数组中可以选择的部分长度    
        int canBeUsed = 10;    
        //填充目标数组    
        for (int i = 0; i < nums.length; i++) {    
            //将随机选取的数字存入目标数组    
            int index = random.nextInt(canBeUsed);    
            nums[i] = defaultNums[index];    
            //将已用过的数字扔到备选数组最后，并减小可选区域    
            swap(index, canBeUsed - 1, defaultNums);    
            canBeUsed--;    
        }    
        if (nums.length>0) {    
            for (int i = 0; i < nums.length; i++) {    
                no+=nums[i];    
            }    
        }    
    
        return no;    
    }    
    private static final int LENGTH = 8;    
    
    private static void swap(int i, int j, int[] nums) {    
        int temp = nums[i];    
        nums[i] = nums[j];    
        nums[j] = temp;    
    }    
        
    public static String generateNumber2() {    
        String no="";    
        int num[]=new int[8];    
        int c=0;    
        for (int i = 0; i < 8; i++) {    
            num[i] = new Random().nextInt(10);    
            c = num[i];    
            for (int j = 0; j < i; j++) {    
                if (num[j] == c) {    
                    i--;    
                    break;    
                }    
            }    
        }    
        if (num.length>0) {    
            for (int i = 0; i < num.length; i++) {    
                no+=num[i];    
            }    
        }    
        return no;    
    }
   
    
}
