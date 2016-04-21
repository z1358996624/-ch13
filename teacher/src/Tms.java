package com.briup.teacher;

import java.util.Scanner;

public class Tms {
	//��ʦ���飬��������������ʦ����Ϣ��
	private Teacher[] teas = new Teacher[3];
	private int index = 0; //��ʦ�ĸ���
	//�����ʦ
	public void add(Teacher tea){
		//��������е�Ԫ�صĸ������ڵ������鳤�ȵ�ʱ��˵�����鳤�Ȳ���
		if(index>=teas.length){
			//������չ
			Teacher[] demo = new Teacher[teas.length+3];
			//����Ŀ���
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;// stus[index] = stu; index++;
	}
	//ͨ��idɾ����ʦ 1002  1T  "HELLO WORLD"
	public void deleteById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int teaIndex = queryIndexById(id); // 1
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	
	//ͨ��id���Ҹ���ʦ���ڵ�λ�� 1002
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
	//ͨ��id��ѯ��ʦ ddl  dml
	public Teacher queryById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}

	//�鿴������ʦ��Ϣ
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	//������ʦ��Ϣ
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}
	//�˵�
	public void menu(){
		System.out.println("-----------��ʦ����ϵͳ-----------");
		System.out.println("*1���鿴������ʦ��Ϣ*");
		System.out.println("*2�������ʦ��Ϣ*");
		System.out.println("*3��ɾ����ʦ��Ϣ*");
		System.out.println("*4����ѯ��ʦ��Ϣ*");
		System.out.println("*5���޸���ʦ��Ϣ*");
		System.out.println("*exit���˳�*");
		System.out.println("*help������*");
		System.out.println("---------------��ӭ----------------");
	}

	public static void main(String[] args){
		//����Tms����
		Tms tms = new Tms();
		tms.menu();	//��ʾ��ҳ��
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("����������Ҫ�Ĺ��ܱ�ţ�");
			//�ȴ��û����빦�ܱ�ţ����û�����س���ʱ���ȡ�س�ǰ���������
			String option = scanner.nextLine();
			switch(option){
				case "1"://��ѯ����
					System.out.println("������������ʦ����Ϣ��");
					Teacher[] teas = tms.queryAll();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("�ܼƣ�"+teas.length+" ��");
					break;
				case "2"://���
					while(true){
						System.out.println("��������ʦ��Ϣ��id#name#age������������break�ص���һ��Ŀ¼");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//��װ����
						Teacher tea = new Teacher(id,name,age);
						//�жϸ��û�id�Ƿ��Ѿ�����ռ��
						Teacher dbtea = tms.queryById(id);
						if(dbtea!=null){
							System.out.println("��id�Ѿ�����ռ�ã�������¼�룡");
							continue;
						}

						tms.add(tea);
						System.out.println("��ӳɹ���");
					}
					
					break;
				case "3"://ɾ��
					while(true){
						System.out.print("��������Ҫɾ����ʦ��id����break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("ɾ���ɹ�����ǰ��ʦ����Ϊ��"+tms.index);
					}
					break;
				case "4"://��ѯ
					while(true){
						System.out.print("��������Ҫ��ѯ��ʦ��id����break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						System.out.println("��������Ҫ���ҵ���ʦ����Ϣ��");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
				case "5"://�޸�
					while(true){
						System.out.print("��������Ҫ�޸���ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						if(tea == null){
							System.out.println("����ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+tea);
						System.out.println("��������Ҫ�޸ĵ���Ϣ��name#age��");
						String teaStr = scanner.nextLine();
						String[] teaArr = teaStr.split("#");

						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);

						Teacher newtea = new Teacher(Long.parseLong(id),name,age);

						tms.update(newtea);
						System.out.println("�޸ĳɹ���");
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("�ټ�");
					System.exit(0);
				default:
					System.out.println("����������������룡");
			}
		}
			
	}
}