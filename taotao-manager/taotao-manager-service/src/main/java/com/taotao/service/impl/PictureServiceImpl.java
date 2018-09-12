package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	
	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) {
		//创建一个返回对象
		PictureResult pictureResult = new PictureResult();
		
		if(uploadFile.isEmpty()){
			pictureResult.setError(1);
			pictureResult.setMessage("图片为空");
			return pictureResult;
		}else{
			//取图片的扩展名
			String filename = uploadFile.getOriginalFilename();
			//取扩展名不要“.”
			String extName = filename.substring(filename.lastIndexOf(".")+1);
			try {
				FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/client.conf");
				//上传图片并返回路径
				String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
				//返回结果
				pictureResult.setError(0);
				pictureResult.setUrl(IMAGE_SERVER_BASE_URL+url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				pictureResult.setError(1);
				pictureResult.setMessage("图片上传失败");
			}
			
			
		}
		return pictureResult;
	}

}
