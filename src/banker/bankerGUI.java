package banker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class bankerGUI {
	public static void main(String[] args) {
		Frame f1 = new Frame("banker");
		f1.setSize(500,500);
		f1.setLocation(300,200);
		f1.setLayout(new FlowLayout());
		JLabel lp = new JLabel("������̸�����");
		TextField tf1 = new TextField(10);
		JLabel ls = new JLabel("������Դ������");
		TextField tf2 = new TextField(10);
		JLabel l1 = new JLabel("����ʣ����Դ���У�");
		TextField tf = new TextField(40);
		JLabel l2 = new JLabel("��������������У�");
		TextArea ta1 = new TextArea(10,40);
		JLabel l3 = new JLabel("�����ѷ�����Դ���У�");
		TextArea ta2 = new TextArea(10,40);
		f1.add(lp);
		f1.add(tf1);
		f1.add(ls);
		f1.add(tf2);
		f1.add(l1);
		f1.add(tf);
		f1.add(l2);
		f1.add(ta1);
		f1.add(l3);
		f1.add(ta2);
		JButton btn = new JButton("���");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p = Integer.valueOf(tf1.getText().toString());
				int s = Integer.valueOf(tf2.getText().toString());
				String []a = tf.getText().toString().split(" ");
				String []d = ta1.getText().toString().split("[ ,\r]");
				String []l = ta2.getText().toString().split("[ ,\r]");
				if(a.length!=s||a.length==0)
					JOptionPane.showMessageDialog(null, "��������ȷ����Դ���У�");
				else if(d.length!=p*s)
					JOptionPane.showMessageDialog(null, "��������ȷ������������У�");
				else if(l.length!=p*s)
					JOptionPane.showMessageDialog(null, "��������ȷ���ѷ�����Դ���У�");
				else {
					int []available = new int[s];
					int [][]demand = new int[p][s];
					int [][]allocate = new int[p][s];
					for(int i=0;i<s;i++)available[i]=Integer.valueOf(a[i]);
					for(int i=0;i<p;i++) {
						for(int j=0;j<s;j++) {
							demand[i][j]=Integer.valueOf(d[j+(i)*s].replaceAll("\n", ""));
							allocate[i][j]=Integer.valueOf(l[j+(i)*s].replaceAll("\n", ""));
						}
					}
					banker bk = new banker(p,s,available,demand,allocate);
					if(bk.isSafe()) {
						int []order = bk.getOrder();
						String o="";
						for(int i=0;i<p;i++) {
							o+=String.valueOf(order[i]+1);
							if(i!=p-1)o+="-->";
						}	
						JOptionPane.showMessageDialog(null, "��ϵͳ״̬��ȫ��\n���̵��ô���Ϊ��"+o);
					}
					else JOptionPane.showMessageDialog(null, "��ϵͳ״̬����ȫ��");
				}
			}
		});
		f1.add(btn);
		f1.setVisible(true);

	}
	
//	public static void init(int p, int s, String[] a, String[] d, String[] l) {
//		int []available = new int[s];
//		int [][]demand = new int[p][s];
//		int [][]allocate = new int[p][s];
//		for(int i=0;i<s;i++)available[i]=Integer.valueOf(a[i]);
//		for(int i=0;i<p;i++) {
//			for(int j=0;j<s;j++) {
//				demand[i][j]=Integer.valueOf(d[j+(i)*p]);
//				allocate[i][j]=Integer.valueOf(l[j+(i)*p]);
//			}
//		}
//		banker bk = new banker(p,s,available,demand,allocate);
//		if(bk.isSafe()) {
//			int []order = bk.getOrder();
//			String o="";
//			for(int i=0;i<p;i++) {
//				o+=(order+" ");
//			}
//			JOptionPane.showMessageDialog(null, "��ϵͳ״̬��ȫ��\n���̵��ô���Ϊ��"+o);
//		}
//		else JOptionPane.showMessageDialog(null, "��ϵͳ״̬����ȫ��");
//	}
}
