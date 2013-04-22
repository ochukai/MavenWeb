/**
 * 
 */
package com.onakaumi.core.base.web.action.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.util.Path;

/**
 * @author Onakaumi
 * @Date 2013-4-2
 * @Description
 */
public class FileUploadAction extends DataSupportAction<AbstractModel<?>> {
	private static final long serialVersionUID = 1L;
	/**
	 * 需要上传的文件
	 */
	private File upload;
	/**
	 * 文件名成
	 */
	private String uploadFileName;
	/**
	 * 文件类型
	 */
	private String uploadContentType;
	/**
	 * 文件最大值
	 */
	private long maxSize = 0;

	@Override
	public String execute() throws Exception {
		Path path = new Path("files", uploadFileName);
		try {
			checkFileSize();
			saveFile(path.realPath(), upload);
			json.put("success", true);
			json.put("path", path.relativePath());
		} catch (Exception e) {
			json.put("success", false);
			json.put("error", e.getMessage());
		}
		return JSON;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public File getUpload() {
		return upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	private void checkFileSize() {

		if (maxSize == 0) {
			return;
		}

		if (upload.length() > maxSize) {
			throw new IllegalArgumentException("文件的长度超过限制!");
		}
	}

	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param target
	 * @param source
	 * @throws IOException
	 */
	private void saveFile(String target, File source) throws IOException {
		FileInputStream fis = new FileInputStream(source);
		FileOutputStream fos = new FileOutputStream(target);

		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			log.error("保存文件时出错。。。。");
			throw e;
		} finally {
			close(fos, fis);
		}
	}
}
