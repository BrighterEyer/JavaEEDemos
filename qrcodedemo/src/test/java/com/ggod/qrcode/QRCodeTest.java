package com.ggod.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ggod.utils.QRCodeUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.weixin.pay.orcode.logo.LogoConfig;
import com.weixin.pay.orcode.logo.ZXingCodeUtil;
import com.weixin.pay.orcode.logo.ZXingConfig;

public class QRCodeTest {

	final String fileRoot = System.getProperty("user.dir") + File.separator + "src/test/resources";

	@Test
	public void printDir() {
		String fRoot = System.getProperty("user.dir") + File.separator + "src/test/resources";
		File f = new File(fRoot);
		if (f.exists()) {
			System.out.println("目录存在!");
		}
		System.out.println();
	}

	/**
	 * 生成图像
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	@Test
	public void testEncode() throws WriterException, IOException {
		String fileName = "zxing.png";                                 
		JSONObject json = new JSONObject(); 
		json.put("zxing", "https://github.com/zxing/zxing/tree/zxing-3.0.0/javase/src/main/java/com/google/zxing");
		json.put("author", "ggod");
		String content = json.toJSONString();// 内容
		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "png";// 图像类型

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		Path path = FileSystems.getDefault().getPath(fileRoot, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		System.out.println("输出成功.");
	}

	/**
	 * 解析图像
	 */
	@Test
	public void testDecode() {
		String filePath = fileRoot + "zxing.png";
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			JSONObject content = JSONObject.parseObject(result.getText());
			System.out.println("图片中内容：  ");
			System.out.println("author： " + content.getString("author"));
			System.out.println("zxing：  " + content.getString("zxing"));
			System.out.println("图片中格式：  ");
			System.out.println("encode： " + result.getBarcodeFormat());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testUtils() {
		String FilePath = fileRoot + "qrcode-util.png";
		File qrFile = new File(FilePath);
		// 二维码内容
		String encodeddata = "Hello X-rapido";
		try {
			QRCodeUtils.qrCodeEncode(encodeddata, qrFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 解码
		String reText = QRCodeUtils.qrCodeDecode(qrFile);
		System.out.println(reText);
	}

	@Test
	public void testWriteZxing() throws Exception {
		String text = "NiuYueYue I Love You！"; // 二维码内容
		int width = 300; // 二维码图片宽度
		int height = 300; // 二维码图片高度
		String format = "gif";// 二维码的图片格式

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		File outputFile = new File(fileRoot + "new.gif");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}

	/**
	 * 这个工具类 ZXingCodeUtil 是提供二维码图片生成的工具
	 * <p>
	 * 首先使用的时候需要实例化 ZXingCodeUtil
	 * <p>
	 * 然后实例化参数 ZXingConfig 和 LogoConfig
	 * <p>
	 * 通过下面的演示可以详细看参数是按照什么循序进行设置
	 * <p>
	 * 最后调用 ZXingCodeUtil 中的 getQR_CODEBufferedImage来生成二维码
	 */
	@Test
	public void testZXingCodeUtil() throws WriterException {
		String content = "http://www.baidu.com";
		System.out.println("inputParam:" + content);
		try {
			// 生成二维码
			File file = new File(fileRoot + "Michael_QRCode.png");
			ZXingCodeUtil zp = new ZXingCodeUtil(); // 实例化二维码工具
			ZXingConfig zxingconfig = new ZXingConfig(); // 实例化二维码配置参数
			zxingconfig.setHints(zp.getDecodeHintType()); // 设置二维码的格式参数
			zxingconfig.setContent(content);// 设置二维码生成内容
			zxingconfig.setLogoPath(fileRoot + "logo.png"); // 设置Logo图片
			zxingconfig.setLogoConfig(new LogoConfig()); // Logo图片参数设置
			zxingconfig.setLogoFlg(true); // 设置生成Logo图片
			BufferedImage bim = zp.getQR_CODEBufferedImage(zxingconfig);// 生成二维码
			ImageIO.write(bim, "png", file); // 图片写出
			Thread.sleep(500); // 缓冲
			zp.parseQR_CODEImage(bim); // 解析调用
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
