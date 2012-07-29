package com.lynch.cms.common.image;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片尺寸工厂类 
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class ImageSizeFactory {

	/**
	 * 需要上传所有尺寸的图片list(如有需要，请自行添加)
	 * 
	 * 注意事项：每个list里第一个尺寸是默认尺寸
	 */
	
	/**广告尺寸list*/
	public static List<ImageSize> admentList ;
	/**特色区域尺寸list*/
	public static List<ImageSize> nadList;
	/**品牌尺寸list*/
	public static List<ImageSize> brandList;
	/**商品尺寸list*/
	public static List<ImageSize> goodsList;
	/**微博头像*/
	public static List<ImageSize> weiBoList;
	
	public ImageSizeFactory(){
		
		CreatAdmentList();
		CreatNadList();
		CreatBrandList();
		CreatGoodsList();
		CreateWeiBoList();
	}
	
	public void CreatAdmentList(){
		
		admentList = new ArrayList<ImageSize>();
		admentList.add(new ImageSize(130,100));
		admentList.add(new ImageSize(150,90));
	}
	
	public void CreatNadList(){
		
		nadList = new ArrayList<ImageSize>();
		nadList.add(new ImageSize(132,45));
		nadList.add(new ImageSize(130,39));
	}
	
	public void CreatBrandList(){
		
		brandList = new ArrayList<ImageSize>();
		brandList.add(new ImageSize(40,30));
		brandList.add(new ImageSize(165,74));
		brandList.add(new ImageSize(181,59));
		brandList.add(new ImageSize(72,97));
		brandList.add(new ImageSize(740,140));
	}
	
	public void CreatGoodsList(){
		
		goodsList = new ArrayList<ImageSize>();
		goodsList.add(new ImageSize(140,140));
		goodsList.add(new ImageSize(300,280));
		goodsList.add(new ImageSize(70,65));
		goodsList.add(new ImageSize(1300,1000));
		goodsList.add(new ImageSize(203,217));
	}
	
	public void CreateWeiBoList(){
		
		weiBoList = new ArrayList<ImageSize>();
		weiBoList.add(new ImageSize(180,180));
		weiBoList.add(new ImageSize(50,50));
		weiBoList.add(new ImageSize(30,30));
	}

}
