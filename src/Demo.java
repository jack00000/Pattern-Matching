import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demo extends JFrame{
	/*Author: Zero Nonsenser
	 * Time: 2017/10/14
	 */
	private static final long serialVersionUID = 1L;
	private JFrame jf=new JFrame("String Patern Matching");
	private JTextField patternInput=new JTextField();
	private JTextArea resource=new JTextArea();
	private JTextArea result=new JTextArea();
	private JButton find=new JButton("Find All");
	private JButton clear=new JButton("Clear");
	private JLabel l1=new JLabel("Pattern String :");
	private JLabel l2=new JLabel("Text Resource");
	private JLabel l3=new JLabel("Choose pattern :");
	private JLabel l4=new JLabel("Result");
	private JScrollPane jp=new JScrollPane(resource);
	private JScrollPane jp2=new JScrollPane(result);
	private JFileChooser chooser=new JFileChooser();
	private JComboBox<String> pattern=new JComboBox<String>();
	
//	构造函数
	Demo(){
		setFrame();
		setTextFields();
		setMenu();
		setButton();
	}
	
//	设置主界面
	void setFrame(){
		jf.setSize(600, 600);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	添加文本域
	void setTextFields(){
		l1.setBounds(20,30,100,30);
		jf.add(l1);
		
		patternInput.setBounds(120,30,200,25);
		jf.add(patternInput);
		patternInput.setCaretColor(Color.CYAN);
		patternInput.setSelectedTextColor(Color.MAGENTA);
		
		l2.setBounds(20,90,100,30);
		jf.add(l2);
		
		resource.setLineWrap(true);
		resource.setWrapStyleWord(true);
		resource.setEditable(false);
		resource.setCaretColor(Color.lightGray);
		resource.setSelectedTextColor(Color.getHSBColor(10, 10, 10));
		try {
			@SuppressWarnings("resource")
			BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/cache.txt")));
			resource.setText("\n\n\n\n\n");
			while(br.readLine()!=null){
				resource.setText(resource.getText()+"\n"+br.readLine()+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		jp.setBounds(20,120,300,400);
		jf.add(jp);
		
		l4.setBounds(370,95,100,25);
		jf.add(l4);
		
		result.setLineWrap(true);
		result.setWrapStyleWord(true);
		result.setEditable(false);
		result.setSelectedTextColor(Color.getHSBColor(1, 400, 100));
		jp2.setBounds(370,120,200,400);
		jf.add(jp2);
		
	}
	
//	设置下拉列表
	void setMenu(){
		l3.setBounds(370,30,100,25);
		jf.add(l3);
		pattern.addItem("Choose File");
		pattern.addItem("Chinese Demo");
		pattern.addItem("English Demo");
		pattern.addItem("Custom");
		pattern.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(pattern.getSelectedItem().toString()=="Chinese Demo"){
//					resource.setText("Input your text");
					resource.setText(null);
					result.setText(null);
					try {
						@SuppressWarnings("resource")
						BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/chidemo.txt")));
						int i=1;
						while(br.readLine()!=null){
							resource.setText(resource.getText().trim()+"\n"+i+"—"+br.readLine());
							i++;
						}
					} catch (Exception e2) {
						e2.printStackTrace();// TODO: handle exception
					}
				}
				else if(pattern.getSelectedItem().toString()=="English Demo"){
//					resource.setText("Input your text");
					resource.setText(null);
					result.setText(null);
					try {
						@SuppressWarnings("resource")
						BufferedReader br=new BufferedReader(new FileReader(new File("txtFile/engdemo.txt")));
						int i=1;
						while(br.readLine()!=null){
							resource.setText(resource.getText().trim()+"\n"+i+"—"+br.readLine());
							i++;
						}
					} catch (Exception e2) {
						e2.printStackTrace();// TODO: handle exception
					}
				}
				else if(pattern.getSelectedItem().toString()=="Choose File"){
//					resource.setText("Choose File");
					resource.setText(null);
					result.setText(null);
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooser.showOpenDialog(null);
					if(chooser.getSelectedFile().exists()&&chooser.getSelectedFile().canRead()){
						try {
							@SuppressWarnings("resource")
							BufferedReader br=new BufferedReader(new FileReader(chooser.getSelectedFile()));
							int i=1;
							while(br.readLine()!=null){
								resource.setText(resource.getText().trim()+"\n"+i+"—"+br.readLine());
								i++;
							}
						} catch (Exception e2) {
							e2.printStackTrace();// TODO: handle exception
						}
					}
				}
				else{
					resource.setEditable(true);
				}
			}
		});
		
		
		
		pattern.setBounds(470,30,100,25);
		jf.add(pattern);
	}
	
//	设置按钮
	void setButton(){
		find.setBounds(370,65,90,25);
		jf.add(find);
		find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sourcestring=resource.getText();
				String patternstring=patternInput.getText();
				String s[]=sourcestring.split("\n");
				result.setText("The text contains "+s.length+" lines.");
				result.setText(result.getText()+"\nThe following line(s) contain(s) "+"\""+patternstring+"\":");
				int no=0;
				for(int i=0;i<s.length;i++)
					if(s[i].contains(patternstring)){
						result.setText(result.getText()+"\n"+(no+1)+": "+s[i]+"\n");
						no++;
					}
				result.setText(result.getText()+"\n\n"+"Total "+no+" item(s) contsin(s) "+"\""+patternstring+"\".");
			}
		});
		
		clear.setBounds(480,65,90,25);
		jf.add(clear);
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				patternInput.setText(null);
				resource.setText(null);
				result.setText(null);
			}
		});
	}
	
//	int contained(char[] s,char index){
//		int len=s.length;
//		for(int i=len-1;i>=0;i--){
//			if(s[i]==index)
//				return len-i;
//		}
//		return -1;
//	}
	public static void main(String[] args) {
		new Demo();
	}
}
