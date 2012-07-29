package com.lynch.cms.common.util;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.*;

public class Dom4jUtil {//implements XmlDocument {

	String root="employees";
	public void createXml(String fileName) {
		// TODO Auto-generated method stub
		Document document = DocumentHelper.createDocument();   
        Element employees=document.addElement(root);//root
        Element employee=employees.addElement("employee");   
        employee.addAttribute("title", "XML Zone"); //给employee添加title属性，并设置他的值
        Element name= employee.addElement("name");name.setText("小倪");
        Element sex=employee.addElement("sex");sex.setText("famale");
       
        Element age=employee.addElement("age");age.setText("29");
       
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName));
//            Writer fileWriter=new FileWriter(fileName);   
//            XMLWriter xmlWriter=new XMLWriter(fileWriter);   
            xmlWriter.write(document);   
            xmlWriter.close();

        } catch (IOException e) {   
            System.out.println(e.getMessage());   
        }   

	}

	public void parserXml(String fileName) {//只能解析2层结构的xml(不包括根root)
		// TODO Auto-generated method stub
		File inputXml=new File(fileName);   
        SAXReader saxReader = new SAXReader();   
        try {   
           Document document = saxReader.read(inputXml);   
           Element employees=document.getRootElement();   
           for(Iterator i = employees.elementIterator(); i.hasNext();){   
                Element employee = (Element) i.next();   
                for(Iterator j = employee.elementIterator(); j.hasNext();){   
                    Element node=(Element) j.next();   
                    System.out.println(node.getName()+":"+node.getText());   
                }   
 
           }   
       } catch (DocumentException e) {   
           System.out.println(e.getMessage());   
       }   
//       System.out.println("dom4j parserXml");   

	}
	public static void main(String[] args){
		//System.out.println(new Util().hash("111111"));
		Dom4jUtil dom=new Dom4jUtil();
		String fileName="C:\\t.xml";
		dom.createXml(fileName);
		System.out.println("ok!!!");
//		dom.parserXml(fileName);
		dom.getRoot(dom.readXML(fileName));
		
	}

	//递归解析XML
	//用法：dom.getRoot(dom.readXML(fileName));
	public Document readXML(String fileName) {
        Document document = null;   
        SAXReader saxReader = new SAXReader();   
        try {   
            document = saxReader.read(new File(fileName));   
        } catch (DocumentException e) {   
            e.printStackTrace();   
        }   
        return document;   
    }
	 public void getRoot(Document document) {   
	        Element root = document.getRootElement();   
	        tree(root);   
	    }

    private void tree(Element element) {//递归解析xml
        Iterator iterator = element.elementIterator();   
        List list = null;   
        while (iterator.hasNext()) {   
            Element node = (Element) iterator.next();   
            list = node.elements();   
            if (list != null && list.size() > 0) {   
//                System.out.print(node.getName()+"\n");//父元素 
                int count = node.attributeCount();   
                for (int i = 0; i < count; i++) {   
                    Attribute attr = node.attribute(i);   
                    System.out.println("属性" + attr.getName() + ":" + attr.getText());   
                }
                tree(node);   
            } else {   
                System.out.println(node.getName() + "=" + node.getText());   
            }   
        }   
    }

}