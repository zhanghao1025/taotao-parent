package com.taotao.test;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.common.utils.FastDFSClient;


public class TestUpload {
	@Test	
	public void test(){
		try {
			//根据配置文件创建服务链接
			ClientGlobal.init("D:\\workspace-orginal-mahjong\\taotao-parent\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
			//初始化tracker
			TrackerClient trackerClient=new TrackerClient();
			//获取tracker链接
			TrackerServer trackerServer = trackerClient.getConnection();
			//初始化一个storage server对象
			StorageServer storageServer=null;
			//初始化stroageClient对象
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//			String[] upload_file = storageClient.upload_file("D:\\KuGou\\01e8a157f86d8ca84a0d304fcb9943.jpg", "jpg", null);
			String[] upload_file = storageClient.upload_file("D:\\BiZhi\\ttt.jpg", "jpg", null);
			for (String string : upload_file) {
				System.out.println(string);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 测试工具类
	 */
	@Test
	public void testFastDFSClient() {
			try {
				FastDFSClient fastDFSClient = new FastDFSClient("D:\\workspace-orginal-mahjong\\taotao-parent\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
				String uploadFile = fastDFSClient.uploadFile("D:\\BiZhi\\ttt.jpg", "jpg");
					System.out.println(uploadFile);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
