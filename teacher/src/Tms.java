package com.briup.teacher;

import java.util.Scanner;

public class Tms {
	//老师数组，用来保存所有老师的信息的
	private Teacher[] teas = new Teacher[3];
	private int index = 0; //老师的个数
	//添加老师
	public void add(Teacher tea){
		//如果数组中的元素的个数大于等于数组长度的时候，说明数组长度不够
		if(index>=teas.length){
			//数组扩展
			Teacher[] demo = new Teacher[teas.length+3];
			//数组的拷贝
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;// stus[index] = stu; index++;
	}
	//通过id删除老师 1002  1T  "HELLO WORLD"
	public void deleteById(long id){
		//调用方法获取id为指定参数，所在数组中的位置
		int teaIndex = queryIndexById(id); // 1
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	
	//通过id查找该老师所在的位置 1002
	private int queryIndexById(long id){
		int teaIndex= -1;
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				teaIndex = i;
				break;
			}
		}
		return teaIndex;
	}
	//通过id查询老师 ddl  dml
	public Teacher queryById(long id){
		//调用方法获取id为指定参数，所在数组中的位置
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}

	//查看所有老师信息
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	//更新老师信息
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}
	//菜单
	public void menu(){
		System.out.println("-----------老师管理系统-----------");
		System.out.println("*1，查看所有老师信息*");
		System.out.println("*2，添加老师信息*");
		System.out.println("*3，删除老师信息*");
		System.out.println("*4，查询老师信息*");
		System.out.println("*5，修改老师信息*");
		System.out.println("*exit，退出*");
		System.out.println("*help，帮助*");
		System.out.println("---------------欢迎----------------");
	}

	public static void main(String[] args){
		//创建Tms对象
		Tms tms = new Tms();
		tms.menu();	//显示主页面
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入你想要的功能编号：");
			//等待用户输入功能编号，等用户输入回车的时候获取回车前输入的内容
			String option = scanner.nextLine();
			switch(option){
				case "1"://查询所有
					System.out.println("以下是所有老师的信息：");
					Teacher[] teas = tms.queryAll();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("总计："+teas.length+" 人");
					break;
				case "2"://添加
					while(true){
						System.out.println("请输入老师信息【id#name#age】，或者输入break回到上一级目录");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//封装对象
						Teacher tea = new Teacher(id,name,age);
						//判断该用户id是否已经被人占用
						Teacher dbtea = tms.queryById(id);
						if(dbtea!=null){
							System.out.println("该id已经被人占用，请重新录入！");
							continue;
						}

						tms.add(tea);
						System.out.println("添加成功！");
					}
					
					break;
				case "3"://删除
					while(true){
						System.out.print("请输入您要删除老师的id，或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！当前老师个数为："+tms.index);
					}
					break;
				case "4"://查询
					while(true){
						System.out.print("请输入您要查询老师的id，或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的老师的信息：");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
				case "5"://修改
					while(true){
						System.out.print("请输入您要修改老师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						if(tea == null){
							System.out.println("该老师不存在！");
							continue;
						}
						System.out.println("原信息为："+tea);
						System.out.println("请输入您要修改的信息【name#age】");
						String teaStr = scanner.nextLine();
						String[] teaArr = teaStr.split("#");

						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);

						Teacher newtea = new Teacher(Long.parseLong(id),name,age);

						tms.update(newtea);
						System.out.println("修改成功！");
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("再见");
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
			}
		}
			
	}
}